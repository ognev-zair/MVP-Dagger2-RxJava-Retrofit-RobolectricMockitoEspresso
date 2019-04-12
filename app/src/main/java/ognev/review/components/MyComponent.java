package ognev.review.components;

import dagger.Component;
import javax.inject.Singleton;
import ognev.review.MainActivity;
import ognev.review.modules.MyModule;
import ognev.review.modules.NetworkModule;
import ognev.review.presenters.NetworkPresenter;

/**
 *
 */
@Singleton
@Component(modules = {MyModule.class, NetworkModule.class})
public interface MyComponent {

  void inject(MainActivity activity);

  void inject(NetworkPresenter presenter);
}
