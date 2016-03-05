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
public enum HeliEndPoint {
    EP0(0),
    EP1(1),
    EP2(2),
    EP3(3),
    EP4(4);
    
    private final int value;
    
    private HeliEndPoint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static HeliEndPoint fromInteger(int Integer){
        if (Integer > 4 || Integer < 0)
            throw new IllegalArgumentException("HeliEndPoint values are between 0 and 4!");
        return HeliEndPoint.valueOf("EP"+(Integer));
    }
}
