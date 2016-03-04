/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.values;

import com.shadows.hkprogrammer.core.messages.enums.MixDestination;
import com.shadows.hkprogrammer.core.messages.enums.MixSource;
import com.shadows.hkprogrammer.core.messages.enums.MixSwitch;

/**
 *
 * @author John
 */
public class MixSetting {
    private MixSource Source = MixSource.CH1;
    private MixDestination Destination = MixDestination.CH1;
    private int Uprate = 0;
    private int Downrate = 0;
    private MixSwitch Switch = MixSwitch.OFF;

    public MixSource getSource() {
        return Source;
    }

    public void setSource(MixSource Source) {
        this.Source = Source;
    }

    public MixDestination getDestination() {
        return Destination;
    }

    public void setDestination(MixDestination Destination) {
        this.Destination = Destination;
    }

    public int getUprate() {
        return Uprate;
    }

    public void setUprate(int Uprate) {
        ValidateProcents(Uprate);
        this.Uprate = Uprate;
    }

    public int getDownrate() {
        return Downrate;
    }

    public void setDownrate(int Downrate) {
        ValidateProcents(Downrate);
        this.Downrate = Downrate;
    }

    public MixSwitch getSwitch() {
        return Switch;
    }

    public void setSwitch(MixSwitch Switch) {
        this.Switch = Switch;
    }
    
    private void ValidateProcents(int Value){
        if (Value > 127 || Value < 0)
            throw new IllegalArgumentException("Value of mix rates must be between 0 and 127");
    }
}
