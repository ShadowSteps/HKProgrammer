/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import com.shadows.hkprogrammer.core.messages.enums.ControlChannel;
import com.shadows.hkprogrammer.core.messages.enums.CraftType;
import com.shadows.hkprogrammer.core.messages.enums.DRChannel;
import com.shadows.hkprogrammer.core.messages.enums.HeliEndPoint;
import com.shadows.hkprogrammer.core.messages.enums.MixDestination;
import com.shadows.hkprogrammer.core.messages.enums.MixSource;
import com.shadows.hkprogrammer.core.messages.enums.MixSwitch;
import com.shadows.hkprogrammer.core.messages.enums.SwashChannel;
import com.shadows.hkprogrammer.core.messages.enums.SwitchFunction;
import com.shadows.hkprogrammer.core.messages.enums.SwitchType;
import com.shadows.hkprogrammer.core.messages.enums.TXModel;
import com.shadows.hkprogrammer.core.messages.enums.VRFunction;
import com.shadows.hkprogrammer.core.messages.enums.VRType;
import com.shadows.hkprogrammer.core.messages.values.MixSetting;
import com.shadows.hkprogrammer.core.messages.values.ParameterDRValue;
import com.shadows.hkprogrammer.core.messages.values.PitchCurve;
import com.shadows.hkprogrammer.core.messages.values.PotmeterEndPoint;
import com.shadows.hkprogrammer.core.messages.values.ThrottleCurve;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author John
 */
public class ParameterMessage {
    private TXModel TXModelType = TXModel.Model1;
    private CraftType CraftTypeNum = CraftType.Acro;
    private boolean ReverseBitmask = false;
    private final ParameterDRValue[] DRValues = new ParameterDRValue[3];
    private final int[] Swash = new int[3];
    private final PotmeterEndPoint[] EndPoints = new PotmeterEndPoint[6];
    private final ThrottleCurve[] ThrottleCurves = new ThrottleCurve[5];
    private final PitchCurve[] PitchCurves = new PitchCurve[5];
    private final int[] Subtrim = new int[6];
    private final MixSetting[] Mixes = new MixSetting[3];
    private final SwitchFunction[] SwitchFunctions = new SwitchFunction[2];
    private final VRFunction[] VRModes = new VRFunction[2];

