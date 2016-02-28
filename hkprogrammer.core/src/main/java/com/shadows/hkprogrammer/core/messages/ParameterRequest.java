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
    private final int flag = 0;

    public int getFlag() {
        return flag;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.flag;
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
