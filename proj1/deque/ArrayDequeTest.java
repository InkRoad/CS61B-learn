package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddPrint(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        assertTrue(ad.isEmpty());
        ad.addFirst(1);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(7);
        ad.addFirst(1);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(1);
        ad.addLast(3);


        assertEquals(9,ad.size());
        assertEquals(3,(int)ad.removeFirst());
        assertEquals(3,(int)ad.removeLast());
        assertEquals(1,(int)ad.removeFirst());
        assertEquals(1,(int)ad.removeLast());
        assertFalse(ad.isEmpty());

        ad.printDeque();
        //System.out.println(-8 % 8);
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
}
