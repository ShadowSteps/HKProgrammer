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
public enum VRFunction {
    Unassigned(0),
    PitchAdjust(1);
    
    private final int value;
    
    private VRFunction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static VRFunction fromInteger(int Integer){
        switch (Integer){
            case 0:
                return Unassigned;
            case 1:
                return PitchAdjust;            
        }
        throw new IllegalArgumentException("VRFunction value is between 0 and 1!");
    }
}
