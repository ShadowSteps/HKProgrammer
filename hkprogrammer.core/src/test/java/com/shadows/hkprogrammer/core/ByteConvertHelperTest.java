/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author John
 */
public class ByteConvertHelperTest {
    
    public ByteConvertHelperTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of MSB method, of class ByteConvertHelper.
     */
    @Test
    public void testMSB() {
        System.out.println("MSB");
        byte[] value = { 0, 39, 0, 1 };
        byte expResult = 39;
        byte result = ByteConvertHelper.MSB(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of LSB method, of class ByteConvertHelper.
     */
    @Test
    public void testLSB() {
        System.out.println("LSB");
        byte[] value = { 0, 39, 0, 1 };
        byte expResult = 1;
        byte result = ByteConvertHelper.LSB(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of ByteToString method, of class ByteConvertHelper.
     */
    @Test
    public void testByteToString() {
        System.out.println("ByteToString");
        byte[] value = { 97, 98, 99 };
        String expResult = "abc";
        String result = ByteConvertHelper.ByteToString(value);
        assertEquals(expResult, result);        
    }

    /**
     * Test of ByteToInteger method, of class ByteConvertHelper.
     */
    @Test
    public void testByteToInteger() {
        System.out.println("ByteToInteger");
        byte[] value = new byte[]{-2, -4, -8, -16};
        Integer expResult = -16975632;
        Integer result = ByteConvertHelper.ByteToInteger(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of StringToByte method, of class ByteConvertHelper.
     */
    @Test
    public void testStringToByte() {
        System.out.println("StringToByte");
        String value = "abc";
        byte[] expResult = { 97, 98, 99 };
        byte[] result = ByteConvertHelper.StringToByte(value);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of IntegerToByte method, of class ByteConvertHelper.
     */
    @Test
    public void testIntegerToByte() {
        System.out.println("IntegerToByte");
        int value = -16975632;
        byte[] expResult = new byte[]{-2, -4, -8, -16};
        byte[] result = ByteConvertHelper.IntegerToByte(value);
        assertArrayEquals(expResult, result);
    }
    
}
