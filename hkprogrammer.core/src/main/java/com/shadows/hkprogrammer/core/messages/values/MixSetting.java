/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages.values;

import com.shadows.hkprogrammer.core.messages.enums.MixDestination;
import com.shadows.hkprogrammer.core.messages.enums.MixSource;
import com.shadows.hkprogrammer.core.messages.enums.MixSwitch;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.Source);
        hash = 73 * hash + Objects.hashCode(this.Destination);
        hash = 73 * hash + this.Uprate;
        hash = 73 * hash + this.Downrate;
        hash = 73 * hash + Objects.hashCode(this.Switch);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MixSetting other = (MixSetting) obj;
        if (this.Source != other.Source) {
            return false;
        }
        if (this.Destination != other.Destination) {
            return false;
        }
        if (this.Uprate != other.Uprate) {
            return false;
        }
        if (this.Downrate != other.Downrate) {
            return false;
        }
        if (this.Switch != other.Switch) {
            return false;
        }
        return true;
    }

    
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
        if (Value > 127 || Value < -127)
            throw new IllegalArgumentException("Value of mix rates must be between 0 and 127");
    }
}
