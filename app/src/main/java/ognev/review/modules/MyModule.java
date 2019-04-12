package ognev.review.modules;

import dagger.Module;
import dagger.Provides;
import java.util.HashSet;
import javax.inject.Singleton;
import ognev.review.presenters.NetworkPresenter;
import ognev.review.presenters.PresenterImpl;
import ognev.review.impl.CalcPresenter;
import ognev.review.views.MainView;
import ognev.review.views.NetworkView;

/**
 *
 */
@Module
public class MyModule {
  private MainView mainView;

  public MyModule(MainView mainView) {
    this.mainView = mainView;
  }

  @Provides MainView provideMainView() {
    return mainView;
  }

  @Provides
  @Singleton
  static CalcPresenter provideCalcPresenter(MainView mainView) {
    return new PresenterImpl(mainView);
  }

  //@Provides
  //@Singleton NetworkPresenter provideNetworkPresenter(MainView networkView) {
  //  return new NetworkPresenter(networkView);
  //}
}
