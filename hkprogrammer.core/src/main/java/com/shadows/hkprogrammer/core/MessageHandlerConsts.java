/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.core;

/**
 *
 * @author John
 */
public class MessageHandlerConsts {
    public static final byte HeaderBeggining = (byte)0x55;
    public static final byte HeaderPosition = (byte)0xFC;
    public static final byte HeaderParameterRequest = (byte)0xFA;
    public static final byte HeaderParameterDump = (byte)0xFD;
    public static final byte HeaderParameterSet = (byte)0xFF;
    public static final int msgPositionLength = 18;
    public static final int msgParameterRequestLength = 3;
    public static final int msgParameterDumpLength = 68;
    public static final int msgParameterSetLength = 68;
}
