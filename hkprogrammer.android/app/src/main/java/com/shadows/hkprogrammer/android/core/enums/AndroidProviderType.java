package com.shadows.hkprogrammer.android.core.enums;

/**
 * Created by John on 17.3.2016 г..
 */
public enum AndroidProviderType {
    Bluetooth(1),
    USB(0);

    private int value;

    AndroidProviderType(int val){
        this.value = val;
    }

    public int getValue(){
        return this.value;
    }

    public static int[] allValues(){
        return new int[]{
            Bluetooth.value
        };
    }

    public static AndroidProviderType fromInteger(int value){
        switch (value){
            case 0:
                return Bluetooth;
        }
        throw new IllegalArgumentException("AndroidProviderType only accepts values between 0 and 0!");
    }
}
