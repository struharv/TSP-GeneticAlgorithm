package gui;

public class MainLoop implements Runnable {

    public final int SLEEP_TIME = 100;
    MainForm mainForm;

    public MainLoop(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void run() {
        try {
            do {
                Thread.sleep(SLEEP_TIME);
                if (mainForm.active) {
                    mainForm.step();
                }

            } while (true);

        } catch (InterruptedException ex) {
        }

    }

    public void pause() {
        try {
            this.wait();
        } catch (Exception ex) {
        }
    }

    public void start() {
        try {
            this.notify();
        } catch (Exception ex) {
        }
    }
}
