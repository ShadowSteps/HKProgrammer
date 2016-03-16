/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.windows.core.tasks;

import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.windows.controls.events.PositionSyncEvent;
import java.awt.event.ActionListener;
import javafx.concurrent.Task;

/**
 *
 * @author John
 */
public class SyncTask extends Task<Boolean>{
    public boolean isSyncing = true;
    public int SyncInterval = 100;
    public ActionListener onSyncCycleSuccess;
    private final Communicator communicator;

    public SyncTask(Communicator communicator) {
        this.communicator = communicator;
    }
    
    @Override
    protected Boolean call() throws Exception {
        while(isSyncing){
            synchronized(communicator){            
                communicator.Sync();
            } 
            if (onSyncCycleSuccess != null)
                onSyncCycleSuccess.actionPerformed(new PositionSyncEvent(communicator, communicator.getStatus().Positions));
            Thread.sleep(SyncInterval);
        }        
        return true;
    }
}
