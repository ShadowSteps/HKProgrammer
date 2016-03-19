/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import com.shadows.hkprogrammer.core.utils.ByteArray;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public interface ICommunicationProvider {
    void OpenConnection(String SelectedPort) throws Exception;
    void CloseConnection();
    ByteArray Read() throws Exception; 
    void Write(ByteArray message) throws Exception;
    ArrayList<String> GetListOfPorts() throws Exception;
    public boolean isConnectionOpened();
}
