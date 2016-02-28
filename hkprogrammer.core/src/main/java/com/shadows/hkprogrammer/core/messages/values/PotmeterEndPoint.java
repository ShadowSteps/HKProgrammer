/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.values;

/**
 *
 * @author John
 */
public class PotmeterEndPoint {
    private int Left;
    private int Right;        
    
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
