package com.shadows.hkprogrammer.android.core.providers;

import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;
import com.shadows.hkprogrammer.core.utils.ByteArray;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by John on 17.3.2016 г..
 */
public class BluetoothProvider implements ICommunicationProvider {
    @Override
    public void OpenConnection(String s) throws IOException {

    }

    @Override
    public void CloseConnection() {

    }

    @Override
    public ByteArray Read() throws IOException {
        return null;
    }

    @Override
    public void Write(ByteArray byteArray) throws IOException {

    }

    @Override
    public ArrayList<String> GetListOfPorts() {
        return null;
    }

    @Override
    public boolean isConnectionOpened() {
        return false;
    }
}
