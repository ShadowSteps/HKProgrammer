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
public enum SwitchFunction {
    Unassigned(0),
    DualRate(1),
    ThrottleCut(2),
    Normal(3);
    
    private final int value;
    
    private SwitchFunction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static SwitchFunction fromInteger(int Integer){
        switch (Integer){
            case 0:
                return Unassigned;
            case 1:
                return DualRate; 
            case 2:
                return ThrottleCut;         
            case 3:
                return Normal;   
        }
        throw new IllegalArgumentException("SwitchFunction value is between 0 and 3!");
    }
}
