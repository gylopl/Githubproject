package makdroid.gitproject.dagger;

import javax.inject.Singleton;

import dagger.Component;
import makdroid.gitproject.MainActivity;

/**
 * Created by Grzecho on 23.06.2016.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
