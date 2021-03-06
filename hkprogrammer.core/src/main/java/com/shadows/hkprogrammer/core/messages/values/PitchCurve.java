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
public class PitchCurve implements Serializable  {
    public byte Normal = 0;
    public byte ID = 0;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.Normal;
        hash = 37 * hash + this.ID;
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
        final PitchCurve other = (PitchCurve) obj;
        if (this.Normal != other.Normal) {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }   
    
    public byte getNormal() {
        return Normal;
    }

    public void setNormal(byte Normal) {
        ValidateValue(Normal);
        this.Normal = Normal;
    }

    public byte getID() {
        return ID;
    }

    public void setID(byte ID) {
        ValidateValue(ID);
        this.ID = ID;
    }
    
    private void ValidateValue(byte Value){
        if (Value > 127 || Value < -128)
            throw new IllegalArgumentException("Value of Pitch Curve must be between 0 and 127");
    } 
    
}
