package DesignPattern.SingletonDesignPattern;

public class SingleTon {
    // only one object is initialized
    private static SingleTon s;

    private SingleTon() {
    }

    public static synchronized SingleTon getInstance() {
        if (s == null) {
            s = new SingleTon();
        }
        return s;
    }
}
