package tester;

import static org.junit.Assert.*;

//import afu.org.checkerframework.checker.signature.qual.SourceName;
import edu.princeton.cs.algs4.StdRandom;
//import org.junit.Assert;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void testRandom(){
        int testTimes = 1000;
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        StringBuilder errorMsg = new StringBuilder("");
        for(int i =0;i<testTimes;i++){
            int selection = StdRandom.uniform(0,4);
            int randnumber = StdRandom.uniform(0,100);
            switch (selection){
                case 0:
                    ads.addFirst(randnumber);
                    sad.addFirst(randnumber);
                    errorMsg.append("addFirst(" + String.valueOf(randnumber) + ")\n");
                    break;
                case 1:
                    ads.addLast(randnumber);
                    sad.addLast(randnumber);
                    errorMsg.append("addLast(" + String.valueOf(randnumber) + ")\n");
                    break;
                case 2:
                    errorMsg.append("removeFirst()\n");
                    if(!ads.isEmpty() && !sad.isEmpty()) assertEquals(errorMsg.toString(),(Integer)ads.removeFirst(),(Integer)sad.removeFirst());
                    break;
                case 3:
                    errorMsg.append("removeLast()\n");
                    if(!ads.isEmpty() && !sad.isEmpty()) assertEquals(errorMsg.toString(),(Integer)ads.removeLast(),(Integer)sad.removeLast());
                    break;
            }
        }
    }
}
