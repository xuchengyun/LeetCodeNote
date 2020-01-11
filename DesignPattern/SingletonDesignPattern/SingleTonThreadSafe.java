package DesignPattern.SingletonDesignPattern;

public class SingleTonThreadSafe {
    // once a object is created, synchronization is no longer useful.
    private volatile static SingleTonThreadSafe object;

    private SingleTonThreadSafe() {
    }

    public SingleTonThreadSafe getInstance() {
        if (object == null) {
            synchronized (SingleTonThreadSafe.class) {
                if (object == null) {
                    object = new SingleTonThreadSafe();
                }
            }
        }
        return object;
    }
}
