package com.example.admin.vtuapplicatoin;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Admin on 07-08-2016.
 */
public class ResultSyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String _TAG = "result_sync_adapter";


    public ResultSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    public ResultSyncAdapter(Context context,
                             boolean autoInitialize,
                             boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle extras,
                              String authority,
                              ContentProviderClient provider,
                              SyncResult syncResult) {
        CountSingleton obj = CountSingleton.getInstance();
        obj.count += 1;
        Log.e(_TAG, " performing sync in the result adapter");
    }
}
