/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import com.shadows.hkprogrammer.core.utils.ByteArrayHelper;
import com.shadows.hkprogrammer.core.utils.ByteConvertHelper;
import java.util.Arrays;

/**
 *
 * @author John
 */
public class MessageHandler {     
    private byte[] BuildHeader(byte messageIdentifyHeader){
        byte Header = MessageHandlerConsts.HeaderBeggining;
        return new byte[]{ Header, messageIdentifyHeader };
    }
    
    private byte[] InitializeByteArray(int length,byte msgIdentifier){
        byte[] returnArray = new byte[length];        
        returnArray = ByteArrayHelper.WriteToByteArray(
                returnArray,
                BuildHeader(msgIdentifier),
                0
        );
        return returnArray;
    }
    
    private byte[] CreateCheckSumToByteArray(byte[] array){
        byte[] payload = ByteArrayHelper.ReadFromByteArray(array, 2, array.length - 4);
        byte[] Checksum = ByteArrayHelper.ByteArrayCheckSumBySB(payload);
        return ByteArrayHelper.WriteToByteArray(array,Checksum,2 + payload.length);
    }
    
    private byte[] WritePayloadOfPositionMessage(PositionValuesMessage message,byte[] messageBytes){
        byte[] payload = new byte[14];
        for (int i = 0; i < 6; i++) {
            payload = ByteArrayHelper.WriteToByteArray(
                payload, 
                ByteConvertHelper.IntegerToByteBySB(message.getChannelPositionInfo(i+1)),
                i*2
            );  
        }   
        payload = ByteArrayHelper.WriteToByteArray(
            payload, 
            ByteConvertHelper.IntegerToByteBySB(message.getFourthPseudo()),
            12
        );  
        return ByteArrayHelper.WriteToByteArray(messageBytes,payload,2);
    }
    
    private void ValidatePositionValuesMessageBytes(byte[] messageBytes){
        if (messageBytes.length != MessageHandlerConsts.msgPositionLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        byte[] MessageHeader = ByteArrayHelper.ReadFromByteArray(messageBytes, 0, 2);
        if (!Arrays.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderPosition)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        byte[] Checksum = ByteArrayHelper.ReadFromByteArray(messageBytes, messageBytes.length - 2, 2);
        byte[] Payload = ByteArrayHelper.ReadFromByteArray(messageBytes, 2, messageBytes.length - 4);
        byte[] payloadChecksum = ByteArrayHelper.ByteArrayCheckSumBySB(Payload);
        if (!Arrays.equals(Checksum,payloadChecksum))
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
    }
    
    private void ValidateParameterRequestMessageBytes(byte[] messageBytes){
        if (messageBytes.length != MessageHandlerConsts.msgParameterRequestLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        byte[] MessageHeader = ByteArrayHelper.ReadFromByteArray(messageBytes, 0, 2);
        if (!Arrays.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterRequest)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        byte[] Payload = ByteArrayHelper.ReadFromByteArray(messageBytes, 2, 1);
        byte[] payloadChecksum = new byte[] { (byte)0x00 };
         if (!Arrays.equals(Payload,payloadChecksum))
            throw new IllegalArgumentException("Message flag does not match 0!"); 
    }
    
    private PositionValuesMessage CreatePositionValuesMessageFromBytes(byte[] msgBytes){
        byte[] Payload = ByteArrayHelper.ReadFromByteArray(msgBytes, 2, msgBytes.length - 4);
        byte[] fourthPseudoBytes = ByteArrayHelper.ReadFromByteArray(Payload, 12, 2);
        PositionValuesMessage message = new PositionValuesMessage();
        for (int i = 0; i < 6; i++) {
            byte[] get = ByteArrayHelper.ReadFromByteArray(Payload, i*2, 2);
            message.setChannelPositionInfo(i+1, ByteConvertHelper.ByteToInteger(get));
        }
        message.setFourthChannelPositionPseudo(ByteConvertHelper.ByteToInteger(fourthPseudoBytes));
        return message;
    }
    
    public byte[] GetBytesForPositionValuesMessage(PositionValuesMessage message){
        byte[] returnArray = InitializeByteArray(
                MessageHandlerConsts.msgPositionLength,
                MessageHandlerConsts.HeaderPosition
        );
        returnArray = WritePayloadOfPositionMessage(message, returnArray);        
        returnArray = CreateCheckSumToByteArray(returnArray);
        return returnArray;
    }        
    
    public PositionValuesMessage GetPositionValuesMessageFromBytes(byte[] messageBytes){
        ValidatePositionValuesMessageBytes(messageBytes);       
        return CreatePositionValuesMessageFromBytes(messageBytes);
    }
    
    public byte[] GetBytesForParameterRequestMessage(ParameterRequest message){
        byte[] returnArray = InitializeByteArray(
                MessageHandlerConsts.msgParameterRequestLength,
                MessageHandlerConsts.HeaderParameterRequest
        );
        byte[] integerBytes = ByteConvertHelper.IntegerToByteBySB(message.getFlag());
        byte[] valueBytes = new byte[] { integerBytes[1] };
        returnArray = ByteArrayHelper.WriteToByteArray(returnArray, valueBytes , 2);  
        return returnArray;
    }        
    
    public ParameterRequest GetParameterRequestMessageFromBytes(byte[] messageBytes){
        ValidateParameterRequestMessageBytes(messageBytes);
        ParameterRequest message = new ParameterRequest();
        return message;
    }
}
