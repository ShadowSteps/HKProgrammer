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
public final class Communicator {
    private final MessageHandler Handler = new MessageHandler();
    private final ICommunicationProvider Provider;
    private final CommunicationStatus Status = new CommunicationStatus();
    private boolean parametersSync = false;
    
    public boolean isParametersSync(){
        return this.parametersSync;
    }
    
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
    
    public Communicator(ICommunicationProvider provider) throws IOException, InterruptedException{
        if (!provider.isConnectionOpened())
            throw new IOException("Cannot craete Communicator while connection with provider is not established!");
        this.Provider = provider;   
    }
    
    public CommunicationStatus getStatus() {
        return Status;
    }         
    
    public void Sync() throws IOException{
        ByteArray readBytes;
        while (!(readBytes= Provider.Read()).isEmpty()){
            ByteArray Header = readBytes.Read(0, 2);        
            if (Objects.equals(Header, PositionsHeader)){
                Status.Positions = Handler.GetPositionValuesMessageFromBytes(readBytes);
            } else if (Objects.equals(Header, ParametersHeader)){
                Status.Parameters = Handler.GetParameterDumpMessageFromBytes(readBytes);
                parametersSync = true;
            }
        }
    }
    
    public void ParametersSync() throws IOException, InterruptedException {
        RequestParametersDump();        
        for (int i = 0; i < 5; i++) {
            Thread.sleep(i*1000);
            Sync();
            if (parametersSync)
                break;
        }
        if (!parametersSync)
            throw new IOException("Could not sync parameters!");
    }
    
    public final void RequestParametersDump() throws IOException{
        parametersSync = false;
        ParameterRequest Request = new ParameterRequest();
        ByteArray bytes = Handler.GetBytesForParameterRequestMessage(Request);
        Provider.Write(bytes);
    }
    
    public final void SetParameters(ParameterMessage parameters) throws IOException{
        if (!parametersSync)
            throw new IllegalStateException("Cannot write parameter when they are not synced!");
        ByteArray bytes = Handler.GetBytesForParameterSetMessage(parameters);
        Provider.Write(bytes);
    }
}
