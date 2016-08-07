package com.example.admin.vtuapplicatoin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String _TAG = "main_activity";

    public static final String AUTHORITY = "com.example.admin.vtuapplicatoin.provider";

    public static final String ACCOUNT_TYPE = "sai.ram";

    public static final String ACCOUNT = "account";

    public static final long SYNC_INTERVAL = 9L;

    public ContentResolver mResolver;

    public Account mAccount;
            /*
    android:authorities="com.example.admin.vtuapplicatoin.provider"
    android:name="com.example.admin.vtuapplicatoin.ResultProvider"
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccount = createSyncAccount(this);
        mResolver = getContentResolver();
        ContentResolver.addPeriodicSync(mAccount,
                AUTHORITY,
                Bundle.EMPTY,
                SYNC_INTERVAL);
        Log.e(_TAG, "after periodic sync");
    }

    public Account createSyncAccount(Context context) {
        String accType = getResources().getString(R.string.account_type_str);

        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        Log.e(_TAG, "type: " + newAccount.type);
        Log.e(_TAG, "type: " + accType);
        AccountManager am = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);

        if (am.addAccountExplicitly(newAccount, null, null)) {
            Log.e(_TAG, "added account explicitly");
        } else {
            Log.e(_TAG, "account exists");
        }
        return newAccount;
    }
}
