package com.example.admin.vtuapplicatoin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Admin on 07-08-2016.
 */
public class ResultSyncAdapterService extends Service {
    private static ResultSyncAdapter mSyncAdapter;
    public static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if(mSyncAdapter == null) {
                mSyncAdapter = new ResultSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mSyncAdapter.getSyncAdapterBinder();
    }
}
