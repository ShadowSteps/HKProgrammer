/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.enums;

import java.io.Serializable;

/**
 *
 * @author John
 */
public enum MixSwitch implements Serializable  {
    SWA(0),
    SWB(1),
    ON(2),
    OFF(3);
    
    private final int value;
    
    private MixSwitch(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static MixSwitch fromInteger(int Integer){
        switch (Integer){
            case 0:
                return SWA;
            case 1:
                return SWB;    
            case 2:
                return ON;
            case 3:
                return OFF;
        }
        throw new IllegalArgumentException("MixSwitch value is between 0 and 3!");
    }
}
