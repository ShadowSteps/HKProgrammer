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
public class ThrottleCurve {
    private int Normal = 0;
    private int ID = 0;

    public int getNormal() {
        return Normal;
    }

    public void setNormal(int Normal) {
        ValidateValue(Normal);
        this.Normal = Normal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        ValidateValue(ID);
        this.ID = ID;
    }
    
    private void ValidateValue(int Value){
        if (Value > 127 || Value < 0)
            throw new IllegalArgumentException("Value of Throttle Curve must be between 0 and 127");
    } 
    
}
