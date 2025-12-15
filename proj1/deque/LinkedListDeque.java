package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        private T item;
        private Node rest;
        private Node front;
    }

    private Node sentienl;
    private int size;

    private class LldIterator implements Iterator<T> {
        private int pointer;
        private int size;

        public LldIterator() {
            pointer = 0;
            size = size();
        }

        public boolean hasNext() {
            return pointer < size;
        }

        public T next() {
            return get(pointer++);
        }
    }

    public LinkedListDeque() {
        sentienl = new Node();
        sentienl.rest = sentienl;
        sentienl.front = sentienl;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node temp = new Node();
        temp.item = item;
        temp.front = sentienl;
        temp.rest = sentienl.rest;

        if (size == 0) {
            sentienl.front = temp;
        } else {
            sentienl.rest.front = temp;
        }
        sentienl.rest = temp;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node temp = new Node();
        temp.item = item;
        temp.front = sentienl.front;
        temp.rest = sentienl;
        sentienl.front.rest = temp;
        sentienl.front = temp;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentienl.rest;
        while (p != sentienl) {
            System.out.print(String.valueOf(p.item) + ' ');
            p = p.rest;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T res = sentienl.rest.item;
        sentienl.rest.rest.front = sentienl;
        sentienl.rest = sentienl.rest.rest;
        if (size == 1) {
            sentienl.front = sentienl;
        }
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T res = sentienl.front.item;
        sentienl.front.front.rest = sentienl;
        sentienl.front = sentienl.front.front;
        if (size == 1) {
            sentienl.rest = sentienl;
        }
        size--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentienl.rest;
        while (index-- != 0) {
            p = p.rest;
        }
        return p.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LldIterator();
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o instanceof Deque && size() == ((Deque) o).size()) {
            flag = true;
            int len = size();
            for (int i = 0; i < len; i++) {
                if (get(i) != ((Deque) o).get(i)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    private T recurNode(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return recurNode(p.rest, index - 1);
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        return recurNode(sentienl.rest, index);
    }

}
