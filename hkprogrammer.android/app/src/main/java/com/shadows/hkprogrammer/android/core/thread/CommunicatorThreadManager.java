package com.shadows.hkprogrammer.android.core.thread;

import com.shadows.hkprogrammer.android.MainActivity;
import com.shadows.hkprogrammer.core.client.ParameterSyncTask;
import com.shadows.hkprogrammer.core.communication.Communicator;
import com.shadows.hkprogrammer.core.communication.ICommunicationProvider;

/**
 * Created by John on 19.3.2016 г..
 */
public class CommunicatorThreadManager{
    private final Communicator communicator;
    private final MainActivity activity;

    public CommunicatorThreadManager(Communicator communicator, MainActivity activity) {
        this.communicator = communicator;
        this.activity = activity;
    }

    public void RunSyncParametersTask(){
        ParameterSyncTask task = new ParameterSyncTask(communicator);

    }
}
