package ognev.review.presenters;

import ognev.review.Sign;
import ognev.review.impl.CalcPresenter;
import ognev.review.views.MainView;

/**
 *
 */
public class PresenterImpl implements CalcPresenter {
  private MainView view;

  public PresenterImpl(MainView view) {
    this.view = view;
  }

  @Override public void calculate(String firstNumber, String secondNumber, Sign sign) {

    int res = 0;

    switch (sign) {
      case PLUS:
        res = Integer.valueOf(firstNumber) + Integer.valueOf(secondNumber);
        showResult(String.valueOf(res));
        break;
      case MINUS:
        res = Integer.valueOf(firstNumber) - Integer.valueOf(secondNumber);
        showResult(String.valueOf(res));
        break;
      case MULTIPLY:
        res = Integer.valueOf(firstNumber) * Integer.valueOf(secondNumber);
        showResult(String.valueOf(res));
        break;
      case EQUALS:
        showResult(String.valueOf(res));
        break;
    }
  }

  @Override public void showResult(String result) {
    view.displayResult(result);
  }
}
