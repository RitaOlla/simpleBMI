package com.example.rita_ola.simplebmi;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void for_valid_data_bmi_is_correct(){
        BMI bmiCounter = new KgMBmiCounter(60, (float) 1.70);
        double bmi = bmiCounter.calculateBMI();
        assertEquals(20.76124567, bmi,0.001);
    }
   /* @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_throw_exception() {
        BMI bmiCounter = new KgMBmiCounter(0, 0);
        try {
            double bmi = bmiCounter.calculateBMI();
        }catch (IllegalArgumentException e)
        {
            System.out.println("Exception was caught");
        }
    }*/

    @Test
    public void for_valid_data_return_correct() {
        BMI bmiCounter = new LbInBmiCounter(400, 80);
            double bmi = bmiCounter.calculateBMI();
            assertEquals(43.939, bmi, 0.001);
    }

    @Test
    public void for_negative_throw_ex() {
        BMI bmiCounter = new LbInBmiCounter(0, 0);
        try {
            double bmi = bmiCounter.calculateBMI();
            fail();

            //Assert.fail("Should thrown Illegal Argument Exception");
        }catch(IllegalArgumentException e){
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /*@Test
    public void for_negative_should_throw_exception(){
        BMI bmiCounter = new KgMBmiCounter( -10, -20);
        try{
            double bmi = bmiCounter.calculateBMI() ;
            fail();
        }catch (IllegalArgumentException ex){
            System.out.println("Exception was caught");
        }
    }

    @Test
    public void for_zero_throw_exception1(){
        BMI bmiCounter = new LbInBmiCounter( 0, 0);
        try{
            double bmi = bmiCounter.calculateBMI();
            fail();
        }catch (IllegalArgumentException ex){
            System.out.println("Exception was caught");
        }
    }

    @Test
    public void for_negative_should_throw_exception2(){
        BMI bmiCounter = new LbInBmiCounter( -40, -50);
        try{
            double bmi = bmiCounter.calculateBMI() ;
            fail();
        }catch (IllegalArgumentException ex){
            //System.out.println("Exception was caught");
        }
    }
*/
    /*@Test(expected= IllegalArgumentException.class)
    public void for_negative_throw_exception3() {
        BMI bmiCounter = new LbInBmiCounter( -40, -50);
        double bmi = bmiCounter.calculateBMI() ;
    }*/


}