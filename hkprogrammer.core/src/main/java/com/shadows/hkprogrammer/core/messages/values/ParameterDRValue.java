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
public class ParameterDRValue {
    private int OnValue;
    private int OffValue;

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
