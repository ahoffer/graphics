package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class Model {

    protected List<Point3D> points = new ArrayList<>();
    Stack<Function<Point3D, Point3D>> transformations = new Stack();

    public Function<Point3D, Point3D> pop() {
        return transformations.pop();
    }

    public Model push(Function<Point3D, Point3D> t) {
        transformations.push(t);
        return this;
    }

    public List<Point3D> transform() {
        List<Point3D> transformedPoints = new ArrayList<>(points.size());
        for (Point3D p : points) {
            Point3D newPoint = p;
            for (Function<Point3D, Point3D> t : transformations) {
                newPoint = t.apply(newPoint);
            }
            transformedPoints.add(newPoint);
        }
        return transformedPoints;
    }
}
