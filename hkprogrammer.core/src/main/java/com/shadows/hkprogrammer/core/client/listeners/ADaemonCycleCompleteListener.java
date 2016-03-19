/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client.listeners;

import com.shadows.hkprogrammer.core.client.events.DaemonCycleCompleteEvent;
import com.shadows.hkprogrammer.core.client.events.IActionEvent;

/**
 *
 * @author John
 * @param <CycleType>
 */
public abstract class ADaemonCycleCompleteListener<CycleType> implements IActionListener{
    @Override
    public void trigger(IActionEvent event) {
        DaemonCycleCompleteEvent<CycleType> e = (DaemonCycleCompleteEvent<CycleType>)event;
        this.onCycle(e);
    }
    
    public abstract void onCycle(DaemonCycleCompleteEvent<CycleType> result);
}
