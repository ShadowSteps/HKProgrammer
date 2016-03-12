/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.providers;

import com.shadows.hkprogrammer.core.utils.ByteArray;
import gnu.io.SerialPortEvent;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John
 */
public class UsbConnectionProviderTest {
    
    public UsbConnectionProviderTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of closeUSB method, of class UsbConnectionProvider.
     */
    @Test
    public void testCloseUSB() throws IOException {
        System.out.println("closeUSB");
        UsbConnectionProvider instance = new UsbConnectionProvider();
        instance.closeUSB();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of serialEvent method, of class UsbConnectionProvider.
     */
    @Test
    public void testSerialEvent() throws IOException {
        System.out.println("serialEvent");
        SerialPortEvent event = null;
        UsbConnectionProvider instance = new UsbConnectionProvider();
        instance.serialEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of OpenConnection method, of class UsbConnectionProvider.
     */
    @Test
    public void testOpenConnection() throws IOException {
        System.out.println("OpenConnection");
        UsbConnectionProvider instance = new UsbConnectionProvider();
        instance.OpenConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CloseConnection method, of class UsbConnectionProvider.
     */
    @Test
    public void testCloseConnection() throws IOException {
        System.out.println("CloseConnection");
        UsbConnectionProvider instance = new UsbConnectionProvider();
        instance.CloseConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Write method, of class UsbConnectionProvider.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("Write");
        byte[] message = null;
        UsbConnectionProvider instance = new UsbConnectionProvider();
        instance.Write(ByteArray.FromByteArray(message));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetListOfPorts method, of class UsbConnectionProvider.
     * @throws java.io.IOException
     */
    @Test
    public void testGetListOfPorts() throws IOException {
        System.out.println("GetListOfPorts");
        UsbConnectionProvider instance = new UsbConnectionProvider();        
        ArrayList<String> result = instance.GetListOfPorts();
        assertTrue(result.size() > 0);
        System.out.println(result);
    }
    
}
