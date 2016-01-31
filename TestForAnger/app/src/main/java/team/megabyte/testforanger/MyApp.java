package team.megabyte.testforanger;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by sergey on 31/01/16.
 */
public class MyApp extends Application{

    public MyApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
    }

}
