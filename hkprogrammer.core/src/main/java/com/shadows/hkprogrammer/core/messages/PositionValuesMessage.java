/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import java.util.Arrays;

/**
 *
 * @author John
 */
public class PositionValuesMessage{    
    private final short[] ChannelPositionInfo = { 1500, 1500, 1500, 1500, 1500, 1500 };
    private short fourthPseudo = 500;   

    public int getFourthPseudo() {
        return fourthPseudo;
    }
    
    public void setChannelPositionInfo(int Channel,short Value) {
        CheckChannelValueRange(Value);
        this.ChannelPositionInfo[Channel - 1] = Value;
    }
    
    public int getChannelPositionInfo(int Channel) {
        return this.ChannelPositionInfo[Channel - 1];
    }

    public void setFourthChannelPositionPseudo(short FourthChannelPositionPseudo) {
        this.fourthPseudo = FourthChannelPositionPseudo;
    }    
    
    private void CheckChannelValueRange(short Value){
        if (Value < 1000||Value > 2000)
            throw new IllegalArgumentException("Value must be between 1000 and 2000 for Pot-position!");        
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Arrays.hashCode(this.ChannelPositionInfo);
        hash = 83 * hash + this.fourthPseudo;
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
        final PositionValuesMessage other = (PositionValuesMessage) obj;
        if (!Arrays.equals(this.ChannelPositionInfo, other.ChannelPositionInfo)) {
            return false;
        }
        if (this.fourthPseudo != other.fourthPseudo) {
            return false;
        }
        return true;
    }   
}
