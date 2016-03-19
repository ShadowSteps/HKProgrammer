/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client;

import com.shadows.hkprogrammer.core.client.events.DaemonCycleCompleteEvent;
import com.shadows.hkprogrammer.core.client.listeners.ADaemonCycleCompleteListener;

/**
 *
 * @author John
 * @param <Type>
 */
public abstract class RunnableDaemon<Type> extends RunnableTask<Boolean>{
    private boolean isRunning = true;
    public ADaemonCycleCompleteListener<Type> onCycleComplete;
    @Override
    protected Boolean doRoutine() throws Exception {
        while (isRunning){
            Type result = doCycle();
            if (onCycleComplete != null)
                onCycleComplete.onCycle(new DaemonCycleCompleteEvent<>(result));
        }
        return true;
    }
    
    public void stop(){
        this.isRunning = false;
    }
    protected abstract Type doCycle() throws Exception;
}
