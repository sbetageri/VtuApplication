package com.example.admin.vtuapplicatoin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String _TAG = "main_activity";

    public static final String AUTHORITY = "com.example.admin.vtuapplicatoin.provider";

    public static final String ACCOUNT_TYPE = "results.vtu.ac.in";

    public static final String ACCOUNT = "an_account";

    public Account mAccount;
            /*
    android:authorities="com.example.admin.vtuapplicatoin.provider"
    android:name="com.example.admin.vtuapplicatoin.ResultProvider"
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Account createSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager am = (AccountManager)context.getSystemService(ACCOUNT_SERVICE);

        if(am.addAccountExplicitly(newAccount, null, null)) {
            Log.e(_TAG, "added account explicitly");
        } else {
            Log.e(_TAG, "some error");
        }
        return newAccount;
    }
}
