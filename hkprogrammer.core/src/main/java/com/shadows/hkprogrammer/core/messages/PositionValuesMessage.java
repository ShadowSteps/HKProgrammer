/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import com.shadows.hkprogrammer.core.ByteArrayHelper;
import com.shadows.hkprogrammer.core.ByteConvertHelper;
import java.util.Arrays;

/**
 *
 * @author John
 */
public class PositionValuesMessage extends SerialMessage {    
    private final int[] ChannelPositionInfo = { 1500, 1500, 1500, 1500, 1500, 1500 };
    private int fourthPseudo;
    
    public PositionValuesMessage() {
        super((byte)0xFC, 18);
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

    
    public void setChannelPositionInfo(int Channel,int Value) {
        CheckChannelValueRange(Value);
        this.ChannelPositionInfo[Channel - 1] = Value;
    }

    public void setFourthChannelPositionPseudo(int FourthChannelPositionPseudo) {
        this.fourthPseudo = FourthChannelPositionPseudo;
    }    
    
    private void CheckChannelValueRange(int Value){
        if (Value < 1000||Value > 2000)
            throw new IllegalArgumentException("Value must be between 1000 and 2000 for Pot-position!");        
    }
    
    @Override
    protected byte[] GetPayloadToByteArray() {
        byte[] payload = new byte[14];
        for (int i = 0; i < ChannelPositionInfo.length; i++) {
            payload = ByteArrayHelper.WriteToByteArray(
                payload, 
                ByteConvertHelper.IntegerToByteBySB(this.ChannelPositionInfo[i]),
                i*2
            );  
        }   
        payload = ByteArrayHelper.WriteToByteArray(
            payload, 
            ByteConvertHelper.IntegerToByteBySB(this.fourthPseudo),
            12
        );  
        return payload;
    }

    @Override
    protected void GetVariablesFromPayload(byte[] Payload) {  
        byte[] fourthPseudoBytes = ByteArrayHelper.ReadFromByteArray(Payload, 12, 2);
        for (int i = 0; i < this.ChannelPositionInfo.length; i++) {
            byte[] get = ByteArrayHelper.ReadFromByteArray(Payload, i*2, 2);
            this.setChannelPositionInfo(i+1, ByteConvertHelper.ByteToInteger(get));
        }
        this.setFourthChannelPositionPseudo(ByteConvertHelper.ByteToInteger(fourthPseudoBytes));
    }   
    
}
