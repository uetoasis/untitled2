package sample;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Enemy {

    int x;
    int y;
    int speed = 10;
    int WIDTH_IMAGE = 40;
    int HEIGHT_IMAGE = 40;
    int wayPointIndex = 0;
    Image image;
    Direction direction;

    public final Point[] wayPoints = new Point[]{
            new Point(0 * WIDTH_IMAGE + 0, 2 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(11 * WIDTH_IMAGE + WIDTH_IMAGE/2, 2 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(11 * WIDTH_IMAGE + WIDTH_IMAGE/2, 7 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(2 * WIDTH_IMAGE + WIDTH_IMAGE/2, 7 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(2 * WIDTH_IMAGE + WIDTH_IMAGE/2, 11 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(8 * WIDTH_IMAGE + WIDTH_IMAGE/2, 11 * HEIGHT_IMAGE + HEIGHT_IMAGE/2),
            new Point(8 * WIDTH_IMAGE + WIDTH_IMAGE/2, 16 * HEIGHT_IMAGE ),
    };

    enum Direction {
        LEFT(180), UP(270), RIGHT(0), DOWN(90);
        int degree;

        Direction(int i) {
            degree = i;
        }

        int getDegree() {
            return degree;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y, 40, 40);
    }

    public Point getNextWayPoint() {
        if (wayPointIndex < wayPoints.length - 1)
            return wayPoints[++wayPointIndex];
        return null;
    }

    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public void calculateDirection() {
        if (wayPointIndex >= wayPoints.length) {
            return;
        }

        Point currentWP = wayPoints[wayPointIndex];
        if ( distance(x, y, currentWP.x, currentWP.y) <= speed) {
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) return;
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed) direction = Direction.RIGHT;
            else if (deltaX < -speed) direction = Direction.LEFT;
            else if (deltaY > speed) direction = Direction.DOWN;
            else if (deltaY <= -speed) direction = Direction.UP;
        }
    }

    public void update() {
        calculateDirection();

        switch (direction) {
            case UP:
                y --;
                break;
            case DOWN:
                y ++;
                break;
            case LEFT:
                x --;
                break;
            case RIGHT:
                x ++;
                break;
        }
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}