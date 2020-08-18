package com.example.registration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

public class MyReceiver extends BroadcastReceiver {
    ImageView img;

    public MyReceiver(ImageView img) {
        this.img=img;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch(intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                img.setImageResource(R.drawable.connected);
                break;
            case Intent.ACTION_BATTERY_LOW:
                img.setImageResource(R.drawable.disconnected);
        }
    }
}
