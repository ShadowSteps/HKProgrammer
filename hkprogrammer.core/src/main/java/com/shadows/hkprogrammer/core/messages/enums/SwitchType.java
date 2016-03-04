/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.enums;

/**
 *
 * @author John
 */
public enum SwitchType {
    SwitchA(0),
    SwitchB(1);
    
    private final int value;
    
    private SwitchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public SwitchType fromInteger(int Integer){
        switch (Integer){
            case 0:
                return SwitchA;
            case 1:
                return SwitchB;            
        }
        throw new IllegalArgumentException("SwitchType value is between 0 and 1!");
    }
    
}
