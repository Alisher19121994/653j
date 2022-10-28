package com.example.a653j;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcast extends BroadcastReceiver {

    String SMS = "android.provider.Telephony.SMS_RECEIVED";
    String msg;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(SMS)) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] objects = (Object[]) bundle.get("sms");
                SmsMessage[] messages = new SmsMessage[objects.length];

                for (int i = 0; i < objects.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {

                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
                    }
                    msg = messages[i].getMessageBody();

                }
            }
            Toast.makeText(context, "Message: " + msg, Toast.LENGTH_SHORT).show();
        }
    }
}
