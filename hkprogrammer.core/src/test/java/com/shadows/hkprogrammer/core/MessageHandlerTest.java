/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;
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
        message.setChannelPositionInfo(1, 1340);
        message.setChannelPositionInfo(2, 1250);
        message.setChannelPositionInfo(3, 1560);
        message.setChannelPositionInfo(4, 1800);
        message.setChannelPositionInfo(5, 1025);
        message.setChannelPositionInfo(6, 1360);
        message.setFourthChannelPositionPseudo(562);
        MessageHandler instance = new MessageHandler();
        byte[] expResult = new byte[] {
            (byte)0x55, (byte)0xFC, //HEADER
            5, 60,                  //CHANNEL 1
            4, -30,                 //CHANNEL 2
            6, 24,                  //CHANNEL 3
            7, 8,                   //CHANNEL 4
            4, 1,                   //CHANNEL 5
            5, 80,                  //CHANNEL 6
            2, 50,                  //CHANNEL 4 PSEUDO
            0, -30                  //CHECKSUM
        };
        byte[] result = instance.GetBytesForPositionValuesMessage(message);
        assertArrayEquals(expResult, result);       
    }

    /**
     * Test of GetPositionValuesMessageFromBytes method, of class MessageHandler.
     */
    @Test
    public void testGetPositionValuesMessageFromBytes() {
        System.out.println("GetPositionValuesMessageFromBytes");
        byte[] messageBytes = new byte[] {
            (byte)0x55, (byte)0xFC, //HEADER
            5, 60,                  //CHANNEL 1
            4, -30,                 //CHANNEL 2
            6, 24,                  //CHANNEL 3
            7, 8,                   //CHANNEL 4
            4, 1,                   //CHANNEL 5
            5, 80,                  //CHANNEL 6
            2, 50,                  //CHANNEL 4 PSEUDO
            0, -30                  //CHECKSUM
        };
        MessageHandler instance = new MessageHandler();        
        PositionValuesMessage expResult = new PositionValuesMessage();
        expResult.setChannelPositionInfo(1, 1340);
        expResult.setChannelPositionInfo(2, 1250);
        expResult.setChannelPositionInfo(3, 1560);
        expResult.setChannelPositionInfo(4, 1800);
        expResult.setChannelPositionInfo(5, 1025);
        expResult.setChannelPositionInfo(6, 1360);
        expResult.setFourthChannelPositionPseudo(562);
        PositionValuesMessage result = instance.GetPositionValuesMessageFromBytes(messageBytes);
        assertEquals(expResult, result);
    }
    
}
