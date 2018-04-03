package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.general.Constants;

/**
 * Created by Florentina on 03-Apr-18.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private String points;

    public ProcessingThread(Context context, String pointsOutput) {
        this.context = context;

        points = pointsOutput;
    }

    @Override
    public void run() {
        sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionType);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " "  + points);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
