package android.smurf;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

import software.rsquared.restapi.RestApi;
import software.rsquared.restapi.RestApiConfiguration;

/**
 * Main class for this application
 *
 * @author Wojtek Kolendo
 */

public class SmurfApplication extends Application {

    private static SmurfApplication context;
    private static RestApiConfiguration restConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initRest();
    }

    private void initRest() {
        restConfiguration = new RestApiConfiguration()
                .setHost("195.181.221.22")
                .setPort(8080)
                .setScheme("http")
                .setTimeout(10*1000);

        RestApi.setConfiguration(restConfiguration);
    }

    public static Context getContext() {
        return context;
    }
}
