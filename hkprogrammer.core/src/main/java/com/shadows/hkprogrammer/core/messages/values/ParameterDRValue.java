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
public class ParameterDRValue implements Serializable {
    private int OnValue = 0;
    private int OffValue = 0;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.OnValue;
        hash = 41 * hash + this.OffValue;
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
        final ParameterDRValue other = (ParameterDRValue) obj;
        if (this.OnValue != other.OnValue) {
            return false;
        }
        if (this.OffValue != other.OffValue) {
            return false;
        }
        return true;
    }   
    
    public int getOnValue() {
        return OnValue;
    }

    public void setOnValue(int OnValue) {
        ValidateValue(OnValue);
        this.OnValue = OnValue;
    }

    public int getOffValue() {
        return OffValue;
    }

    public void setOffValue(int OffValue) {
        ValidateValue(OffValue);
        this.OffValue = OffValue;
    }
        
    private void ValidateValue(int Value){
        if (Value > 127 || Value < 0)
            throw new IllegalArgumentException("Value of DR must be between 0 and 127");
    }
}
