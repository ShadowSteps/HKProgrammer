/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
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
            37, 28
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
            37, 28
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
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterDump,
            0, //TXModel/CraftType
            0, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            0, 0, 0, //Swash
            0, 0, //Endpoint CH1
            0, 0, //Endpoint CH2
            0, 0, //Endpoint CH3
            0, 0, //Endpoint CH4
            0, 0, //Endpoint CH5
            0, 0, //Endpoint CH6
            0, 0, //Throttle EP0
            0, 0, //Throttle EP1
            0, 0, //Throttle EP2
            0, 0, //Throttle EP3
            0, 0, //Throttle EP4
            0, 0, //Pitch EP0
            0, 0, //Pitch EP1
            0, 0, //Pitch EP2
            0, 0, //Pitch EP3
            0, 0, //Pitch EP4
            0, 0, 0, 0, 0, 0, // Subtrim
            0, 0, 0, 3, // Mix 1
            0, 0, 0, 3, // Mix 2
            0, 0, 0, 3, // Mix 3
            0, 0, //Switch function
            0, 0, //VR functions
            -34, -84 //Checksum
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
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = ByteArray.FromByteArray(new byte[]{
            MessageHandlerConsts.HeaderBeggining,
            MessageHandlerConsts.HeaderParameterSet,
            0, //TXModel/CraftType
            0, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            0, 0, 0, //Swash
            0, 0, //Endpoint CH1
            0, 0, //Endpoint CH2
            0, 0, //Endpoint CH3
            0, 0, //Endpoint CH4
            0, 0, //Endpoint CH5
            0, 0, //Endpoint CH6
            0, 0, //Throttle EP0
            0, 0, //Throttle EP1
            0, 0, //Throttle EP2
            0, 0, //Throttle EP3
            0, 0, //Throttle EP4
            0, 0, //Pitch EP0
            0, 0, //Pitch EP1
            0, 0, //Pitch EP2
            0, 0, //Pitch EP3
            0, 0, //Pitch EP4
            0, 0, 0, 0, 0, 0, // Subtrim
            0, 0, 0, 3, // Mix 1
            0, 0, 0, 3, // Mix 2
            0, 0, 0, 3, // Mix 3
            0, 0, //Switch function
            0, 0, //VR functions
            -34, -84 //Checksum
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
            0, //TXModel/CraftType
            0, //Reverse bitmark
            0, 0, //DR CH1
            0, 0, //DR CH2
            0, 0, //DR CH4
            0, 0, 0, //Swash
            0, 0, //Endpoint CH1
            0, 0, //Endpoint CH2
            0, 0, //Endpoint CH3
            0, 0, //Endpoint CH4
            0, 0, //Endpoint CH5
            0, 0, //Endpoint CH6
            0, 0, //Throttle EP0
            0, 0, //Throttle EP1
            0, 0, //Throttle EP2
            0, 0, //Throttle EP3
            0, 0, //Throttle EP4
            0, 0, //Pitch EP0
            0, 0, //Pitch EP1
            0, 0, //Pitch EP2
            0, 0, //Pitch EP3
            0, 0, //Pitch EP4
            0, 0, 0, 0, 0, 0, // Subtrim
            0, 0, 0, 3, // Mix 1
            0, 0, 0, 3, // Mix 2
            0, 0, 0, 3, // Mix 3
            0, 0, //Switch function
            0, 0, //VR functions
            -34, -84 //Checksum
        });
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = new ParameterMessage();
        ParameterMessage result = instance.GetParameterSetMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }
    
}
