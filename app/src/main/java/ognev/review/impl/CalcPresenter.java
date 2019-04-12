package ognev.review.impl;

import ognev.review.Sign;

/**
 *
 */
public interface CalcPresenter {

  void calculate(String firstNumber, String secondNumber, Sign sign);

  void showResult(String result);

}
