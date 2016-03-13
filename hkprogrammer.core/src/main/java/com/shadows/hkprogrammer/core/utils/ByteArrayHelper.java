/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.utils;

/**
 *
 * @author John
 */
class ByteArrayHelper {
    private final ByteConvertHelper Converter = new ByteConvertHelper();    
    public byte[] WriteToByteArray(byte[] message, byte[] part, int offset) throws ArrayIndexOutOfBoundsException{       
        int length = part.length;        
        if ((offset + length) > message.length)
            throw new ArrayIndexOutOfBoundsException("Part length is more then the space available for write in message from given offset!");
        System.arraycopy(part, 0, message, offset, length);
        return message;
    }
    
    public byte[] ReadFromByteArray(byte[] message, int offset, int length) throws ArrayIndexOutOfBoundsException{
        if ((offset + length) > message.length)
            throw new ArrayIndexOutOfBoundsException("Part length is more then the space available fore read in message from given offset!");
        byte[] part = new byte[length];
        System.arraycopy(message, offset, part, 0, length);
        return part;
    }
     
    public int ByteArrayChecksum(byte[] array){
        int Sum = 0;
        for (Byte byteValue : array) {
            Sum += byteValue;
        }
        return Sum;
    }
    
    public byte[] ByteArrayCheckSumBySB(byte[] array){
        int Sum = ByteArrayChecksum(array);
        return Converter.IntegerToByteBySB(Sum);
    }       
    
}
