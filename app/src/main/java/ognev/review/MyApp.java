package ognev.review;

import android.app.Application;
import ognev.review.components.DaggerMyComponent;
import ognev.review.components.MyComponent;
import ognev.review.modules.MyModule;
import ognev.review.views.MainView;

/**
 *
 */
public class MyApp extends Application {

  private static MyApp instance;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
  }

  public static MyApp getInstance() {
    return instance;
  }

  //public void setComponent(MyComponent component) {
  //  this.component = component;
  //}

  //public MyComponent getComponent() {
  //  return component;
  //}


}
