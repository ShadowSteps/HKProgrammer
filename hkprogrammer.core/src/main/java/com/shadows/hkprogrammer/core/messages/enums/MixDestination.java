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
public enum MixDestination {
    CH1(0),
    CH2(1),
    CH3(2),
    CH4(3),
    CH5(4),
    CH6(5);
    
    private final int value;
    
    private MixDestination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static MixDestination fromInteger(int Integer){
        if (Integer > 5 || Integer < 0)
            throw new IllegalArgumentException("MixSource values is between 0 and 5!");
        return MixDestination.valueOf("CH"+(Integer+1));
    }
}
