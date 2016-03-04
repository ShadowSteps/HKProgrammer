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
public enum ControlChannel {
    CHANNEL1(0),
    CHANNEL2(1),
    CHANNEL3(2),
    CHANNEL4(3),
    CHANNEL5(4),
    CHANNEL6(5);
    
    private final int value;
    
    private ControlChannel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public ControlChannel fromInteger(int Integer){
        if (Integer > 5 || Integer < 0)
            throw new IllegalArgumentException("ControlChannel values ins between 0 and 5!");
        return ControlChannel.valueOf("Channel"+(Integer + 1));
    }

}
