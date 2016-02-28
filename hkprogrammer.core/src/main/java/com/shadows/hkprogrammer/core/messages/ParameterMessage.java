/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core.messages;

import com.shadows.hkprogrammer.core.messages.values.MixSetting;
import com.shadows.hkprogrammer.core.messages.values.ParameterDRValue;
import com.shadows.hkprogrammer.core.messages.values.PitchCurve;
import com.shadows.hkprogrammer.core.messages.values.PotmeterEndPoint;
import com.shadows.hkprogrammer.core.messages.values.ThrottleCurve;

/**
 *
 * @author John
 */
public class ParameterMessage {
    private int TXModel;
    private int CraftType;
    private boolean ReverseBitmask;
    private final ParameterDRValue[] DRValues = new ParameterDRValue[3];
    private final int[] Swash = new int[3];
    private final PotmeterEndPoint[] EndPoints = new PotmeterEndPoint[6];
    private final ThrottleCurve[] ThrottleCurves = new ThrottleCurve[5];
    private final PitchCurve[] PitchCurves = new PitchCurve[5];
    private final int[] Subtrim = new int[6];
    private final MixSetting[] Mixes = new MixSetting[3];
    private final int[] SwitchFunction = new int[2];
    private final int[] VRModes = new int[2];
}
