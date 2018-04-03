package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.general.Constants;

/**
 * Created by Florentina on 03-Apr-18.
 */

public class PracticalTest01Var01Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String pointsOutput = intent.getStringExtra(Constants.SERVICESTRING);

        processingThread = new ProcessingThread(this, pointsOutput);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
