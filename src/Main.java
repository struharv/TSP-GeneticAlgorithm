import gui.MainForm;
import utils.Point;
import utils.Points;

public class Main {

    public static void main(String[] args) {
        Points points = new Points();
        points.point.add(new Point(48, 44));
        points.point.add(new Point(256, 41));
        points.point.add(new Point(256, 273));
        points.point.add(new Point(34, 273));
        points.point.add(new Point(118, 43));
        points.point.add(new Point(194, 44));
        points.point.add(new Point(109, 270));
        points.point.add(new Point(197, 269));
        points.point.add(new Point(49, 111));
        points.point.add(new Point(46, 188));
        points.point.add(new Point(246, 111));
        points.point.add(new Point(256, 193));
        points.point.add(new Point(77, 239));
        points.point.add(new Point(153, 237));
        points.point.add(new Point(230, 236));

        points.point.add(new Point(116, 200));
        points.point.add(new Point(196, 196));
        points.point.add(new Point(116, 147));
        points.point.add(new Point(195, 146));

        MainForm mainForm = new MainForm(points, false);

    }

}
