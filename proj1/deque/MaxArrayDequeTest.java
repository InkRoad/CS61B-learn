package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    @Test
    public void test(){
        MaxArrayDeque<Integer> d = new MaxArrayDeque<>(Integer::compare);
        d.addFirst(23);
        d.addFirst(12);
        d.addFirst(11);
        d.addFirst(67);
        d.addFirst(78);
        assertEquals(78,(int)d.max());
        d.addFirst(124);
        assertEquals(124,(int)d.max());
    }
}
