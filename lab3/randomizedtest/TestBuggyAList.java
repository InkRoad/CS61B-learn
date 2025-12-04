package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> orign = new AListNoResizing<>();
        BuggyAList<Integer> detect = new BuggyAList<>();
        orign.addLast(4);orign.addLast(5);orign.addLast(6);
        detect.addLast(4);detect.addLast(5);detect.addLast(6);
        for(int i = 0 ;i<orign.size();i++) assertEquals(orign.get(i),detect.get(i));
        orign.removeLast();detect.removeLast();
        for(int i = 0,len = orign.size();i<len;i++){
            orign.removeLast();detect.removeLast();
            for(int j = 0;j<orign.size();j++) assertEquals(orign.get(i),detect.get(i));
        }

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
            else if(operationNumber == 2 && L.size()>0){
                for(int j = 0;j<L.size();j++) assertEquals(L.get(j),B.get(j));
            }
            else if(operationNumber == 3 && L.size()>0){
                assertEquals(L.removeLast(),B.removeLast());
            }
        }
    }
}
