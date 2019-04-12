package ognev.review.views;

import ognev.review.models.Joke;
import ognev.review.models.ModelWrapper;

/**
 *
 */
public interface MainView {

  void displayResult(String result);

  void onJokeResponse(ModelWrapper<Joke> o);
  void onJokeError(Throwable t);
}
