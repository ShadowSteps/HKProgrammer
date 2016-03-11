/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import com.shadows.hkprogrammer.core.MessageHandler;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.utils.ByteArray;
import java.io.IOException;

/**
 *
 * @author John
 */
public class Communicator {
    private final MessageHandler Handler = new MessageHandler();
    private final ICommunicationProvider Provider;
    private final CommunicationStatus Status = new CommunicationStatus();
    
    public Communicator(ICommunicationProvider provider){
        this.Provider = provider;
    }

    public CommunicationStatus getStatus() {
        return Status;
    }
         
    public void SyncStatus(){
        byte[] positionInitialBytes = Provider.ReadPositionValues();
        byte[] parameterInitialBytes = Provider.ReadParameterDumpValues();
        ByteArray positionBytes = ByteArray.FromByteArray(positionInitialBytes),
                parameterBytes = ByteArray.FromByteArray(parameterInitialBytes);
        if (!positionBytes.isEmpty())
            Status.Positions = Handler.GetPositionValuesMessageFromBytes(positionBytes);
        if (!parameterBytes.isEmpty())
            Status.Parameters = Handler.GetParameterDumpMessageFromBytes(parameterBytes);
    }
    
    public void RequestParametersDump() throws IOException{
        ParameterRequest Request = new ParameterRequest();
        ByteArray bytes = Handler.GetBytesForParameterRequestMessage(Request);
        Provider.Write(bytes.ToPrimitive());
    }
    
    public void SetParameters(ParameterMessage parameters) throws IOException{
        ByteArray bytes = Handler.GetBytesForParameterSetMessage(parameters);
        Provider.Write(bytes.ToPrimitive());
    }
}
