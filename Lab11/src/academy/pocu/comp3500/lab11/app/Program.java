package academy.pocu.comp3500.lab11.app;

import academy.pocu.comp3500.lab11.BallBoy;
import academy.pocu.comp3500.lab11.data.Point;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        testOfficial();
        testOnePoint();

        System.out.println("No prob lab 11");
    }

    public static void testOfficial() {
        String startPoint = new Point(0, 0).toString();

        {
            Point[] points = {};

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == 1);

            String path0 = path.get(0).toString();

            assert (path0.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(-3, -4)
            };
            String ballA = points[0].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();

            assert (path0.equals(startPoint));
            assert (path1.equals(ballA));
            assert (path2.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(20, 15),
                    new Point(20, 48),
                    new Point(0, 63)
            };
            String ballA = points[0].toString();
            String ballB = points[1].toString();
            String ballC = points[2].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();
            String path3 = path.get(3).toString();
            String path4 = path.get(4).toString();

            assert (path0.equals(startPoint));
            assert ((path1.equals(ballA) && path2.equals(ballB) && path3.equals(ballC))
                    || (path1.equals(ballC) && path2.equals(ballB) && path3.equals(ballA)));
            assert (path4.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(2, 0),
                    new Point(2, 2),
                    new Point(0, 2),
                    new Point(20, 20),
                    new Point(22, 20),
                    new Point(22, 22),
                    new Point(20, 22)
            };

            String ballA = points[0].toString();
            String ballB = points[1].toString();
            String ballC = points[2].toString();
            String ballD = points[3].toString();
            String ballE = points[4].toString();
            String ballF = points[5].toString();
            String ballG = points[6].toString();


            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();
            String path3 = path.get(3).toString();
            String path4 = path.get(4).toString();
            String path5 = path.get(5).toString();
            String path6 = path.get(6).toString();
            String path7 = path.get(7).toString();
            String path8 = path.get(8).toString();

            assert (path0.equals(startPoint));
            assert (path1.equals(ballA) && path2.equals(ballB) && path3.equals(ballD) && path4.equals(ballE)
                     && path5.equals(ballF) && path6.equals(ballG) && path7.equals(ballC));
            assert (path8.equals(startPoint));
        }
    }

    public static void testOnePoint() {
        String startPoint = new Point(0, 0).toString();

        Point ballA = new Point(2, 3);
        String ballAString = ballA.toString();

        List<Point> path = BallBoy.findPath(new Point[]{ballA});
        assert (path.size() == 3);

        String path0 = path.get(0).toString();
        String path1 = path.get(1).toString();
        String path2 = path.get(2).toString();

        assert (path0.equals(startPoint));
        assert (path1.equals(ballAString));
        assert (path2.equals(startPoint));
    }
}
