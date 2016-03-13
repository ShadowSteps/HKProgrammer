/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
import com.shadows.hkprogrammer.core.messages.enums.ControlChannel;
import com.shadows.hkprogrammer.core.messages.enums.CraftType;
import com.shadows.hkprogrammer.core.messages.enums.DRChannel;
import com.shadows.hkprogrammer.core.messages.enums.HeliEndPoint;
import com.shadows.hkprogrammer.core.messages.enums.MixDestination;
import com.shadows.hkprogrammer.core.messages.enums.MixSource;
import com.shadows.hkprogrammer.core.messages.enums.MixSwitch;
import com.shadows.hkprogrammer.core.messages.enums.SwashChannel;
import com.shadows.hkprogrammer.core.messages.enums.SwitchFunction;
import com.shadows.hkprogrammer.core.messages.enums.SwitchType;
import com.shadows.hkprogrammer.core.messages.enums.TXModel;
import com.shadows.hkprogrammer.core.messages.enums.VRFunction;
import com.shadows.hkprogrammer.core.messages.enums.VRType;
import com.shadows.hkprogrammer.core.messages.values.*;
import com.shadows.hkprogrammer.core.utils.*;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author John
 */
public class MessageHandler {     
    private ByteArray BuildHeader(byte messageIdentifyHeader){
        byte Header = MessageHandlerConsts.HeaderBeggining;
        return ByteArray.FromByteArray(new byte[]{ Header, messageIdentifyHeader });
    }
    
    private ByteArray InitializeByteArray(int length,byte msgIdentifier){
        ByteArray returnArray = new ByteArray(length);        
        returnArray.Write(BuildHeader(msgIdentifier), 0);
        return returnArray;
    }
    
    private void CreateCheckSumToByteArray(ByteArray array){
        ByteArray payload = array.Read(2, array.Length() - 4);
        ByteArray Checksum = payload.ChecksumBySB();
        array.Write(Checksum, 2 + payload.Length());       
    }
    
    private void WritePayloadOfPositionMessage(PositionValuesMessage message,ByteArray messageBytes){
        ByteArray payload = new ByteArray(14);
        for (int i = 0; i < 6; i++) {
            payload.Write(
                message.getChannelPositionInfo(i+1),
                i*2,
                true
            );  
        }   
        payload.Write(
            message.getFourthPseudo(),
            12,
            true
        );  
        messageBytes.Write(payload,2);
        messageBytes.Write(payload.ChecksumBySB());
    }
    
    private void WritePayloadOfParameterMessage(ParameterMessage message,ByteArray messageBytes){
        ByteArray payload = new ByteArray(65);
        String Model = Integer.toBinaryString(message.getTXModelType().getValue()),
                Craft = Integer.toBinaryString(message.getCraftTypeNum().getValue());
        payload.Write(Integer.parseInt(StringUtils.leftPad(Model,4,"0")+StringUtils.leftPad(Craft,4,"0"), 2));
        String Bitmask = "";
        for (Boolean reverse:message.getReverseBitmask())
            Bitmask = (reverse ? 1 : 0) + Bitmask;
        Bitmask = "00"+Bitmask;
        payload.Write((byte)Integer.parseInt(Bitmask,2));
        for (ParameterDRValue DRValue : message.getDRValues()) {
            payload.Write((byte)DRValue.getOnValue());
            payload.Write((byte)DRValue.getOffValue());
        }        
        for (int Swash : message.getSwash())
            payload.Write((byte)Swash);        
        for (PotmeterEndPoint Endpoint : message.getEndPoints()){
            payload.Write((byte)Endpoint.getLeft());
            payload.Write((byte)Endpoint.getRigth());
        }
        for (ThrottleCurve Curve : message.getThrottleCurves()){
            payload.Write((byte)Curve.getNormal());
            payload.Write((byte)Curve.getID());
        }
        for (PitchCurve Curve : message.getPitchCurves()){
            payload.Write((byte)Curve.getNormal());
            payload.Write((byte)Curve.getID());
        }
        for (int Subtrim : message.getSubtrim())
            payload.Write((byte)Subtrim);
        for (MixSetting Mix : message.getMixes()){
            String Source = Integer.toBinaryString(Mix.getSource().getValue()),
                    Destination = Integer.toBinaryString(Mix.getDestination().getValue());
            payload.Write((byte)Integer.parseInt(
                    StringUtils.leftPad(Source,4,"0") + 
                        StringUtils.leftPad(Destination,4,"0"),
                    2)
            );           
            payload.Write((byte)Mix.getUprate());
            payload.Write((byte)Mix.getDownrate());
            payload.Write((byte)Mix.getSwitch().getValue());
        }
        for (SwitchFunction Switch : message.getSwitchFunction())
            payload.Write((byte)Switch.getValue());
        for (VRFunction VR : message.getVRModes())
            payload.Write((byte)VR.getValue());
        messageBytes.Write(payload);
        messageBytes.Write(payload.ChecksumBySB());
    }
    
