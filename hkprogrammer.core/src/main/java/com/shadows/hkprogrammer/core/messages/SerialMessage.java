/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import com.shadows.hkprogrammer.core.ByteArrayHelper;
import com.shadows.hkprogrammer.core.ByteConvertHelper;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;

/**
 *
 * @author John
 */
public abstract class SerialMessage {
    protected final byte messagesHeaderBeggining = (byte)0x55;
    protected final byte messageHeaderEnd;
    protected final int messagelength;
    
    public SerialMessage(byte messageHeaderEnd, int messagelength) {
        this.messageHeaderEnd = messageHeaderEnd;
        this.messagelength = messagelength;
    }
    
    private final byte[] GetHeader(){
        return new byte[]{messagesHeaderBeggining , messageHeaderEnd};
    }   
    
    public byte[] ToByteArray(){
        byte[] returnArray = new byte[messagelength];
        returnArray = ByteArrayHelper.WriteToByteArray(returnArray,GetHeader(),0);
        byte[] payload = GetPayloadToByteArray();
        returnArray = ByteArrayHelper.WriteToByteArray(returnArray,payload,2); 
        byte[] Checksum = ByteArrayHelper.ByteArrayCheckSumBySB(payload);
        returnArray = ByteArrayHelper.WriteToByteArray(returnArray,Checksum,2 + payload.length); 
        return returnArray;
    }
    public void FromByteArray(byte[] Message){
        if (Message.length != messagelength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        byte[] MessageHeader = ByteArrayHelper.ReadFromByteArray(Message, 0, 2);
        if (MessageHeader != GetHeader())
            throw new IllegalArgumentException("Message header is different from expected!"); 
        byte[] Checksum = ByteArrayHelper.ReadFromByteArray(Message, Message.length - 2, 2);
        byte[] Payload = ByteArrayHelper.ReadFromByteArray(Message, 3, Message.length - 4);
        byte[] payloadChecksum = ByteArrayHelper.ByteArrayCheckSumBySB(Payload);
        if (Checksum != payloadChecksum)
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
        GetVariablesFromPayload(Payload);
    }

    protected abstract byte[] GetPayloadToByteArray();
    protected abstract void GetVariablesFromPayload(byte[] Payload);
    
}
