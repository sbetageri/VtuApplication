package com.example.admin.vtuapplicatoin;

import android.accounts.Account;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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

    private boolean isResultOut(String USN) {
        String op = "failed";
        String base = "http://results.vtu.ac.in/vitavi.php?rid=";
        String submit = "&submit=submit";
        try {
            URL url = new URL(base + USN + submit);
//            URL url = new URL("http://requestb.in/1hf4kgz1");
            Log.e(_TAG, "networking");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            //OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            //conn.setRequestProperty("rid", "1JS12CS105");
            //conn.setRequestProperty("submit", "SUBMIT");
            conn.setDoInput(true);
            //String s = conn.getResponseMessage();
            Log.e(_TAG, "getting output");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s = "no op";
            int count = 1;
            while(count < 243) {
                s = br.readLine();
                if(count == 242) {
                    op = s;
                } else {
                    //Log.e(_TAG, s);
                }
                count += 1;
            }
            Log.e(_TAG, s);
        } catch(Exception e) {
            e.printStackTrace();
        }
        Log.e(_TAG, "length : " + Integer.toString(op.length()));
        if(op.length() > 1000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle extras,
                              String authority,
                              ContentProviderClient provider,
                              SyncResult syncResult) {
        Log.e(_TAG, " performing sync in the result adapter");
        boolean isOut = isResultOut("1JS13IS044");
        if(isOut) {
            // send notif saying results are outO
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
            NotificationManager mManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            builder = builder.setSmallIcon(R.drawable.vtu_logo);
            builder = builder.setContentTitle("RESULTS OUT");
            builder = builder.setContentText("Bro, all the best. Click this!");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://results.vtu.ac.in/vitavi.php?rid=1JS13IS044&submit=subit"));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pIntent = PendingIntent.getActivity(getContext(), 101, browserIntent, PendingIntent.FLAG_ONE_SHOT);
            builder = builder.setContentIntent(pIntent);
            mManager.notify(_TAG, 108, builder.build());
        } else {
            return;
        }
    }
}
