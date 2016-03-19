/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client.listeners;

import com.shadows.hkprogrammer.core.client.events.TaskOnSuccessEvent;
import com.shadows.hkprogrammer.core.client.events.IActionEvent;

/**
 *
 * @author John
 * @param <TaskReturnType>
 */
public abstract class ATaskOnSuccessListener<TaskReturnType> implements IActionListener{
    @Override
    public void trigger(IActionEvent event) {
        TaskOnSuccessEvent<TaskReturnType> e = (TaskOnSuccessEvent<TaskReturnType>)event;
        this.onSuccess(e);
    }
    
    public abstract void onSuccess(TaskOnSuccessEvent<TaskReturnType> result);
}
