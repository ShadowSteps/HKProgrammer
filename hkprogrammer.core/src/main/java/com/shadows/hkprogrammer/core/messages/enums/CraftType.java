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
public enum CraftType implements Serializable {
    Acro(0),
    heli120(1),
    heli90(2),
    heli140(3);
    
    private final int value;
    
    private CraftType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static CraftType fromInteger(int Integer){        
        switch(Integer){
            case 0:
                return Acro;
            case 1:
                return heli120;
            case 2:
                return heli90;
            case 3:
                return heli140;
        }
        throw new IllegalArgumentException("CraftType values are between 0 and 3!");
    }
}
