package week2;

import week2.helperFunction.CircleAbstract;
import week2.helperFunction.RectangleAbstract;

public class question7{
    public static void main(String[] args) {
        CircleAbstract circle = new CircleAbstract(5);
        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());

        RectangleAbstract rectangle = new RectangleAbstract(4, 6);
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());
    }
}
