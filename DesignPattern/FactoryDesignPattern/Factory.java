package DesignPattern.FactoryDesignPattern;

public class Factory {
    public Shape getShape(String shapeType) {
        if (shapeType.equals("square")) {
            return new Circle();
        } else if (shapeType.equals("rectangle")) {
            return new Rectangle();
        } else {
            return new Circle();
        }
    }
}
