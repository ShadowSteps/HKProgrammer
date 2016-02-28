/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John
 */
public class PositionValuesMessageTest {
    
    public PositionValuesMessageTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of GetPayloadToByteArray method, of class PositionValuesMessage.
     */
    @Test
    public void testGetPayloadToByteArray() {
        System.out.println("GetPayloadToByteArray");
        PositionValuesMessage instance = new PositionValuesMessage();
        instance.setChannelPositionInfo(1, 1250);
        instance.setChannelPositionInfo(2, 1250);
        instance.setChannelPositionInfo(3, 1250);
        instance.setChannelPositionInfo(4, 1250);
        instance.setChannelPositionInfo(5, 1250);
        instance.setChannelPositionInfo(6, 1250);
        instance.setFourthChannelPositionPseudo(1250);
        byte[] expResult = {
            4, -30, //CHANNEL 1
            4, -30, //CHANNEL 2
            4, -30, //CHANNEL 3
            4, -30, //CHANNEL 4
            4, -30, //CHANNEL 5
            4, -30, //CHANNEL 6
            4, -30 //CHANNEL 4 PESUDO
        };
        byte[] result = instance.GetPayloadToByteArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of GetVariablesFromPayload method, of class PositionValuesMessage.
     */
    @Test
    public void testGetVariablesFromPayload() {
        System.out.println("GetVariablesFromPayload");
        byte[] Payload = {
            4, -30, //CHANNEL 1
            4, -30, //CHANNEL 2
            4, -30, //CHANNEL 3
            4, -30, //CHANNEL 4
            4, -30, //CHANNEL 5
            4, -30, //CHANNEL 6
            4, -30 //CHANNEL 4 PESUDO
        };
        PositionValuesMessage instance = new PositionValuesMessage();
        PositionValuesMessage expected = new PositionValuesMessage();
        expected.setChannelPositionInfo(1, 1250);
        expected.setChannelPositionInfo(2, 1250);
        expected.setChannelPositionInfo(3, 1250);
        expected.setChannelPositionInfo(4, 1250);
        expected.setChannelPositionInfo(5, 1250);
        expected.setChannelPositionInfo(6, 1250);        
        expected.setFourthChannelPositionPseudo(1250);
        instance.GetVariablesFromPayload(Payload);
        assertEquals(expected, instance);
    }
    
}
