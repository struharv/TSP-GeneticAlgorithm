package gui;

import ga.GaParams;
import ga.Pool;
import utils.Points;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;



public class MainForm extends JFrame {

    final int DEFAULT_POPULATION = 4000;
    final int DEFAULT_MIN_POPULATION = 20;
    final int DEFAULT_MAX_POPULATION = 50000;
    final int DEFAULT_SELECTION = 5;
    final double DEFAULT_MUT = 0.012;
    public Pool pool;
    MainPanel mainPanel;
    JButton btnStep;
    JButton btnPlay;
    JButton btnPause;
    JButton btnReset;
    JLabel lblGenerations;
    JLabel lblLength;
    JSpinner spnMutationRate;
    JSpinner spnSelectionRate;
    JSpinner spnPopulationRate;
    MainLoop mainLoop;
    public boolean active;
    Points points;
    JPanel optionPanel;

    public MainForm(Points points, boolean isApplet) {
        super("TSP Genetic Algorithm");
        this.active = false;
        this.points = points;
        this.pool = null;
        this.setSize(450, 500);
        this.setLayout(new BorderLayout());
        if (!isApplet) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        mainPanel = new MainPanel(this);
        this.add(mainPanel);

        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        btnStep = new JButton("Step");
        btnStep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                step();
            }
        });


        btnPlay = new JButton("Start");
        btnPause = new JButton("Stop");
        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        btnPlay.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        btnPause.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });


        controlPanel.add(btnReset);
        controlPanel.add(btnPause);
        controlPanel.add(btnPlay);
        controlPanel.add(btnStep);
        this.add(controlPanel, BorderLayout.SOUTH);
        optionPanel = createControlPanel();
        this.add(createTabbedPane(optionPanel, createInfoPanel()),
                BorderLayout.NORTH);
        init();
        initLoop();
        this.setVisible(true);
    }

    public void start() {
        btnStep.setEnabled(false);
        btnPlay.setEnabled(false);
        btnReset.setEnabled(false);
        btnPause.setEnabled(true);
        active = true;
        mainLoop.start();
    }

    public void initLoop() {
        mainLoop = new MainLoop(this);
        Thread t = new Thread(mainLoop);
        t.start();
    }

    public void stop() {
        btnStep.setEnabled(true);
        btnPlay.setEnabled(true);
        btnReset.setEnabled(true);
        btnPause.setEnabled(false);

        active = false;
        mainLoop.pause();
    }

    public void reset() {
        if (pool != null) {
            pool.reset();
        }
        lblGenerations.setText("-");
        lblLength.setText("-");
        enableOptionPanel();

        pool = null;
        mainPanel.repaint();
    }

    public void step() {
        if (pool == null) {
            GaParams params = new GaParams();
            params.mutace = (Double) spnMutationRate.getValue();
            params.selekce = (Integer) spnSelectionRate.getValue();
            params.setPopulace((Integer) spnPopulationRate.getValue());


            pool = new Pool(points, params);
        }

        pool.evoluce();
        disableOptionPanel();
        lblGenerations.setText("" + pool.pocetGeneraci);
        lblLength.setText("" + pool.best.fitnesss);
        mainPanel.repaint();
    }

    public void init() {
        btnStep.setEnabled(true);
        btnPlay.setEnabled(true);
        btnReset.setEnabled(true);
        btnPause.setEnabled(false);
    }

    public JPanel createInfoPanel() {
        JPanel res = new JPanel(new GridLayout(0, 2));
        lblGenerations = new JLabel();
        lblLength = new JLabel();
        res.add(new JLabel("Generations"));
        res.add(lblGenerations);

        res.add(new JLabel("Path Length"));
        res.add(lblLength);

        JPanel pom = new JPanel(new BorderLayout());
        pom.add(res, BorderLayout.NORTH);
        return pom;

    }

    public JTabbedPane createTabbedPane(JPanel options, JPanel info) {
        JTabbedPane res = new JTabbedPane();
        res.addTab("Setting", options);
        res.addTab("Results", info);
;
        res.setPreferredSize(new Dimension(200, 150));
        return res;
    }

    public JPanel createControlPanel() {
        JPanel res = new JPanel(new GridLayout(0, 2));
        spnMutationRate = new JSpinner(new SpinnerDouble(DEFAULT_MUT));
        spnPopulationRate = new JSpinner(new SpinnerNumberModel(DEFAULT_POPULATION, DEFAULT_MIN_POPULATION, DEFAULT_MAX_POPULATION, 1));
        spnSelectionRate = new JSpinner(new SpinnerNumberModel(DEFAULT_SELECTION, 1, DEFAULT_MAX_POPULATION, 1));
        res.add(new JLabel("Mutation Rate"));
        res.add(spnMutationRate);

        res.add(new JLabel("Population Size"));
        res.add(spnPopulationRate);

        res.add(new JLabel("Selection parameter"));
        res.add(spnSelectionRate);
        JPanel pom = new JPanel(new BorderLayout());
        pom.add(res, BorderLayout.NORTH);
        return pom;
    }

    public void disableOptionPanel() {
        spnMutationRate.setEnabled(false);
        spnPopulationRate.setEnabled(false);
        spnSelectionRate.setEnabled(false);
        mainPanel.setEnabled(false);
    }

    public void enableOptionPanel() {
        spnMutationRate.setEnabled(true);
        spnPopulationRate.setEnabled(true);
        spnSelectionRate.setEnabled(true);
        mainPanel.setEnabled(true);
    }
}