    private void ValidatePositionValuesMessageBytes(ByteArray messageBytes){
        if (messageBytes.Length() != MessageHandlerConsts.msgPositionLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderPosition)))
            throw new IllegalArgumentException("Message header is different from expected!");
        ByteArray Checksum = messageBytes.Read(messageBytes.Length() - 2, 2);
        ByteArray Payload = messageBytes.Read(2, messageBytes.length - 4);
        ByteArray payloadChecksum = Payload.ChecksumBySB();
        if (!Objects.equals(Checksum,payloadChecksum))
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
    }    
    
    private void ValidateParameterRequestMessageBytes(ByteArray messageBytes){
        if (messageBytes.length != MessageHandlerConsts.msgParameterRequestLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterRequest)))
            throw new IllegalArgumentException("Message header is different from expected!");         
    }
    
    private void ValidateParameterDumpMessageBytes(ByteArray messageBytes){
        if (messageBytes.Length() != MessageHandlerConsts.msgParameterDumpLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterDump)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        ByteArray Checksum = messageBytes.Read(messageBytes.Length() - 2, 2);
        ByteArray Payload = messageBytes.Read(2, messageBytes.length - 4);
        ByteArray payloadChecksum = Payload.ChecksumBySB();
        if (!Objects.equals(Checksum,payloadChecksum))
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
    }
    
    private void ValidateParameterSetMessageBytes(ByteArray messageBytes){
        if (messageBytes.Length() != MessageHandlerConsts.msgParameterSetLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterSet)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
        ByteArray Checksum = messageBytes.Read(messageBytes.Length() - 2, 2);
        ByteArray Payload = messageBytes.Read(2, messageBytes.length - 4);
        ByteArray payloadChecksum = Payload.ChecksumBySB();
        if (!Objects.equals(Checksum,payloadChecksum))
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
    }
    
    
     private PositionValuesMessage CreatePositionValuesMessageFromBytes(ByteArray msgBytes){
        ByteArray Payload = msgBytes.Read(2, msgBytes.length - 4);
        ByteArray fourthPseudoBytes = Payload.Read(12, 2);
        PositionValuesMessage message = new PositionValuesMessage();
        for (int i = 0; i < 6; i++) {
            ByteArray get = Payload.Read(i*2, 2);
            message.setChannelPositionInfo(i+1, get.ToShort());
        }
        message.setFourthChannelPositionPseudo(fourthPseudoBytes.ToShort());
        return message;
    }
    
    private ParameterMessage CreateParameterMessageFromBytes(ByteArray msgBytes){
        ByteArray Payload = msgBytes.Read(2, msgBytes.length - 4);
        ParameterMessage message = new ParameterMessage();
        String BaseTypesValue = Integer.toBinaryString(Payload.Read(0, 1).ToByte());
        String BaseTypes = StringUtils.leftPad(BaseTypesValue, 8, '0');
        message.setTXModelType(
                TXModel.fromInteger(Integer.parseInt(BaseTypes.substring(0, 4),2))
        );
        String CraftTypeString = BaseTypes.substring(4, 8);
        message.setCraftTypeNum(
                CraftType.fromInteger(Integer.parseInt(CraftTypeString,2))
        );        
        for (int i = 0; i < 3; i++) {
            int onValue = Payload.Read(2+(i)*2, 1).ToByte(),
                    offValue = Payload.Read(3+(i)*2, 1).ToByte();
            message.setDRValueForChannel(DRChannel.fromInteger(i), onValue, offValue);
            int swash = Payload.Read(8 + i, 1).ToByte();
            message.setSwashValueForChannel(SwashChannel.fromInteger(i), swash);
            String mixCommunicationValue = Integer.toBinaryString(Payload.Read(49 + i*4, 1).ToByte());
            String mixCommunication = StringUtils.leftPad(mixCommunicationValue, 8, '0');
                    
            int mixUprate = Payload.Read(50 + i*4, 1).ToByte(),
                    mixDownrate = Payload.Read(51 + i*4, 1).ToByte(),
                    mixSwitch = Payload.Read(52 + i*4, 1).ToByte();
            message.setMixSettingsValue(
                    i+1, 
                    MixDestination.fromInteger(Integer.parseInt(mixCommunication.substring(4, 8), 2)), 
                    MixSource.fromInteger(Integer.parseInt(mixCommunication.substring(0, 4), 2)), 
                    MixSwitch.fromInteger(mixSwitch), 
                    mixDownrate, 
                    mixUprate
            );            
        }
        String ReverseBytes = Integer.toBinaryString(Payload.Read(1, 1).ToByte());       
        ReverseBytes = StringUtils.leftPad(ReverseBytes, 8, '0');
        for (int i = 0; i < 6; i++) {
            int endPointLeft = Payload.Read(11+(i)*2, 1).ToByte(),
                   endPointRight = Payload.Read(12+(i)*2, 1).ToByte();
            message.setEndPointValueForChannel(
                    ControlChannel.fromInteger(i), 
                    endPointLeft, 
                    endPointRight
            );
            message.setReverseBitmaskForChannel(
                    ControlChannel.fromInteger(i),
                    Integer.parseInt(ReverseBytes.substring(7-i, 8-i),2) == 1
                );
            int subtrim = Payload.Read(43+i, 1).ToByte();
            message.setSubtrimValueForChannel(ControlChannel.fromInteger(i), subtrim);
        }
        for (int i = 0; i < 5; i++) {
            byte throttleNormal = Payload.Read(23+(i)*2, 1).ToByte(),
                    throttleId = Payload.Read(24+(i)*2, 1).ToByte(),
                    pitchNormal = Payload.Read(33+(i)*2, 1).ToByte(),
                    pitchId = Payload.Read(34+(i)*2, 1).ToByte();
            message.setThrottleCurveValueForChannel(
                    HeliEndPoint.fromInteger(i), 
                    throttleNormal, 
                    throttleId
            );
            message.setPitchCurveValueForChannel(
                    HeliEndPoint.fromInteger(i), 
                    pitchNormal, 
                    pitchId
            );
        }
        for (int i = 0; i < 2; i++){
            int SwitchFunctionVal = Payload.Read(61 + i, 1).ToByte(),
                    VRFunctionVal = Payload.Read(63 + i, 1).ToByte();
            message.setSwitchFunction(
                    SwitchType.fromInteger(i), 
                    SwitchFunction.fromInteger(SwitchFunctionVal)
            );
            message.setVRFunction(
                    VRType.fromInteger(i), 
                    VRFunction.fromInteger(VRFunctionVal)
            );
        }
        return message;
    }
    
    public ByteArray GetBytesForPositionValuesMessage(PositionValuesMessage message){
        ByteArray returnArray = InitializeByteArray(
                MessageHandlerConsts.msgPositionLength,
                MessageHandlerConsts.HeaderPosition
        );
        WritePayloadOfPositionMessage(message, returnArray);        
        return returnArray;
    }        
    
    public PositionValuesMessage GetPositionValuesMessageFromBytes(ByteArray messageBytes){
        ValidatePositionValuesMessageBytes(messageBytes);       
        return CreatePositionValuesMessageFromBytes(messageBytes);
    }
    
    public ByteArray GetBytesForParameterRequestMessage(ParameterRequest message){
        ByteArray returnArray = InitializeByteArray(
                MessageHandlerConsts.msgParameterRequestLength,
                MessageHandlerConsts.HeaderParameterRequest
        );
        returnArray.Write(message.getFlag(), 2);
        return returnArray;
    }        
    
    public ParameterRequest GetParameterRequestMessageFromBytes(ByteArray messageBytes){
        ValidateParameterRequestMessageBytes(messageBytes);
        ParameterRequest message = new ParameterRequest();
        return message;
    }
    
    public ByteArray GetBytesForParameterDumpMessage(ParameterMessage message){
        ByteArray returnArray = InitializeByteArray(
                MessageHandlerConsts.msgParameterDumpLength,
                MessageHandlerConsts.HeaderParameterDump
        );
        WritePayloadOfParameterMessage(message, returnArray);
        return returnArray;
    }        
    
    public ParameterMessage GetParameterDumpMessageFromBytes(ByteArray messageBytes){
        ValidateParameterDumpMessageBytes(messageBytes);
        return CreateParameterMessageFromBytes(messageBytes);
    }    
    
    public ByteArray GetBytesForParameterSetMessage(ParameterMessage message){
        ByteArray returnArray = InitializeByteArray(
                MessageHandlerConsts.msgParameterSetLength,
                MessageHandlerConsts.HeaderParameterSet
        );
        WritePayloadOfParameterMessage(message, returnArray);
        return returnArray;
    }        
    
    public ParameterMessage GetParameterSetMessageFromBytes(ByteArray messageBytes){
        ValidateParameterSetMessageBytes(messageBytes);
        return CreateParameterMessageFromBytes(messageBytes);
    }    
}
