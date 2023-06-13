package ru.stqa.java_learn.sandbox;

public class FirstProgram {

    public static void main(String[] args) {

        Point p1 = new Point(3, 5);
        Point p2 = new Point(2, 1);
        System.out.println("Расстояние между точками = " + p1.distance(p2));
    }


}