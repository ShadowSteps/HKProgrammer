/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import java.io.IOException;

/**
 *
 * @author John
 */
public interface ICommunicationProvider {
    void OpenConnection();
    void CloseConnection();
    byte[] ReadPositionValues();
    byte[] ReadParameterDumpValues();    
    void Write(byte[] message) throws IOException;
    String[] GetListOfPorts();
}
