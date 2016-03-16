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
public enum TXModel implements Serializable {
    Model1(0),
    Model2(1),
    Model3(2),
    Model4(3);
    
    private final int value;
    
    private TXModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static TXModel fromInteger(int Integer){        
         if (Integer > 3 || Integer < 0)
            throw new IllegalArgumentException("TXModel values is between 0 and 3!");
         return TXModel.valueOf("Model"+(Integer + 1));
    }
}
