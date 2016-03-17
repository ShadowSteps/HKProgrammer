package com.shadows.hkprogrammer.android.core.events;

import java.util.ArrayList;

/**
 * Created by John on 17.3.2016 г..
 */
public class PortsLoadedEvent implements IActionEvent {
    public ArrayList<String> Ports;

    public PortsLoadedEvent(ArrayList<String> ports) {
        Ports = ports;
    }
}
