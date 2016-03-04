/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import com.shadows.hkprogrammer.core.utils.*;
import java.util.Arrays;

/**
 *
 * @author John
 */
public class MessageHandler {     
    private final ByteConvertHelper Converter = new ByteConvertHelper();
    private final ByteArrayHelper ArrayTools = new ByteArrayHelper();
    private byte[] BuildHeader(byte messageIdentifyHeader){
        byte Header = MessageHandlerConsts.HeaderBeggining;
        return new byte[]{ Header, messageIdentifyHeader };
    }
    
    private byte[] InitializeByteArray(int length,byte msgIdentifier){
        byte[] returnArray = new byte[length];        
        returnArray = ArrayTools.WriteToByteArray(
                returnArray,
                BuildHeader(msgIdentifier),
                0
        );
        return returnArray;
    }
    
    private byte[] CreateCheckSumToByteArray(byte[] array){
        byte[] payload = ArrayTools.ReadFromByteArray(array, 2, array.length - 4);
        byte[] Checksum = ArrayTools.ByteArrayCheckSumBySB(payload);
        return ArrayTools.WriteToByteArray(array,Checksum,2 + payload.length);
    }
    
    private byte[] WritePayloadOfPositionMessage(PositionValuesMessage message,byte[] messageBytes){
        byte[] payload = new byte[14];
        for (int i = 0; i < 6; i++) {
            ArrayTools.WriteToByteArray(
                payload, 
                Converter.IntegerToByteBySB(message.getChannelPositionInfo(i+1)),
                i*2
            );  
        }   
        ArrayTools.WriteToByteArray(
            payload, 
            Converter.IntegerToByteBySB(message.getFourthPseudo()),
            12
        );  
        return ArrayTools.WriteToByteArray(messageBytes,payload,2);
    }
    
    private byte[] WritePayloadOfParameterMessage(ParameterMessage message,byte[] messageBytes){
        byte[] payload = new byte[65];
        ArrayTools.WriteToByteArray(
                payload, 
                Converter.StringToByte(message.getTXModelType().name()), 
                0
        );
        for (int i = 0; i < 6; i++) {
            payload = ArrayTools.WriteToByteArray(
                payload, 
                Converter.IntegerToByteBySB(message.getChannelPositionInfo(i+1)),
                i*2
            );  
        }   
        payload = ArrayTools.WriteToByteArray(
            payload, 
            Converter.IntegerToByteBySB(message.getFourthPseudo()),
            12
        );  
        return ArrayTools.WriteToByteArray(messageBytes,payload,2);
    }
    
    private void ValidatePositionValuesMessageBytes(byte[] messageBytes){
        if (messageBytes.length != MessageHandlerConsts.msgPositionLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        byte[] MessageHeader = ArrayTools.ReadFromByteArray(messageBytes, 0, 2);
        if (!Arrays.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderPosition)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        byte[] Checksum = ArrayTools.ReadFromByteArray(messageBytes, messageBytes.length - 2, 2);
        byte[] Payload = ArrayTools.ReadFromByteArray(messageBytes, 2, messageBytes.length - 4);
        byte[] payloadChecksum = ArrayTools.ByteArrayCheckSumBySB(Payload);
        if (!Arrays.equals(Checksum,payloadChecksum))
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
    }
    
    private void ValidateParameterRequestMessageBytes(byte[] messageBytes){
        if (messageBytes.length != MessageHandlerConsts.msgParameterRequestLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        byte[] MessageHeader = ArrayTools.ReadFromByteArray(messageBytes, 0, 2);
        if (!Arrays.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterRequest)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        byte[] Payload = ArrayTools.ReadFromByteArray(messageBytes, 2, 1);
        byte[] payloadChecksum = new byte[] { (byte)0x00 };
        if (!Arrays.equals(Payload,payloadChecksum))
           throw new IllegalArgumentException("Message flag does not match 0!"); 
    }
    
    private PositionValuesMessage CreatePositionValuesMessageFromBytes(byte[] msgBytes){
        byte[] Payload = ArrayTools.ReadFromByteArray(msgBytes, 2, msgBytes.length - 4);
        byte[] fourthPseudoBytes = ArrayTools.ReadFromByteArray(Payload, 12, 2);
        PositionValuesMessage message = new PositionValuesMessage();
        for (int i = 0; i < 6; i++) {
            byte[] get = ArrayTools.ReadFromByteArray(Payload, i*2, 2);
            message.setChannelPositionInfo(i+1, Converter.ByteToInteger(get));
        }
        message.setFourthChannelPositionPseudo(Converter.ByteToInteger(fourthPseudoBytes));
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
        byte[] integerBytes = Converter.IntegerToByteBySB(message.getFlag());
        byte[] valueBytes = new byte[] { integerBytes[1] };
        returnArray = ArrayTools.WriteToByteArray(returnArray, valueBytes , 2);  
        return returnArray;
    }        
    
    public ParameterRequest GetParameterRequestMessageFromBytes(byte[] messageBytes){
        ValidateParameterRequestMessageBytes(messageBytes);
        ParameterRequest message = new ParameterRequest();
        return message;
    }
    
    public byte[] GetBytesForParameterDumpMessage(ParameterMessage message){
        byte[] returnArray = InitializeByteArray(
                MessageHandlerConsts.msgParameterRequestLength,
                MessageHandlerConsts.HeaderParameterDump
        );
        byte[] integerBytes = Converter.IntegerToByteBySB(message.getFlag());
        byte[] valueBytes = new byte[] { integerBytes[1] };
        returnArray = ArrayTools.WriteToByteArray(returnArray, valueBytes , 2);  
        return returnArray;
    }        
    
    public ParameterRequest GetParameterDumpMessageFromBytes(byte[] messageBytes){
        ValidateParameterRequestMessageBytes(messageBytes);
        ParameterRequest message = new ParameterRequest();
        return message;
    }
}
