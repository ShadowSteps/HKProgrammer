/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.providers;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class UsbConnectionProvider implements SerialPortEventListener,ICommunicationProvider {
    private InputStream inputStream;
    private OutputStream writeOutputStream;
    private final int PORT_TIMEOUT = 2000;
    private SerialPort serialPort;
    protected LinkedBlockingQueue<Byte> receivedBytes;
    public UsbConnectionProvider() throws IOException {
        
        receivedBytes = new LinkedBlockingQueue<>(100000);
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

            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                //nothing to do...
                break;

            case SerialPortEvent.DATA_AVAILABLE:
                byte received = -1;
                do {
                    try {
                        received = (byte) inputStream.read();
                    } catch (IOException e) {
                        System.err.println("Error reading USB:" + e.getMessage());
                    }

                    synchronized (receivedBytes) {
                        try {
                            receivedBytes.add(received);
                        } catch (IllegalStateException ew) {
                            System.err.println(ew.getMessage());
                            receivedBytes.poll();
                            receivedBytes.add(received);
                        }
                    }
                } while (received != -1);

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
    public byte[] ReadPositionValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] ReadParameterDumpValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Write(byte[] message) throws IOException {        
        writeOutputStream.write(message);
        writeOutputStream.flush();
    }

    @Override
    public String[] GetListOfPorts() {
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portsArray = new ArrayList();
        CommPortIdentifier portId;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portsArray.add(portId.getName());
            }
        }
        return (String[])portsArray.toArray();
    }
}
