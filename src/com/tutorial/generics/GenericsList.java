package com.tutorial.generics;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

//Add constraint for T (Float, Int, Double)
//T is bounded
//public class GenericsList<T extends Number & Comparable>
public class GenericsList<T> implements Iterable {
    private T[] items = (T[]) new Object[10];
    private int count;

    public void add(T item){
        items[count++] = item;
    }
    public T get(int index){
        return items[index];
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }

    private class ListIterator implements Iterator<T>{

        private GenericsList<T> list;
        private int index;

        public ListIterator(GenericsList<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return (index < list.count);
        }

        @Override
        public T next() {
            return list.items[index++];
        }
    }
}
