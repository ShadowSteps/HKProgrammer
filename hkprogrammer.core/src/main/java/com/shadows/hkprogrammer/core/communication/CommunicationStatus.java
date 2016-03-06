/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.communication;

import com.shadows.hkprogrammer.core.messages.ParameterMessage;
import com.shadows.hkprogrammer.core.messages.PositionValuesMessage;

/**
 *
 * @author John
 */
public class CommunicationStatus {
    public PositionValuesMessage Positions = new PositionValuesMessage();
    public ParameterMessage Parameters = new ParameterMessage();
}
