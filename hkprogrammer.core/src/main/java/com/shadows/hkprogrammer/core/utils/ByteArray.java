/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author John
 */
public class ByteArray {
    private byte[] Value;
    private final ByteArrayHelper ArrayTools = new ByteArrayHelper();
    private final ByteConvertHelper Convert = new ByteConvertHelper();
    public final int length;
    private int currentOffset = 0;
        
    public ByteArray(int length) {
        if (length < 0)
            throw new IllegalArgumentException("ByteArray cannot have negative length!");
        this.Value = new byte[length];
        this.length = length;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Arrays.hashCode(this.Value);
        hash = 79 * hash + Objects.hashCode(this.ArrayTools);
        hash = 79 * hash + Objects.hashCode(this.Convert);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ByteArray other = (ByteArray) obj;
        if (!Arrays.equals(this.Value, other.Value)) {
            return false;
        }
        if (!Objects.equals(this.ArrayTools, other.ArrayTools)) {
            return false;
        }
        if (!Objects.equals(this.Convert, other.Convert)) {
            return false;
        }
        return true;
    }
    
    
    
    public static ByteArray FromByteArray(byte[] Array){
        ByteArray newArray = new ByteArray(Array.length);
        newArray.Write(Array, 0);
        return newArray;
    }
    
    public void Write(ByteArray chunk,int offset){
        this.Write(chunk.ToPrimitive(), offset);
        
    }
    
    public void Write(ByteArray chunk){
        this.Write(chunk.ToPrimitive(), currentOffset);        
    }
    
    public void Write(byte[] chunk,int offset){
        this.Value = ArrayTools.WriteToByteArray(Value, chunk, offset);
        this.currentOffset += chunk.length;
    }
    
    public void Write(byte[] chunk){
        this.Write(chunk, currentOffset);        
    }
    
    public void Write(int chunk,int offset,boolean convertInt){
        byte[] Integer;
        if (convertInt)
            Integer = Convert.IntegerToByteBySB(chunk);
        else
            Integer = Convert.IntegerToByte(chunk);
        this.Write(Integer, offset);
    }
    
    public void Write(int chunk,boolean convertInt){
        this.Write(chunk, currentOffset, convertInt);
    }
    
    public void Write(int chunk,int offset){
        this.Write(chunk, offset, false);
    }
    
    public void Write(int chunk){
        this.Write(chunk, false);
    }
    
    public void Write(String chunk,int offset){
        byte[] str = Convert.StringToByte(chunk);
        this.Write(str, offset);
    }
    
    public void Write(String chunk){
        this.Write(chunk, currentOffset);
    }
    
    public void Write(boolean chunk,int offset){
        byte[] str = Convert.BooleanToByte(chunk);
        this.Write(str, offset);
    }
    
    public void Write(boolean chunk){
        this.Write(chunk, currentOffset);
    }
    
    public byte[] ToPrimitive(){
        return this.Value;
    }
    
    public ByteArray Read(int offset, int length){
        byte[] newArrayBytes = ArrayTools.ReadFromByteArray(Value, offset, length);
        ByteArray newArray = new ByteArray(newArrayBytes.length);
        newArray.Write(newArrayBytes, 0);
        return newArray;
    }
    
    public int Length(){
        return this.Value.length;
    }
    
    public int Checksum(){
        return ArrayTools.ByteArrayChecksum(Value);
    }
    
    public ByteArray ChecksumBySB(){
        byte[] Checksum = ArrayTools.ByteArrayCheckSumBySB(Value);
        return ByteArray.FromByteArray(Checksum);
    }
    
    public int ToInteger(){
        return Convert.ByteToInteger(Value);
    }
    
    public String ToString(){
        return Convert.ByteToString(Value);
    }
    
    public boolean ToBoolean(){
        return Convert.ByteToBoolean(Value);
    }
}
