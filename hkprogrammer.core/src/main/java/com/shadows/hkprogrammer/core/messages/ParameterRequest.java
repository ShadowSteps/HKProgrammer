/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

/**
 *
 * @author John
 */
public class ParameterRequest {
    private final boolean flag = false;

    public boolean getFlag() {
        return flag;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.flag ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParameterRequest other = (ParameterRequest) obj;
        if (this.flag != other.flag) {
            return false;
        }
        return true;
    }

    
    
    
}
