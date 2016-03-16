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
public enum MixSource implements Serializable  {
    CH1(0),
    CH2(1),
    CH3(2),
    CH4(3),
    CH5(4),
    CH6(5),
    VRA(6),
    VRB(7);
    
    private final int value;
    
    private MixSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static MixSource fromInteger(int Integer){
        if (Integer > 7 || Integer < 0)
            throw new IllegalArgumentException("MixSource values is between 0 and 7!");
        switch (Integer){
            case 7:
                return VRB;
            case 6:
                return VRA;
            default:
                return MixSource.valueOf("CH"+(Integer+1));
        }
    }
}
