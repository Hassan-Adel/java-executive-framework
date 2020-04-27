package com.tutorial.generics;

public class KeyValuePair<K,V> {
    private K Key;
    private V value;

    public KeyValuePair(K key, V value) {
        Key = key;
        this.value = value;
    }
}
