package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddRemoveFirst(){
        int testTimes = 100000;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for(int i = 0; i < testTimes; i++) {
            int selection = StdRandom.uniform(0,3);
            int randomData = StdRandom.uniform(100);
            switch (selection){
                case 0:
                    ad.addFirst(randomData);
                    lld.addFirst(randomData);
                    break;
                case 1:
                    assertEquals(lld.removeFirst(),ad.removeFirst());
                    break;
                case 2:
                    assertEquals(lld.isEmpty(),ad.isEmpty());
            }
        }
    }

    @Test
    public void testAddRemoveLast(){
        int testTimes = 100000;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for(int i = 0; i < testTimes; i++) {
            int selection = StdRandom.uniform(0,3);
            int randomData = StdRandom.uniform(100);
            switch (selection){
                case 0:
                    ad.addLast(randomData);
                    lld.addLast(randomData);
                    break;
                case 1:
                    assertEquals(lld.removeLast(),ad.removeLast());
                    break;
                case 2:
                    assertEquals(lld.isEmpty(),ad.isEmpty());
            }
        }
    }

    @Test
    public void testResize(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for(int i = 0;i<9;i++) ad.addLast(i);
        //assertEquals(16,ad.length());
        for(int i = 0;i<6;i++) ad.removeLast();
        //assertEquals(8,ad.length());
    }

    @Test
    public void testEqualsAndIterable(){
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        LinkedListDeque<Integer> d2 = new LinkedListDeque<>();
        ArrayDeque<Integer> d3 = new ArrayDeque<>();
        assertFalse(d1.equals(d2));
        d1.addFirst(12);
        assertFalse(d1.equals(d3));
        d3.addFirst(12);
        assertTrue(d1.equals(d3));

        d1.addFirst(112);
        d1.addFirst(132);
        d1.addFirst(1);
        for(int x:d1){
            System.out.println(x);
        }
    }

    @Test
    public void testGetSize() {
        int testTimes = 100000;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for(int i = 0; i < testTimes; i++) {
            int selection = StdRandom.uniform(0,5);
            int randomData = StdRandom.uniform(100);
            switch (selection){
                case 0:
                    ad.addLast(randomData);
                    lld.addLast(randomData);
                    break;
                case 1:
                    ad.addFirst(randomData);
                    lld.addFirst(randomData);
                    break;
                case 2:
                    ad.removeFirst();
                    lld.removeFirst();
                    break;
                case 3:
                    if (!lld.isEmpty()) {
                        int index = StdRandom.uniform(0,lld.size());
                        assertEquals(lld.get(index),ad.get(index));
                    }
                    break;
                case 4:
                    assertEquals(lld.size(),ad.size());
            }
        }
    }
}
