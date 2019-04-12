package ognev.review.presenters;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import ognev.review.network.NetworkService;
import ognev.review.views.MainView;

/**
 *
 */
public class NetworkPresenter {
  private MainView networkView;
  @Inject NetworkService networkService;

  @Inject
  public NetworkPresenter(MainView networkView) {
    this.networkView = networkView;
  }

  public void getJoke() {
    networkService.searchJob("hello", "trump")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(networkView::onJokeResponse, networkView::onJokeError);
  }
}
