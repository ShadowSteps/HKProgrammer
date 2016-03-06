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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.Normal;
        hash = 71 * hash + this.ID;
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
        final ThrottleCurve other = (ThrottleCurve) obj;
        if (this.Normal != other.Normal) {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }
    
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
