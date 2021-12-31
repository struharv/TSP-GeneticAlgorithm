package gui;

import utils.Point;
import utils.Points;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;



public class ResultGenerator {

    Color cl_blue = new Color(164, 207, 232);
    Color cl_red = new Color(232, 164, 193);
    Color cl_gray = new Color(230, 230, 230);
    MainForm mainForm;
    int width;
    int height;

    public ResultGenerator(int width, int height, MainForm mainForm) {
        this.width = width;
        this.height = height;
        this.mainForm = mainForm;
    }

    public BufferedImage generuj(boolean changed) {
        BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        if (im == null) {
            return null;
        }


        Graphics g = im.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0,
                width, height);
        g.setColor(Color.BLACK);
        for (Point item : mainForm.points.point) {
            g.drawOval(item.getX() - MainPanel.POINT_SIZE, item.getY() - MainPanel.POINT_SIZE,
                    MainPanel.POINT_SIZE * 2, MainPanel.POINT_SIZE * 2);
        }
        if (mainForm.pool != null) {
            if (!changed) {
                if (mainForm.pool.best.individum != null) {
                    drawPath(g2, mainForm.pool.best.individum);
                }
            }
        }
        return im;
    }

    public void drawPath(Graphics2D g2, int[] nej) {
        if (nej.length == 0) {
            return;
        }
        Points points = mainForm.pool.points;
        int lastX = points.point.get(nej[nej.length - 1] - 1).getX();
        int lastY = points.point.get(nej[nej.length - 1] - 1).getY();
        for (int i = 0; i < nej.length; i++) {
            g2.drawLine(lastX, lastY,
                    points.point.get(nej[i] - 1).getX(), points.point.get(nej[i] - 1).getY());
            lastX = points.point.get(nej[i] - 1).getX();
            lastY = points.point.get(nej[i] - 1).getY();
        }

    }
}
