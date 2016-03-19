package com.shadows.hkprogrammer.android.core.providers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.shadows.hkprogrammer.android.core.exceptions.BluetoothDeviceNotFoundException;
import com.shadows.hkprogrammer.android.core.exceptions.BluetoothNotEnabledException;
import com.shadows.hkprogrammer.core.MessageHandlerConsts;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.utils.ByteArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by John on 17.3.2016 г..
 */
public class BluetoothProvider implements ICommunicationProvider {
    public final byte MessageDelimeter = MessageHandlerConsts.HeaderBeggining;
    private boolean isConnected = false;
    private final BluetoothAdapter adapter;
    private BluetoothSocket socket;
    private final UUID deviceUUID;
    private InputStream input;
    private OutputStream output;
    private final ArrayList<ByteArray> ReadQueue = new ArrayList();
    private Object lock = new Object();

    public BluetoothProvider() {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
        deviceUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    }

    private void checkBluetoothStatus() throws BluetoothNotEnabledException {
        if (adapter==null)
            throw new IllegalStateException("Device does not support bluetooth!");
        else {
            if (!adapter.isEnabled()){
                throw new BluetoothNotEnabledException("Device bluetooth not enabled!");
            }
        }
    }

    @Override
    public void OpenConnection(String s) throws IOException, BluetoothDeviceNotFoundException {
        BluetoothDevice selected = null;
        String address = s.substring(s.length() - 17);
        selected = adapter.getRemoteDevice(address);
        if (selected == null)
            throw new BluetoothDeviceNotFoundException("Device with name \""+s+"\" not found in paired devices!");
        socket = selected.createRfcommSocketToServiceRecord(deviceUUID);
        try{
            socket.connect();
            input = socket.getInputStream();
            output = socket.getOutputStream();
            output.write(new byte[]{0});
            isConnected = true;
        } catch (Exception e){
            this.CloseConnection();
            throw e;
        }
    }

    @Override
    public void CloseConnection() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
        }
        isConnected = false;
    }

    public void readCycle(){
        ByteArray receivedBytesBuffer = new ByteArray(1024);
        int total = 0;
        byte received = -1;
        try {
            while ((received = (byte)input.read()) != -1||isConnected)
            {
                if (received == MessageDelimeter&&
                        (
                                total >= MessageHandlerConsts.msgParameterDumpLength||
                                        total == MessageHandlerConsts.msgPositionLength
                        )
                        ){
                    synchronized(lock){
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
    }

    @Override
    public ByteArray Read() throws IOException {
        ByteArray first;
        synchronized(lock){
            if (ReadQueue.isEmpty())
                return new ByteArray(0);
            first = this.ReadQueue.get(0);
            ReadQueue.remove(0);
        }
        return first;
    }

    @Override
    public void Write(ByteArray byteArray) throws IOException {
        output.write(byteArray.ToPrimitive());
        output.flush();
    }

    @Override
    public ArrayList<String> GetListOfPorts() throws Exception{
        checkBluetoothStatus();
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        ArrayList<String> list = new ArrayList<>();
        for (BluetoothDevice device :  pairedDevices){
            list.add(device.getName() + " - " + device.getAddress());
        }
        return list;
    }

    @Override
    public boolean isConnectionOpened() {
        return isConnected;
    }
}
