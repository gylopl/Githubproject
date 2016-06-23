package makdroid.gitproject;

import android.app.Application;

import makdroid.gitproject.dagger.AppModule;
import makdroid.gitproject.dagger.DaggerNetComponent;
import makdroid.gitproject.dagger.NetComponent;
import makdroid.gitproject.dagger.NetModule;

/**
 * Created by Grzecho on 23.06.2016.
 */
public class GitApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}