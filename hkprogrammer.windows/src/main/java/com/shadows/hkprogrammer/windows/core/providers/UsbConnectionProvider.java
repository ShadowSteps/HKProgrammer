/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.providers;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.utils.ByteArray;
import com.shadows.hkprogrammer.windows.core.OSDetector;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author John
 */
public class UsbConnectionProvider implements SerialPortEventListener,ICommunicationProvider {
    private InputStream inputStream;
    private OutputStream writeOutputStream;
    private final int PORT_TIMEOUT = 2000;
    private SerialPort serialPort;
    protected ByteArray receivedBytesBuffer;    
    private ByteArray currentMessage;
    public UsbConnectionProvider() throws IOException {        
        receivedBytesBuffer = new ByteArray(1024);  
        /*String port = "COM1";
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        boolean portFound = false;
        CommPortIdentifier portId = null;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(portId.getName());
                if (portId.getName().equals(port)) {
                    System.out.println("Found port: " + port);
                    portFound = true;
                    break;
                }
            }
        }

        if (!portFound) {
            throw new IOException("port " + port + " not found.");
        }

        try {
            System.out.println("USB port opening...");
            serialPort = (SerialPort) portId.open("USBCommunicator", PORT_TIMEOUT);
            System.out.println("USB port opened");
            inputStream = serialPort.getInputStream();
            writeOutputStream = serialPort.getOutputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            Thread.sleep(1000);

            int baudRate = 115200;
            serialPort.setSerialPortParams(baudRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            System.out.println("setted SerialPortParams");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new IOException(e.getMessage());
        }*/
    }

    public void closeUSB() {
        try {
            inputStream.close();
            writeOutputStream.close();
        } catch (IOException e) {
        }
    }    
    
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                receivedBytesBuffer = new ByteArray(1024);
                int total = 0;
                int received = -1;
                try {
                    do {
                        byte[] buffer = new byte[64];
                        received = inputStream.read(buffer);
                        total+=received;
                        receivedBytesBuffer.Write(buffer);
                    } while (received != -1);

                } catch (IOException ex) {
                }  
                break;
        }
    }

    @Override
    public void OpenConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CloseConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Write(ByteArray message) throws IOException {        
        writeOutputStream.write(message.ToPrimitive());
        writeOutputStream.flush();
    }

    @Override
    public ArrayList<String> GetListOfPorts() {
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portsArray = new ArrayList<String>();
        CommPortIdentifier portId;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portsArray.add(portId.getName());
            }
        }
        return portsArray;
    }

    @Override
    public ByteArray Read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
