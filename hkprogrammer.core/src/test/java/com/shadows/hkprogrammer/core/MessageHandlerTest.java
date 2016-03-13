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
import com.shadows.hkprogrammer.core.utils.ByteArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John
 */
public class MessageHandlerTest {
    
    public MessageHandlerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of GetBytesForPositionValuesMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForPositionValuesMessage() {
        System.out.println("GetBytesForPositionValuesMessage");
        PositionValuesMessage message = new PositionValuesMessage();
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderPosition,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            1, -12,
            6, 59
        });
        ByteArray result = instance.GetBytesForPositionValuesMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetPositionValuesMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetPositionValuesMessageFromBytes() {
        System.out.println("GetPositionValuesMessageFromBytes");
        ByteArray messageBytes = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderPosition,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            5, -36,
            1, -12,
            6, 59
        });
        MessageHandler instance = new MessageHandler();
        PositionValuesMessage expResult = new PositionValuesMessage();
        PositionValuesMessage result = instance.GetPositionValuesMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetBytesForParameterRequestMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterRequestMessage() {
        System.out.println("GetBytesForParameterRequestMessage");
        ParameterRequest message = new ParameterRequest();
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterRequest,
            0
        });
        ByteArray result = instance.GetBytesForParameterRequestMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetParameterRequestMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterRequestMessageFromBytes() {
        System.out.println("GetParameterRequestMessageFromBytes");
        ByteArray messageBytes = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterRequest,
            0
        });
        MessageHandler instance = new MessageHandler();
        ParameterRequest expResult = new ParameterRequest();
        ParameterRequest result = instance.GetParameterRequestMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetBytesForParameterDumpMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterDumpMessage() {
        System.out.println("GetBytesForParameterDumpMessage");
        ParameterMessage message = new ParameterMessage();        
        message.setCraftTypeNum(CraftType.heli90);
        message.setDRValueForChannel(DRChannel.CHANNEL1, 0, 0);
        message.setDRValueForChannel(DRChannel.CHANNEL2, 0, 0);
        message.setDRValueForChannel(DRChannel.CHANNEL4, 0, 0);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL1, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL2, 95, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL3, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL4, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL5, 95, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL6, 120, 120);
        message.setMixSettingsValue(1, MixDestination.CH5, MixSource.CH1, MixSwitch.ON, 0, -100);
        message.setMixSettingsValue(2, MixDestination.CH2, MixSource.CH1, MixSwitch.ON, 85, -5);
        message.setMixSettingsValue(3, MixDestination.CH5, MixSource.CH2, MixSwitch.SWA, 100, 100);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP0, (byte)2, (byte)0);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP1, (byte)4, (byte)-89);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP2, (byte)85, (byte)-4);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP3, (byte)5, (byte)-21);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP4, (byte)5, (byte)-18);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL1,true);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL2,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL3,true);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL4,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL5,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL6,false);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL1, 23);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL2, 0);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL3, 20);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL4, 10);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL5, -18);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL6, -100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL1, 100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL2, 100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL6, 100);
        message.setSwitchFunction(SwitchType.SwitchA, SwitchFunction.Unassigned);
        message.setSwitchFunction(SwitchType.SwitchB, SwitchFunction.ThrottleCut);
        message.setTXModelType(TXModel.Model2);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP0, (byte)120, (byte)-18);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP1, (byte)120, (byte)14);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP2, (byte)120, (byte)-45);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP3, (byte)120, (byte)-24);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP4, (byte)120, (byte)-24);
        message.setVRFunction(VRType.VRA, VRFunction.Unassigned);
        message.setVRFunction(VRType.VRB, VRFunction.PitchAdjust);
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterDump,
            18, //TXModel/CraftType
            5, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            100, 100, 100, //Swash
            120, 120, //Endpoint CH1
            95, 120, //Endpoint CH2
            120, 120, //Endpoint CH3
            120, 120, //Endpoint CH4
            95, 120, //Endpoint CH5
            120, 120, //Endpoint CH6
            120, -18, //Throttle EP0
            120, 14, //Throttle EP1
            120, -45, //Throttle EP2
            120, -24, //Throttle EP3
            120, -24, //Throttle EP4
            2, 0, //Pitch EP0
            4, -89, //Pitch EP1
            85, -4, //Pitch EP2
            5, -21, //Pitch EP3
            5, -18, //Pitch EP4
            23, 0, 20, 10, -18, -100, // Subtrim
            4, -100, 0, 2, // Mix 1
            1, -5, 85, 2, // Mix 2
            20, 100, 100, 0, // Mix 3
            0, 2, //Switch function
            0, 1, //VR functions
            21, 28//Checksum
        });
        ByteArray result = instance.GetBytesForParameterDumpMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetParameterDumpMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterDumpMessageFromBytes() {
        System.out.println("GetParameterDumpMessageFromBytes");
        ByteArray messageBytes = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterDump,
            18, //TXModel/CraftType
            5, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            100, 100, 100, //Swash
            120, 120, //Endpoint CH1
            95, 120, //Endpoint CH2
            120, 120, //Endpoint CH3
            120, 120, //Endpoint CH4
            95, 120, //Endpoint CH5
            120, 120, //Endpoint CH6
            120, -18, //Throttle EP0
            120, 14, //Throttle EP1
            120, -45, //Throttle EP2
            120, -24, //Throttle EP3
            120, -24, //Throttle EP4
            2, 0, //Pitch EP0
            4, -89, //Pitch EP1
            85, -4, //Pitch EP2
            5, -21, //Pitch EP3
            5, -18, //Pitch EP4
            23, 0, 20, 10, -18, -100, // Subtrim
            4, -100, 0, 2, // Mix 1
            1, -5, 85, 2, // Mix 2
            20, 100, 100, 0, // Mix 3
            0, 2, //Switch function
            0, 1, //VR functions
            21, 28//Checksum
        });
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = new ParameterMessage();
        expResult.setCraftTypeNum(CraftType.heli90);
        expResult.setDRValueForChannel(DRChannel.CHANNEL1, 0, 0);
        expResult.setDRValueForChannel(DRChannel.CHANNEL2, 0, 0);
        expResult.setDRValueForChannel(DRChannel.CHANNEL4, 0, 0);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL1, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL2, 95, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL3, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL4, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL5, 95, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL6, 120, 120);
        expResult.setMixSettingsValue(1, MixDestination.CH5, MixSource.CH1, MixSwitch.ON, 0, -100);
        expResult.setMixSettingsValue(2, MixDestination.CH2, MixSource.CH1, MixSwitch.ON, 85, -5);
        expResult.setMixSettingsValue(3, MixDestination.CH5, MixSource.CH2, MixSwitch.SWA, 100, 100);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP0, (byte)2, (byte)0);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP1, (byte)4, (byte)-89);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP2, (byte)85, (byte)-4);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP3, (byte)5, (byte)-21);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP4, (byte)5, (byte)-18);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL1,true);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL2,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL3,true);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL4,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL5,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL6,false);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL1, 23);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL2, 0);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL3, 20);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL4, 10);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL5, -18);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL6, -100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL1, 100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL2, 100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL6, 100);
        expResult.setSwitchFunction(SwitchType.SwitchA, SwitchFunction.Unassigned);
        expResult.setSwitchFunction(SwitchType.SwitchB, SwitchFunction.ThrottleCut);
        expResult.setTXModelType(TXModel.Model2);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP0, (byte)120, (byte)-18);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP1, (byte)120, (byte)14);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP2, (byte)120, (byte)-45);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP3, (byte)120, (byte)-24);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP4, (byte)120, (byte)-24);
        expResult.setVRFunction(VRType.VRA, VRFunction.Unassigned);
        expResult.setVRFunction(VRType.VRB, VRFunction.PitchAdjust);
        ParameterMessage result = instance.GetParameterDumpMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetBytesForParameterSetMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterSetMessage() {
        System.out.println("GetBytesForParameterSetMessage");
        ParameterMessage message = new ParameterMessage();
        message.setCraftTypeNum(CraftType.heli90);
        message.setDRValueForChannel(DRChannel.CHANNEL1, 0, 0);
        message.setDRValueForChannel(DRChannel.CHANNEL2, 0, 0);
        message.setDRValueForChannel(DRChannel.CHANNEL4, 0, 0);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL1, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL2, 95, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL3, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL4, 120, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL5, 95, 120);
        message.setEndPointValueForChannel(ControlChannel.CHANNEL6, 120, 120);
        message.setMixSettingsValue(1, MixDestination.CH5, MixSource.CH1, MixSwitch.ON, 0, -100);
        message.setMixSettingsValue(2, MixDestination.CH2, MixSource.CH1, MixSwitch.ON, 85, -5);
        message.setMixSettingsValue(3, MixDestination.CH5, MixSource.CH2, MixSwitch.SWA, 100, 100);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP0, (byte)2, (byte)0);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP1, (byte)4, (byte)-89);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP2, (byte)85, (byte)-4);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP3, (byte)5, (byte)-21);
        message.setPitchCurveValueForChannel(HeliEndPoint.EP4, (byte)5, (byte)-18);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL1,true);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL2,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL3,true);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL4,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL5,false);
        message.setReverseBitmaskForChannel(ControlChannel.CHANNEL6,false);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL1, 23);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL2, 0);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL3, 20);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL4, 10);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL5, -18);
        message.setSubtrimValueForChannel(ControlChannel.CHANNEL6, -100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL1, 100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL2, 100);
        message.setSwashValueForChannel(SwashChannel.CHANNEL6, 100);
        message.setSwitchFunction(SwitchType.SwitchA, SwitchFunction.Unassigned);
        message.setSwitchFunction(SwitchType.SwitchB, SwitchFunction.ThrottleCut);
        message.setTXModelType(TXModel.Model2);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP0, (byte)120, (byte)-18);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP1, (byte)120, (byte)14);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP2, (byte)120, (byte)-45);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP3, (byte)120, (byte)-24);
        message.setThrottleCurveValueForChannel(HeliEndPoint.EP4, (byte)120, (byte)-24);
        message.setVRFunction(VRType.VRA, VRFunction.Unassigned);
        message.setVRFunction(VRType.VRB, VRFunction.PitchAdjust);
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterSet,
            18, //TXModel/CraftType
            5, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            100, 100, 100, //Swash
            120, 120, //Endpoint CH1
            95, 120, //Endpoint CH2
            120, 120, //Endpoint CH3
            120, 120, //Endpoint CH4
            95, 120, //Endpoint CH5
            120, 120, //Endpoint CH6
            120, -18, //Throttle EP0
            120, 14, //Throttle EP1
            120, -45, //Throttle EP2
            120, -24, //Throttle EP3
            120, -24, //Throttle EP4
            2, 0, //Pitch EP0
            4, -89, //Pitch EP1
            85, -4, //Pitch EP2
            5, -21, //Pitch EP3
            5, -18, //Pitch EP4
            23, 0, 20, 10, -18, -100, // Subtrim
            4, -100, 0, 2, // Mix 1
            1, -5, 85, 2, // Mix 2
            20, 100, 100, 0, // Mix 3
            0, 2, //Switch function
            0, 1, //VR functions
            21, 28//Checksum
        });
        ByteArray result = instance.GetBytesForParameterSetMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetParameterSetMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterSetMessageFromBytes() {
        System.out.println("GetParameterSetMessageFromBytes");
        ByteArray messageBytes = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterSet,
            18, //TXModel/CraftType
            5, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            100, 100, 100, //Swash
            120, 120, //Endpoint CH1
            95, 120, //Endpoint CH2
            120, 120, //Endpoint CH3
            120, 120, //Endpoint CH4
            95, 120, //Endpoint CH5
            120, 120, //Endpoint CH6
            120, -18, //Throttle EP0
            120, 14, //Throttle EP1
            120, -45, //Throttle EP2
            120, -24, //Throttle EP3
            120, -24, //Throttle EP4
            2, 0, //Pitch EP0
            4, -89, //Pitch EP1
            85, -4, //Pitch EP2
            5, -21, //Pitch EP3
            5, -18, //Pitch EP4
            23, 0, 20, 10, -18, -100, // Subtrim
            4, -100, 0, 2, // Mix 1
            1, -5, 85, 2, // Mix 2
            20, 100, 100, 0, // Mix 3
            0, 2, //Switch function
            0, 1, //VR functions
            21, 28//Checksum
        });
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = new ParameterMessage();
        expResult.setCraftTypeNum(CraftType.heli90);
        expResult.setDRValueForChannel(DRChannel.CHANNEL1, 0, 0);
        expResult.setDRValueForChannel(DRChannel.CHANNEL2, 0, 0);
        expResult.setDRValueForChannel(DRChannel.CHANNEL4, 0, 0);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL1, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL2, 95, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL3, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL4, 120, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL5, 95, 120);
        expResult.setEndPointValueForChannel(ControlChannel.CHANNEL6, 120, 120);
        expResult.setMixSettingsValue(1, MixDestination.CH5, MixSource.CH1, MixSwitch.ON, 0, -100);
        expResult.setMixSettingsValue(2, MixDestination.CH2, MixSource.CH1, MixSwitch.ON, 85, -5);
        expResult.setMixSettingsValue(3, MixDestination.CH5, MixSource.CH2, MixSwitch.SWA, 100, 100);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP0, (byte)2, (byte)0);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP1, (byte)4, (byte)-89);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP2, (byte)85, (byte)-4);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP3, (byte)5, (byte)-21);
        expResult.setPitchCurveValueForChannel(HeliEndPoint.EP4, (byte)5, (byte)-18);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL1,true);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL2,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL3,true);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL4,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL5,false);
        expResult.setReverseBitmaskForChannel(ControlChannel.CHANNEL6,false);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL1, 23);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL2, 0);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL3, 20);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL4, 10);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL5, -18);
        expResult.setSubtrimValueForChannel(ControlChannel.CHANNEL6, -100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL1, 100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL2, 100);
        expResult.setSwashValueForChannel(SwashChannel.CHANNEL6, 100);
        expResult.setSwitchFunction(SwitchType.SwitchA, SwitchFunction.Unassigned);
        expResult.setSwitchFunction(SwitchType.SwitchB, SwitchFunction.ThrottleCut);
        expResult.setTXModelType(TXModel.Model2);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP0, (byte)120, (byte)-18);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP1, (byte)120, (byte)14);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP2, (byte)120, (byte)-45);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP3, (byte)120, (byte)-24);
        expResult.setThrottleCurveValueForChannel(HeliEndPoint.EP4, (byte)120, (byte)-24);
        expResult.setVRFunction(VRType.VRA, VRFunction.Unassigned);
        expResult.setVRFunction(VRType.VRB, VRFunction.PitchAdjust);
        ParameterMessage result = instance.GetParameterSetMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }
    
}
