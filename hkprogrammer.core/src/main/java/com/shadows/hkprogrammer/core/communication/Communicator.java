/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import com.shadows.hkprogrammer.core.MessageHandler;
import com.shadows.hkprogrammer.core.MessageHandlerConsts;
import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.ParameterRequest;
import com.shadows.hkprogrammer.core.utils.ByteArray;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author John
 */
public class Communicator {
    private final MessageHandler Handler = new MessageHandler();
    private final ICommunicationProvider Provider;
    private final CommunicationStatus Status = new CommunicationStatus();
    
    private final ByteArray PositionsHeader = ByteArray.FromByteArray(
            new byte[] { 
                MessageHandlerConsts.HeaderBeggining,
                MessageHandlerConsts.HeaderPosition
            }
    );
    private final ByteArray ParametersHeader = ByteArray.FromByteArray(
            new byte[] { 
                MessageHandlerConsts.HeaderBeggining,
                MessageHandlerConsts.HeaderParameterDump
            }
    );
    
    public Communicator(ICommunicationProvider provider){
        this.Provider = provider;
    }

    public CommunicationStatus getStatus() {
        return Status;
    }
         
    public void Sync() throws IOException{
        ByteArray readBytes = Provider.Read();
        if (!readBytes.isEmpty()){
            ByteArray Header = readBytes.Read(0, 2);        
            if (Objects.equals(Header, PositionsHeader)){
                Status.Positions = Handler.GetPositionValuesMessageFromBytes(readBytes);
            } else if (Objects.equals(Header, ParametersHeader)){
                Status.Parameters = Handler.GetParameterDumpMessageFromBytes(readBytes);
            }
        }
    }
    
    public void RequestParametersDump() throws IOException{
        ParameterRequest Request = new ParameterRequest();
        ByteArray bytes = Handler.GetBytesForParameterRequestMessage(Request);
        Provider.Write(bytes);
    }
    
    public void SetParameters(ParameterMessage parameters) throws IOException{
        ByteArray bytes = Handler.GetBytesForParameterSetMessage(parameters);
        Provider.Write(bytes);
    }
}
