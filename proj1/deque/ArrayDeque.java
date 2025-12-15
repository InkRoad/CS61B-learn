package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] dequeArr;
    private int head, tail, length;

    public ArrayDeque() {
        dequeArr = (T[]) new Object[8];
        length = 8;
        head = 0;
        tail = 0;
    }

    private class AdIterator implements Iterator<T> {
        private int pointer;
        private int size;
        AdIterator() {
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

    /*
    *  计算实际位置，用于解决负数超过最大-length时重新回到尾端
    * */
    private int calcRealPos(int pos, int convertLength) {
        return (pos - (pos / convertLength - 1) * convertLength) % convertLength;
    }

    /*
    *  将相对数组长度转换为实际位置
    * */
    private int relativeIndex(int originlength, int i) {
        return (i + calcRealPos(head, originlength)) % originlength;
    }

    private void resizeBigOrless(boolean select) {
        int newLength = (int) ((double) length * (select ? 2 : 0.5));
        T[] expand = (T[]) new Object[newLength];

        for (int i = 0, len = size(); i < len; i++) {
            expand[relativeIndex(newLength, i)] = get(i);
        }
        dequeArr = expand;
        length = newLength;
    }

    @Override
    public void addFirst(T item) {
        if (size() == length) {
            resizeBigOrless(true);
        }
        dequeArr[calcRealPos(--head, length)] = item;
    }

    @Override
    public void addLast(T item) {
        if (size() == length) {
            resizeBigOrless(true);
        }
        dequeArr[calcRealPos(tail++, length)] = item;
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T res = dequeArr[calcRealPos(head++, length)];
        if (size() > 4 && size() * 4 < length) {
            resizeBigOrless(false);
        }
        return res;
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T res = dequeArr[calcRealPos(--tail, length)];
        if (size() > 4 && size() * 4 < length) {
            resizeBigOrless(false);
        }
        return res;
    }

    @Override
    public void printDeque() {
        for (int i = 0, len = size(); i < len; i++) {
            System.out.print(String.valueOf(get(i)) + ' ');
        }
        System.out.println();
    }

    @Override
    public int size() {
        return tail - head == length ? length : (tail - head + length) % length;
    }

    private int length() {
        return length;
    }

    @Override
    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        return dequeArr[relativeIndex(length, index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new AdIterator();
    }
    
    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o instanceof Deque && size() == ((Deque) o).size()) {
            flag = true;
            int len = size();
            for (int i = 0; i < len; i++) {
                if (!get(i).equals(((Deque) o).get(i))) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
