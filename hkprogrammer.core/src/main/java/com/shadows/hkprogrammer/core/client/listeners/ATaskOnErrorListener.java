/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnErrorEvent;
import com.shadows.hkprogrammer.core.client.events.IActionEvent;

/**
 *
 * @author John
 */
public abstract class ATaskOnErrorListener implements IActionListener{
    @Override
    public void trigger(IActionEvent event) {
        TaskOnErrorEvent e = (TaskOnErrorEvent)event;
        this.onError(e);
    }
    
    public abstract void onError(TaskOnErrorEvent result);
}
