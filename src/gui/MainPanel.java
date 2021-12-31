package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import utils.Point;

public class MainPanel extends JPanel implements MouseListener {

    private final int RESULT_WIDTH = 300;
    private final int RESULT_HEIGHT = 300;
    private final int MAX_POINTS = 250;
    public static final int POINT_SIZE = 3;
    MainForm mainForm;
    boolean changed;
    boolean enabled;

    public MainPanel(MainForm mainForm) {
        this.mainForm = mainForm;
        this.addMouseListener(this);
        changed = false;
        this.enabled = false;
        this.enabled = true;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        ResultGenerator resultGenerator = new ResultGenerator(RESULT_WIDTH, RESULT_HEIGHT, mainForm);

        //g.setColor(Color.RED);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(resultGenerator.generuj(changed), this.getWidth() / 2 - RESULT_WIDTH / 2, 0, this);
    }

    public void mouseClicked(MouseEvent e) {
        if (!isInMap(e.getX(), e.getY()) || !isEnabled()) {
            return;
        }


        int absX = e.getX() - (getWidth() / 2 - RESULT_WIDTH / 2);
        int absY = e.getY();

        System.out.println("" + absX + " " + absY);

        int index = getIndex(absX, absY);
        if (index == -1) {
            if (mainForm.points.point.size() < MAX_POINTS) {
                mainForm.points.point.add(new Point(absX, absY));
            }
        } else {
            mainForm.points.point.remove(index);
        }

        repaint();
        mainForm.points.saveToFile("points.pnt");

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public boolean isInMap(int x, int y) {
        int startX = getWidth() / 2 - RESULT_WIDTH / 2;
        int startY = 0;
        if ((x < startX) || (x > startX + RESULT_WIDTH)) {
            return false;
        }

        return (y >= startY) && (y <= startY + RESULT_HEIGHT);
    }

    public int getIndex(int x, int y) {
        for (int i = 0; i < mainForm.points.point.size(); i++) {
            if (diff(x, y, mainForm.points.point.get(i)) < POINT_SIZE) {
                return i;
            }
        }

        return -1;
    }

    private double diff(int x, int y, Point point) {
        return Math.sqrt(Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }


}
