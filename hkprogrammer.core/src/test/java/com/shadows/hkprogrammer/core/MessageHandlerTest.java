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
import com.shadows.hkprogrammer.core.messages.*;
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
            -1, 59
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
            -1, 59
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
            0, 9 //Checksum
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
            0, 9 //Checksum
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
            0, 9 //Checksum
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
            0, 9 //Checksum
        });
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = new ParameterMessage();
        ParameterMessage result = instance.GetParameterSetMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }
    
}
