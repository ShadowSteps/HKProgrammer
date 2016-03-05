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
        ByteArray expResult = new ByteArray(MessageHandlerConsts.msgPositionLength);
        expResult.Write(new byte[] { MessageHandlerConsts.HeaderBeggining , MessageHandlerConsts.HeaderPosition });
        ByteArray result = instance.GetBytesForPositionValuesMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPositionValuesMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetPositionValuesMessageFromBytes() {
        System.out.println("GetPositionValuesMessageFromBytes");
        ByteArray messageBytes = null;
        MessageHandler instance = new MessageHandler();
        PositionValuesMessage expResult = null;
        PositionValuesMessage result = instance.GetPositionValuesMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBytesForParameterRequestMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterRequestMessage() {
        System.out.println("GetBytesForParameterRequestMessage");
        ParameterRequest message = null;
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = null;
        ByteArray result = instance.GetBytesForParameterRequestMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetParameterRequestMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterRequestMessageFromBytes() {
        System.out.println("GetParameterRequestMessageFromBytes");
        ByteArray messageBytes = null;
        MessageHandler instance = new MessageHandler();
        ParameterRequest expResult = null;
        ParameterRequest result = instance.GetParameterRequestMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBytesForParameterDumpMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterDumpMessage() {
        System.out.println("GetBytesForParameterDumpMessage");
        ParameterMessage message = null;
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = null;
        ByteArray result = instance.GetBytesForParameterDumpMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetParameterDumpMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterDumpMessageFromBytes() {
        System.out.println("GetParameterDumpMessageFromBytes");
        ByteArray messageBytes = null;
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = null;
        ParameterMessage result = instance.GetParameterDumpMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBytesForParameterSetMessage method, of class MessageHandler.
     */
    @Test
    public void testGetBytesForParameterSetMessage() {
        System.out.println("GetBytesForParameterSetMessage");
        ParameterMessage message = null;
        MessageHandler instance = new MessageHandler();
        ByteArray expResult = null;
        ByteArray result = instance.GetBytesForParameterSetMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetParameterSetMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetParameterSetMessageFromBytes() {
        System.out.println("GetParameterSetMessageFromBytes");
        ByteArray messageBytes = null;
        MessageHandler instance = new MessageHandler();
        ParameterMessage expResult = null;
        ParameterMessage result = instance.GetParameterSetMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
