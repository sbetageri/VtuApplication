package com.example.admin.vtuapplicatoin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Admin on 06-08-2016.
 */
public class VtuAccountService extends Service {
    public VtuAccountAuth mAccount;

    @Override
    public void onCreate() {
        super.onCreate();
        mAccount = new VtuAccountAuth(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAccount.getIBinder();
    }
}
