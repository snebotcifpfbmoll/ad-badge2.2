package com.snebot.fbmoll.functional;

@FunctionalInterface
public interface Function2<T1, T2, R> {
    R apply(T1 var1, T2 var2);
}
