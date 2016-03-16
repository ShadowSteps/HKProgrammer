/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.values;

import java.io.Serializable;

/**
 *
 * @author John
 */
public class PotmeterEndPoint implements Serializable  {
    private int Left = 0;
    private int Right = 0;        

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.Left;
        hash = 53 * hash + this.Right;
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
        final PotmeterEndPoint other = (PotmeterEndPoint) obj;
        if (this.Left != other.Left) {
            return false;
        }
        if (this.Right != other.Right) {
            return false;
        }
        return true;
    }
        
    public int getLeft() {
        return Left;
    }

    public void setLeft(int Left) {
        ValidateValue(Left);
        this.Left = Left;
    }

    public int getRigth() {
        return Right;
    }

    public void setRigth(int Right) {
        ValidateValue(Right);
        this.Right = Right;
    }
    
    private void ValidateValue(int Value){
        if (Value > 127 || Value < 0)
            throw new IllegalArgumentException("Value of DR must be between 0 and 127");
    } 
}
