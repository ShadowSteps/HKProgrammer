/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import com.shadows.hkprogrammer.core.utils.ByteArray;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public interface ICommunicationProvider {
    void OpenConnection(String SelectedPort) throws IOException;
    void CloseConnection();
    ByteArray Read() throws IOException; 
    void Write(ByteArray message) throws IOException;
    ArrayList<String> GetListOfPorts();
    public boolean isConnectionOpened();
}
