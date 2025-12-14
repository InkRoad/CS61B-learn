package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{

    private T dequeArr[];
    private int head,tail,length;

    public ArrayDeque(){
        dequeArr = (T[])new Object[8];
        length = 8;
        head = tail = 0;
    }

    private class adIterator implements Iterator<T>{
        private int pointer;
        private int size;

        public adIterator(){
            pointer = 0;
            size = size();
        }

        public boolean hasNext(){
            return pointer < size;
        }

        public T next(){
            return get(pointer++);
        }
    }

    private int RelativeIndex(int length,int i){
        return (i + head + length)%length;
    }

    private void resizeBigOrless(boolean select){
        int new_length = (int)((double)length*(select?2:0.5));
        T[] expand = (T[])new Object[new_length];

        for(int i = 0;i<length;i++) expand[RelativeIndex(new_length,i)] = get(i);
        dequeArr = expand;
        length = new_length;
    }

    @Override
    public void addFirst(T item){
        if(size() == length) resizeBigOrless(true);
        dequeArr[(--head + length)% length] = item;
    }

    @Override
    public void addLast(T item){
        if(size() == length) resizeBigOrless(true);
        dequeArr[(tail++ + length)%length] = item;
    }

    @Override
    public T removeFirst(){
        if(size() == 0) return null;
        T res = dequeArr[(head++ + length)%length];
        if(size()*4 < length) resizeBigOrless(false);
        return res;
    }

    @Override
    public T removeLast(){
        if(size() == 0) return null;
        T res = dequeArr[(--tail + length)%length];
        if(size()*4 < length) resizeBigOrless(false);
        return res;
    }

    @Override
    public void printDeque(){
        for(int i = 0,len = size();i < len;i++) System.out.print(String.valueOf(get(i)) +' ');
        System.out.println();
    }

    @Override
    public int size(){
        return tail-head == length? length:(tail - head + length)% length;
    }

    public int length(){
        return length;
    }

    @Override
    public T get(int index){
        if(index >= size()) return null;
        return dequeArr[RelativeIndex(length,index)];
    }

    @Override
    public Iterator<T> iterator(){
        return new adIterator();
    }
    
    @Override
    public boolean equals(Object o){
        boolean flag = false;
        if(o instanceof ArrayDeque){
            flag = true;
            int len = size();
            for(int i = 0;i<size();i++){
                if(i == ((ArrayDeque)o).size() || get(i) != ((ArrayDeque)o).get(i)) {flag = false;break;}
            }
        }
        return flag;
    }
}
