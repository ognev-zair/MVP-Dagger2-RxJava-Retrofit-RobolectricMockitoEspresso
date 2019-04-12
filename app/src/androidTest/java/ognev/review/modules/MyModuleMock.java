package ognev.review.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import ognev.review.PresenterImplMock;
import ognev.review.impl.CalcPresenter;
import ognev.review.views.MainView;

/**
 *
 */
@Module
public class MyModuleMock extends MyModule {

  public MyModuleMock(MainView mainView) {
    super(mainView);
  }

}
