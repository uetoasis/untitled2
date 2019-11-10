package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ActionEvent {
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

    abstract class GameObject {
        int i, j;
        int x;
        int y;
        Image image;

        abstract void render(GraphicsContext gc);
        abstract void update();

    }

    abstract class MovableObject extends GameObject {
        double speed;
        Direction direction;
    }

    abstract class VulnerableObject extends MovableObject {
        int health;
        double reward;
    }
}






/*
    public static final ActionEvent.Point[] wayPoints = new ActionEvent.Point[]{
            new ActionEvent.Point(0 * WIDTH_IMAGE + 0, 2 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(11 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 2 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(11 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 7 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(2 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 7 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(2 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 11 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(8 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 11 * HEIGHT_IMAGE + HEIGHT_IMAGE / 2),
            new ActionEvent.Point(8 * WIDTH_IMAGE + WIDTH_IMAGE / 2, 16 * HEIGHT_IMAGE),
    };


 */

