/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client.events;

/**
 *
 * @author John
 * @param <CycleResult>
 */
public class DaemonCycleCompleteEvent<CycleResult> implements IActionEvent{
    public CycleResult cycle;

    public DaemonCycleCompleteEvent(CycleResult result) {
        this.cycle = result;
    }    
}
