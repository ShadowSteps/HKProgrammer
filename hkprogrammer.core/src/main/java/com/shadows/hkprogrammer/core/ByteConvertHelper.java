/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author John
 */
public class ByteConvertHelper {
    public static byte MSB(byte[] value){
        int index = 0;
        int length = value.length - 1;
        if (index < 0)
            throw new IndexOutOfBoundsException("Byte array given is empty!");
        while (value[index] == 0&&index<length)
            index++;
        return value[index];
    }
    public static byte LSB(byte[] value){      
        int index = value.length - 1;
        if (index < 0)
            throw new IndexOutOfBoundsException("Byte array given is empty!");
        return value[index];
    }        
    
    public static String ByteToString(byte[] value){
        return new String(value, StandardCharsets.UTF_8);
    }
    
    public static int ByteToInteger(byte[] value){
        if (value.length < 4){
            byte[] newValue = new byte[4];
            System.arraycopy(value, 0, newValue, value.length, 4 - value.length);
            value = newValue;
        }
        return ByteBuffer.wrap(value).getInt();
    }
    
    public static byte[] StringToByte(String value){
        return value.getBytes(StandardCharsets.UTF_8);
    }
    
    public static byte[] IntegerToByte(int value){
        return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
    }               
    
    public static byte[] IntegerToByteBySB(int value){
        byte[] intBytes = IntegerToByte(value);
        return new byte[] { MSB(intBytes), LSB(intBytes) };
    }
}
