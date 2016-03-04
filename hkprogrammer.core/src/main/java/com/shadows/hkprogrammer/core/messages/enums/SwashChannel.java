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
public enum SwashChannel {
    CHANNEL1(0),
    CHANNEL2(1),
    CHANNEL6(2);
    
    private final int value;
    
    private SwashChannel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public SwashChannel fromInteger(int Integer){
        switch (Integer){
            case 0:
                return CHANNEL1;
            case 1:
                return CHANNEL2;
            case 2:
                return CHANNEL6;
        }
        throw new IllegalArgumentException("SwashChannel channel value is between 0 and 2!");
    }
}
