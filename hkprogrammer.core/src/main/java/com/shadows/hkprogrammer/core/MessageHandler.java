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
    }
    
    private void WritePayloadOfParameterMessage(ParameterMessage message,ByteArray messageBytes){
        ByteArray payload = new ByteArray(65);
        String Model = Integer.toBinaryString(message.getTXModelType().getValue()),
                Craft = Integer.toBinaryString(message.getCraftTypeNum().getValue());
        payload.Write(Integer.parseInt(Model+Craft, 2));
        payload.Write(message.isReverseBitmask());
        for (ParameterDRValue DRValue : message.getDRValues()) {
            payload.Write(DRValue.getOnValue());
            payload.Write(DRValue.getOffValue());
        }        
        for (int Swash : message.getSwash())
            payload.Write(Swash);        
        for (PotmeterEndPoint Endpoint : message.getEndPoints()){
            payload.Write(Endpoint.getLeft());
            payload.Write(Endpoint.getRigth());
        }
        for (ThrottleCurve Curve : message.getThrottleCurves()){
            payload.Write(Curve.getNormal());
            payload.Write(Curve.getID());
        }
        for (PitchCurve Curve : message.getPitchCurves()){
            payload.Write(Curve.getNormal());
            payload.Write(Curve.getID());
        }
        for (int Subtrim : message.getSubtrim())
            payload.Write(Subtrim);
        for (MixSetting Mix : message.getMixes()){
            String Source = Integer.toBinaryString(Mix.getSource().getValue()),
                    Destination = Integer.toBinaryString(Mix.getDestination().getValue());
            payload.Write(Integer.parseInt(Source + Destination, 2));           
            payload.Write(Mix.getUprate());
            payload.Write(Mix.getDownrate());
            payload.Write(Mix.getSwitch().getValue());
        }
        for (SwitchFunction Switch : message.getSwitchFunction())
            payload.Write(Switch.getValue());
        for (VRFunction VR : message.getVRModes())
            payload.Write(VR.getValue());
        ByteArray Checksum = payload.ChecksumBySB();
        messageBytes.Write(payload);
        messageBytes.Write(Checksum);
    }
    
    private void ValidatePositionValuesMessageBytes(ByteArray messageBytes){
        if (messageBytes.Length() != MessageHandlerConsts.msgPositionLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderPosition)))
            throw new IllegalArgumentException("Message header is different from expected!");         
    }
    
    private int CreatePositionValuesMessageChecksum(PositionValuesMessage message){
        int Checksum = 0;
        for (int i = 0; i < 6; i++) {
            Checksum+=
                message.getChannelPositionInfo(i+1);
        }   
        Checksum += message.getFourthPseudo();
        return Checksum;
    }
    
    private int CreateParameterValuesMessageChecksum(ParameterMessage message)
    {
        int Checksum = 0;
        String Model = Integer.toBinaryString(message.getTXModelType().getValue()),
                Craft = Integer.toBinaryString(message.getCraftTypeNum().getValue());
        Checksum+=(Integer.parseInt(Model+Craft, 2));
        Checksum+=(message.isReverseBitmask() ? 1 : 0);
        for (ParameterDRValue DRValue : message.getDRValues()) {
            Checksum+=(DRValue.getOnValue());
            Checksum+=(DRValue.getOffValue());
        }        
        for (int Swash : message.getSwash())
            Checksum+=(Swash);        
        for (PotmeterEndPoint Endpoint : message.getEndPoints()){
            Checksum+=(Endpoint.getLeft());
            Checksum+=(Endpoint.getRigth());
        }
        for (ThrottleCurve Curve : message.getThrottleCurves()){
            Checksum+=(Curve.getNormal());
            Checksum+=(Curve.getID());
        }
        for (PitchCurve Curve : message.getPitchCurves()){
            Checksum+=(Curve.getNormal());
            Checksum+=(Curve.getID());
        }
        for (int Subtrim : message.getSubtrim())
            Checksum+=(Subtrim);
        for (MixSetting Mix : message.getMixes()){
            String Source = Integer.toBinaryString(Mix.getSource().getValue()),
                    Destination = Integer.toBinaryString(Mix.getDestination().getValue());
            Checksum+=(Integer.parseInt(Source + Destination, 2));           
            Checksum+=(Mix.getUprate());
            Checksum+=(Mix.getDownrate());
            Checksum+=(Mix.getSwitch().getValue());
        }
        for (SwitchFunction Switch : message.getSwitchFunction())
            Checksum+=(Switch.getValue());
        for (VRFunction VR : message.getVRModes())
            Checksum+=(VR.getValue());
        return Checksum;
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
    }
    
    private void ValidateParameterSetMessageBytes(ByteArray messageBytes){
        if (messageBytes.Length() != MessageHandlerConsts.msgParameterSetLength)
            throw new IllegalArgumentException("Message length is more then allowed for this type of message!");
        ByteArray MessageHeader = messageBytes.Read(0, 2);
        if (!Objects.equals(MessageHeader, BuildHeader(MessageHandlerConsts.HeaderParameterSet)))
            throw new IllegalArgumentException("Message header is different from expected!"); 
    }
    
    
    private PositionValuesMessage CreatePositionValuesMessageFromBytes(ByteArray msgBytes){
        ByteArray Payload = msgBytes.Read(2, msgBytes.length - 4);
        ByteArray fourthPseudoBytes = Payload.Read(12, 2);
        PositionValuesMessage message = new PositionValuesMessage();
        for (int i = 0; i < 6; i++) {
            ByteArray get = Payload.Read(i*2, 2);
            message.setChannelPositionInfo(i+1, get.ToInteger());
        }
        message.setFourthChannelPositionPseudo(fourthPseudoBytes.ToInteger());
        return message;
    }
    
    private ParameterMessage CreateParameterMessageFromBytes(ByteArray msgBytes){
        ByteArray Payload = msgBytes.Read(2, msgBytes.length - 4);
        ParameterMessage message = new ParameterMessage();
        String BaseTypesValue = Integer.toBinaryString(Payload.Read(0, 1).ToInteger());
        String BaseTypes = StringUtils.leftPad(BaseTypesValue, 8, '0');
        message.setTXModelType(
                TXModel.fromInteger(Integer.parseInt(BaseTypes.substring(0, 4)))
        );
        message.setCraftTypeNum(
                CraftType.fromInteger(Integer.parseInt(BaseTypes.substring(4, 8)))
        );
        message.setReverseBitmask(Payload.Read(1, 1).ToBoolean());        
        for (int i = 0; i < 3; i++) {
            int onValue = Payload.Read(2+(i)*2, 1).ToInteger(),
                    offValue = Payload.Read(3+(i)*2, 1).ToInteger();
            message.setDRValueForChannel(DRChannel.fromInteger(i), onValue, offValue);
            int swash = Payload.Read(8 + i, 1).ToInteger();
            message.setSwashValueForChannel(SwashChannel.fromInteger(i), swash);
            String mixCommunicationValue = Integer.toBinaryString(Payload.Read(49 + i*4, 1).ToInteger());
            String mixCommunication = StringUtils.leftPad(mixCommunicationValue, 8, '0');
                    
            int mixUprate = Payload.Read(50 + i*4, 1).ToInteger(),
                    mixDownrate = Payload.Read(51 + i*4, 1).ToInteger(),
                    mixSwitch = Payload.Read(52 + i*4, 1).ToInteger();
            message.setMixSettingsValue(
                    i+1, 
                    MixDestination.fromInteger(Integer.parseInt(mixCommunication.substring(4, 8), 2)), 
                    MixSource.fromInteger(Integer.parseInt(mixCommunication.substring(0, 4), 2)), 
                    MixSwitch.fromInteger(mixSwitch), 
                    mixDownrate, 
                    mixUprate
            );            
        }
        for (int i = 0; i < 6; i++) {
            int endPointLeft = Payload.Read(11+(i)*2, 1).ToInteger(),
                   endPointRight = Payload.Read(12+(i)*2, 1).ToInteger();
            message.setEndPointValueForChannel(
                    ControlChannel.fromInteger(i), 
                    endPointLeft, 
                    endPointRight
            );
            int subtrim = Payload.Read(43+i, 1).ToInteger();
            message.setSubtrimValueForChannel(ControlChannel.fromInteger(i), subtrim);
        }
        for (int i = 0; i < 5; i++) {
            int throttleNormal = Payload.Read(23+(i)*2, 1).ToInteger(),
                    throttleId = Payload.Read(24+(i)*2, 1).ToInteger(),
                    pitchNormal = Payload.Read(33+(i)*2, 1).ToInteger(),
                    pitchId = Payload.Read(34+(i)*2, 1).ToInteger();
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
            int SwitchFunctionVal = Payload.Read(61 + i, 1).ToInteger(),
                    VRFunctionVal = Payload.Read(63 + i, 1).ToInteger();
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
        int CheckSum = CreatePositionValuesMessageChecksum(message);
        returnArray.Write(CheckSum, 16, true);
        return returnArray;
    }        
    
    public PositionValuesMessage GetPositionValuesMessageFromBytes(ByteArray messageBytes){
        ValidatePositionValuesMessageBytes(messageBytes);       
        PositionValuesMessage message = CreatePositionValuesMessageFromBytes(messageBytes);
        ByteArray checkSumFromMessage = messageBytes.Read(16, 2);
        int CheckSum = CreatePositionValuesMessageChecksum(message);
        if (checkSumFromMessage.ToInteger()!=CheckSum)
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
        return message;
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
        ParameterMessage message = CreateParameterMessageFromBytes(messageBytes);
        ByteArray checkSumFromMessage = messageBytes.Read(67, 2);
        int CheckSum = CreateParameterValuesMessageChecksum(message);
        if (checkSumFromMessage.ToInteger()!=CheckSum)
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
        return message;
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
        ParameterMessage message = CreateParameterMessageFromBytes(messageBytes);
        ByteArray checkSumFromMessage = messageBytes.Read(67, 2);
        int CheckSum = CreateParameterValuesMessageChecksum(message);
        if (checkSumFromMessage.ToInteger()!=CheckSum)
            throw new IllegalArgumentException("Message checksum does not match payload!"); 
        return message;
    }    
}
