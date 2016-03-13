/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.providers;

import com.shadows.hkprogrammer.core.MessageHandlerConsts;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.utils.ByteArray;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import jdk.internal.org.objectweb.asm.Handle;

/**
 *
 * @author John
 */
public class UsbConnectionProvider implements SerialPortEventListener,ICommunicationProvider {
    public final byte MessageDelimeter = MessageHandlerConsts.HeaderBeggining;
    private InputStream inputStream;
    private OutputStream writeOutputStream;
    private final int PORT_TIMEOUT = 2000;
    private SerialPort serialPort;
    protected ByteArray receivedBytesBuffer;    
    private final ArrayList<ByteArray> ReadQueue = new ArrayList();
    private final Object lock = new Object();
    private boolean connected = false;
    
    private CommPortIdentifier GetSelectedPortId(String port){
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();        
        boolean portFound = false;
        CommPortIdentifier portId = null;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(port)) {
                    portFound = true;
                    break;
                }
            }
        }
        return portId;
    }
    
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                receivedBytesBuffer = new ByteArray(1024);
                int total = 0;
                byte received = -1;
                try {                   
                    while ((received = (byte)inputStream.read()) != -1)
                    {
                        if (received == MessageDelimeter&&
                            (
                                total >= MessageHandlerConsts.msgParameterDumpLength||
                                total == MessageHandlerConsts.msgPositionLength
                            )
                        ){
                            synchronized (lock){
                                this.ReadQueue.add(receivedBytesBuffer.Read(0, total));
                            }
                            receivedBytesBuffer = new ByteArray(1024);
                            total = 0;                            
                        }             
                        total ++;                        
                        receivedBytesBuffer.Write(new byte[]{received});
                    }
                    
                } catch (IOException ex) {
                }  
                break;
        }
    }   
    
    @Override
    public void OpenConnection(String SelectedPort) throws IOException {
        CommPortIdentifier portId = GetSelectedPortId(SelectedPort);
        if (portId == null) {
            throw new IOException("Port " + SelectedPort + " not found.");
        }
        try {
            serialPort = (SerialPort) portId.open("USBCommunicator", PORT_TIMEOUT);
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
            connected = true;
        } catch (PortInUseException | IOException | TooManyListenersException | InterruptedException | UnsupportedCommOperationException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void CloseConnection() {
        try {
            inputStream.close();
            writeOutputStream.close();
            connected = false;
        } catch (IOException ex) {
        }
    }

    @Override
    public void Write(ByteArray message) throws IOException {        
        writeOutputStream.write(message.ToPrimitive());
        writeOutputStream.flush();
    }

    @Override
    public ArrayList<String> GetListOfPorts() {
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portsArray = new ArrayList<>();
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
        ByteArray first;
        synchronized (lock){
            if (ReadQueue.isEmpty())
                return new ByteArray(0);
            first = this.ReadQueue.get(0);
            ReadQueue.remove(0);
        }
        return first;        
    }

    @Override
    public boolean isConnectionOpened() {
        return connected;
    }
}
