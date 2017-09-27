package inject;

import android.app.Application;

import com.example.manolo.weather.App;
import com.example.manolo.weather.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import service.ServiceModule;

/**
 * Created by manolofernandez on 9/26/17.
 */

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}