    public ParameterMessage() {
        Arrays.fill(DRValues, new ParameterDRValue());
        Arrays.fill(EndPoints, new PotmeterEndPoint());
        Arrays.fill(Swash, 0);
        Arrays.fill(ThrottleCurves, new ThrottleCurve());
        Arrays.fill(PitchCurves, new PitchCurve());
        Arrays.fill(Subtrim, 0);
        Arrays.fill(Mixes, new MixSetting());
        Arrays.fill(SwitchFunctions, SwitchFunction.Unassigned);
        Arrays.fill(VRModes, VRFunction.Unassigned);
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.TXModelType);
        hash = 47 * hash + Objects.hashCode(this.CraftTypeNum);
        hash = 47 * hash + (this.ReverseBitmask ? 1 : 0);
        hash = 47 * hash + Arrays.deepHashCode(this.DRValues);
        hash = 47 * hash + Arrays.hashCode(this.Swash);
        hash = 47 * hash + Arrays.deepHashCode(this.EndPoints);
        hash = 47 * hash + Arrays.deepHashCode(this.ThrottleCurves);
        hash = 47 * hash + Arrays.deepHashCode(this.PitchCurves);
        hash = 47 * hash + Arrays.hashCode(this.Subtrim);
        hash = 47 * hash + Arrays.deepHashCode(this.Mixes);
        hash = 47 * hash + Arrays.deepHashCode(this.SwitchFunctions);
        hash = 47 * hash + Arrays.deepHashCode(this.VRModes);
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
        final ParameterMessage other = (ParameterMessage) obj;
        if (this.TXModelType != other.TXModelType) {
            return false;
        }
        if (this.CraftTypeNum != other.CraftTypeNum) {
            return false;
        }
        if (this.ReverseBitmask != other.ReverseBitmask) {
            return false;
        }
        if (!Arrays.deepEquals(this.DRValues, other.DRValues)) {
            return false;
        }
        if (!Arrays.equals(this.Swash, other.Swash)) {
            return false;
        }
        if (!Arrays.deepEquals(this.EndPoints, other.EndPoints)) {
            return false;
        }
        if (!Arrays.deepEquals(this.ThrottleCurves, other.ThrottleCurves)) {
            return false;
        }
        if (!Arrays.deepEquals(this.PitchCurves, other.PitchCurves)) {
            return false;
        }
        if (!Arrays.equals(this.Subtrim, other.Subtrim)) {
            return false;
        }
        if (!Arrays.deepEquals(this.Mixes, other.Mixes)) {
            return false;
        }
        if (!Arrays.deepEquals(this.SwitchFunctions, other.SwitchFunctions)) {
            return false;
        }
        if (!Arrays.deepEquals(this.VRModes, other.VRModes)) {
            return false;
        }
        return true;
    }
    
    
    
    public TXModel getTXModelType() {
        return TXModelType;
    }

    public void setTXModelType(TXModel TXModelType) {
        this.TXModelType = TXModelType;
    }

    public CraftType getCraftTypeNum() {
        return CraftTypeNum;
    }

    public void setCraftTypeNum(CraftType CraftTypeNum) {
        this.CraftTypeNum = CraftTypeNum;
    }

    public boolean isReverseBitmask() {
        return ReverseBitmask;
    }

    public void setReverseBitmask(boolean ReverseBitmask) {
        this.ReverseBitmask = ReverseBitmask;
    }

    public ParameterDRValue[] getDRValues() {
        return DRValues;
    }

    public int[] getSwash() {
        return Swash;
    }

    public PotmeterEndPoint[] getEndPoints() {
        return EndPoints;
    }

    public ThrottleCurve[] getThrottleCurves() {
        return ThrottleCurves;
    }

    public PitchCurve[] getPitchCurves() {
        return PitchCurves;
    }

    public int[] getSubtrim() {
        return Subtrim;
    }

    public MixSetting[] getMixes() {
        return Mixes;
    }

    public SwitchFunction[] getSwitchFunction() {
        return SwitchFunctions;
    }

    public VRFunction[] getVRModes() {
        return VRModes;
    }
    
    private void ValidateDRChannel(int channelNum){
        if (channelNum > 2 || channelNum < 0){
            throw new IllegalArgumentException("DR is available only for Channel 1,Channel 2 and Channel 4!");
        }
    }
    
    private void ValidateSwashValue(int swashValue){
        if (swashValue > 127 || swashValue < 0){
            throw new IllegalArgumentException("Swash value is available only between 0 and 127!");
        }
    }
    
    private void ValidateSubtrimValue(int swashValue){
        if (swashValue > 127 || swashValue < -128){
            throw new IllegalArgumentException("Subtrim value is available only between -128 and 127!");
        }
    }
    
    public void setDRValueForChannel(DRChannel channel,ParameterDRValue value){
        int channelNum = (channel.getValue());
        this.DRValues[channelNum] = value;
    }
    
    public void setDRValueForChannel(DRChannel channel,int onValue,int offValue){
        ParameterDRValue DR = new ParameterDRValue();
        DR.setOffValue(offValue);
        DR.setOnValue(onValue);
        this.setDRValueForChannel(channel, DR);
    }
    
    public void setSwashValueForChannel(SwashChannel channel,int value){
        ValidateSwashValue(value);
        int channelNum = channel.getValue();
        this.Swash[channelNum] = value;
    }
     
    public void setEndPointValueForChannel(ControlChannel channel,PotmeterEndPoint value){
        int channelNum = (channel.getValue());        
        this.EndPoints[channelNum] = value;
    }
    
    public void setEndPointValueForChannel(ControlChannel channel,int left,int right){
        PotmeterEndPoint Point = new PotmeterEndPoint();
        Point.setLeft(left);
        Point.setRigth(right);
        this.setEndPointValueForChannel(channel, Point);
    }
    
    public void setThrottleCurveValueForChannel(HeliEndPoint point,ThrottleCurve value){
        int pointNum = (point.getValue());        
        this.ThrottleCurves[pointNum] = value;
    }
    
    public void setThrottleCurveValueForChannel(HeliEndPoint point,int normal,int ID){
        ThrottleCurve Point = new ThrottleCurve();
        Point.setID(ID);
        Point.setNormal(normal);
        this.setThrottleCurveValueForChannel(point, Point);
    }
    
    public void setPitchCurveValueForChannel(HeliEndPoint point,PitchCurve value){
        int pointNum = (point.getValue());        
        this.PitchCurves[pointNum] = value;
    }
    
    public void setPitchCurveValueForChannel(HeliEndPoint point,int normal,int ID){
        PitchCurve Point = new PitchCurve();
        Point.setID(ID);
        Point.setNormal(normal);
        this.setPitchCurveValueForChannel(point, Point);
    }
    
    public void setSubtrimValueForChannel(ControlChannel channel,int value){
        ValidateSwashValue(value);
        int channelNum = channel.getValue();
        this.Subtrim[channelNum] = value;
    }
    
    public void setMixSettingsValue(int Mix,MixSetting value){
        if (Mix < 1 || Mix > 3)
            throw new IllegalArgumentException("There is only Mix1-3 available!");
        this.Mixes[Mix-1] = value;
    }
    
    public void setMixSettingsValue(
            int Mix,
            MixDestination destination,
            MixSource source,
            MixSwitch mixSwitch,
            int Downrate,
            int Uprate
    ){
        MixSetting MSetting = new MixSetting();
        MSetting.setDestination(destination);
        MSetting.setDownrate(Downrate);
        MSetting.setSource(source);
        MSetting.setSwitch(mixSwitch);
        MSetting.setUprate(Uprate);
        this.setMixSettingsValue(Mix, MSetting);
    }
    
    public void setSwitchFunction(SwitchType switchNum,SwitchFunction value){
        int pointNum = (switchNum.getValue());        
        this.SwitchFunctions[pointNum] = value;
    }
    
    public void setVRFunction(VRType vrNum,VRFunction value){
        int pointNum = (vrNum.getValue());        
        this.VRModes[pointNum] = value;
    }
}
