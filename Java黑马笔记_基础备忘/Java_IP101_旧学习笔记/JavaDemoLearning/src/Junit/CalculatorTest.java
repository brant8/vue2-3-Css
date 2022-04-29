package Junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    //for test
    @Before
    public void init(){
        System.out.println("init...");
    }
    @After
    public void close(){
        System.out.println("closed...");
    }

    @Test
    public void testAdd(){
        CalculatorJunit c = new CalculatorJunit();
        int result = c.add(1,2);
        System.out.println("test method...");
        Assert.assertEquals(3,result);
    }

}
