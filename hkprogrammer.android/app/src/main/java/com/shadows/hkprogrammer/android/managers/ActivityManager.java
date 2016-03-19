package com.shadows.hkprogrammer.android.managers;

import android.app.Activity;
import android.content.Intent;

import com.shadows.hkprogrammer.android.activities.SelectProviderActivity;

/**
 * Created by John on 17.3.2016 г..
 */
public class ActivityManager {
    public static void OpenSelectProviderActivity(Activity parent){
        parent.startActivity(new Intent(parent, SelectProviderActivity.class));
    }
}
