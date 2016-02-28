/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import static com.shadows.hkprogrammer.core.ByteConvertHelper.IntegerToByte;
import static com.shadows.hkprogrammer.core.ByteConvertHelper.LSB;
import static com.shadows.hkprogrammer.core.ByteConvertHelper.MSB;

/**
 *
 * @author John
 */
public class ByteArrayHelper {
     public static byte[] WriteToByteArray(byte[] message, byte[] part, int offset){       
        int length = part.length;        
        if ((offset + length) > message.length)
            throw new ArrayIndexOutOfBoundsException("Part length is more then the space available for write in message from given offset!");
        System.arraycopy(part, 0, message, offset, length);
        return message;
    }
    
     public static byte[] ReadFromByteArray(byte[] message, int offset, int length){
        if ((offset + length) > message.length)
            throw new ArrayIndexOutOfBoundsException("Part length is more then the space available fore read in message from given offset!");
        byte[] part = new byte[length];
        System.arraycopy(message, offset, part, 0, length);
        return part;
    }
     
    public static int ByteArrayChecksum(byte[] array){
        int Sum = 0;
        for (int i = 0;i<array.length;i++)
            Sum += array[i];
        return Sum;
    }
    
    public static byte[] ByteArrayCheckSumBySB(byte[] array){
        int Sum = ByteArrayChecksum(array);
        return ByteConvertHelper.IntegerToByteBySB(Sum);
    }       
    
}
