package com.arthur.chapter3;

/**
 * 防止指令重排
 */
public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {}

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
