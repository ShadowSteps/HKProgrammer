/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.client.listeners;

import com.shadows.hkprogrammer.core.client.events.IActionEvent;

/**
 *
 * @author John
 */
public interface IActionListener {
    public void trigger(IActionEvent event);
}
