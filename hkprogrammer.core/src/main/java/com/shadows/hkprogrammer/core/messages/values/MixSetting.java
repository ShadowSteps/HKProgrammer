/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.values;

/**
 *
 * @author John
 */
public class MixSetting {
    private int Source;
    private int Destination;
    private int Uprate;
    private int Downrate;
    private int Switch;

    public int getSource() {
        return Source;
    }

    public void setSource(int Source) {
        if (Source > 7 || Source < 0)
            throw new IllegalArgumentException("Value of Source must be between 0 and 7");
    
        this.Source = Source;
    }

    public int getDestination() {
        return Destination;
    }

    public void setDestination(int Destination) {
        if (Destination > 5 || Destination < 0)
            throw new IllegalArgumentException("Value of Destination must be between 0 and 5");
    
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

    public int getSwitch() {
        return Switch;
    }

    public void setSwitch(int Switch) {
        if (Switch > 4 || Switch < 0)
            throw new IllegalArgumentException("Switch of Destination must be between 0 and 4");
    
        this.Switch = Switch;
    }
    
    private void ValidateProcents(int Value){
        if (Value > 127 || Value < 0)
            throw new IllegalArgumentException("Value of mix rates must be between 0 and 127");
    }
}
