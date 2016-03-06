/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author John
 */
public class ByteConvertHelper {
    private byte[] TrimIntegerBytes(byte[] array){
        int j = 0;
        for( int i=0;  i<array.length;  i++ )
        {
            if (array[i] != 0||array.length-1 == i)
                array[j++] = array[i];
        }
        byte[] newArray = new byte[j];
        System.arraycopy( array, 0, newArray, 0, j );
        return newArray;
    }
    public byte MSB(byte[] value){
        int index = 0;
        int length = value.length - 1;
        if (index < 0)
            throw new IndexOutOfBoundsException("Byte array given is empty!");
        while (value[index] == 0&&index<length-1)
            index++;
        return value[index];
    }
    public byte LSB(byte[] value){      
        int index = value.length - 1;
        if (index < 0)
            throw new IndexOutOfBoundsException("Byte array given is empty!");
        return value[index];
    }        
    
    public String ByteToString(byte[] value){
        return new String(value, StandardCharsets.UTF_8);
    }
    
    public int ByteToInteger(byte[] value){
        if (value.length < 4){
            byte[] newValue = new byte[4];
            System.arraycopy(value, 0, newValue, 4 - value.length, value.length);
            value = newValue;
        }
        return ByteBuffer.wrap(value).getInt();
    }
    
    public byte[] StringToByte(String value){
        return value.getBytes(StandardCharsets.UTF_8);
    }
    
    public byte[] IntegerToByte(int value, boolean trim){
        byte[] bytes =  new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
        if (trim)
            return TrimIntegerBytes(bytes);
        else 
            return bytes;
    }   
    
    public byte[] IntegerToByte(int value){
        return IntegerToByte(value,false);
    }         
    
    public byte[] IntegerToByteBySB(int value){
        byte[] intBytes = IntegerToByte(value);
        return new byte[] { MSB(intBytes), LSB(intBytes) };
    }
    
    public byte[] BooleanToByte(boolean value){
        int intValue = value ? 1 : 0;
        byte[] values = IntegerToByte(intValue,true);
        return values;
    }

    public boolean ByteToBoolean(byte[] Value) {
        int intValue = ByteToInteger(Value);
        return intValue == 1;
    }
}
