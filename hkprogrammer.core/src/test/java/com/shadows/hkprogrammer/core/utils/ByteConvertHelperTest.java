/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.utils;

import com.shadows.hkprogrammer.core.utils.ByteConvertHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author John
 */
public class ByteConvertHelperTest {
    
    private ByteConvertHelper Converter = new ByteConvertHelper();
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
        byte result = Converter.MSB(value);
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
        byte result = Converter.LSB(value);
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
        String result = Converter.ByteToString(value);
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
        Integer result = Converter.ByteToInteger(value);
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
        byte[] result = Converter.StringToByte(value);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of IntegerToByte method, of class ByteConvertHelper.
     */
    @Test
    public void testIntegerToByte() {
        System.out.println("IntegerToByte");
        int value = -16975632;
        byte[] expResult = new byte[]{ -2, -4, -8, -16 };
        byte[] result = Converter.IntegerToByte(value);
        assertArrayEquals(expResult, result);
    }
    
     /**
     * Test of IntegerToByteBySB method, of class ByteConvertHelper.
     */
    @Test
    public void testIntegerToByteBySB() {
        System.out.println("IntegerToByteBySB");
        int value = -16975632;
        byte[] expResult = new byte[]{-2, -16};
        byte[] result = Converter.IntegerToByteBySB(value);
        assertArrayEquals(expResult, result);
    }
    
     /**
     * Test of BooleanToByte method, of class ByteConvertHelper.
     */
    @Test
    public void testBooleanToByte() {
        System.out.println("BooleanToByte");
        boolean value = true,
                falseValue = false;
        byte[] expResult = new byte[]{ 0,0,0,(byte)1 },
                expResultFalse = new byte[] { 0,0,0,(byte)0 };
        byte[] result = Converter.BooleanToByte(value),
                falseResult = Converter.BooleanToByte(falseValue);
        assertArrayEquals(expResult, result);
        assertArrayEquals(expResultFalse, falseResult);
        
    }
    
    /**
     * Test of ByteToBoolean method, of class ByteConvertHelper.
     */
    @Test
    public void testByteToBoolean() {
        System.out.println("ByteToBoolean");
        byte[] value = new byte[]{ 0,0,0,(byte)1 },
                falseValue = new byte[] { 0,0,0,(byte)0 };
        boolean expResult = true,
                expResultFalse = false;
        boolean result = Converter.ByteToBoolean(value),
                falseResult = Converter.ByteToBoolean(falseValue);
        assertTrue(result);
        assertFalse(falseResult);
        
    }
}
