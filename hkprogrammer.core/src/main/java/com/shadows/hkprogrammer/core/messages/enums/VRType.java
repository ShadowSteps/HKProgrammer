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
public enum VRType {
    VRA(0),
    VRB(1);
    
    private final int value;
    
    private VRType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public VRType fromInteger(int Integer){
        switch (Integer){
            case 0:
                return VRA;
            case 1:
                return VRB;            
        }
        throw new IllegalArgumentException("VRType value is between 0 and 1!");
    }
}
