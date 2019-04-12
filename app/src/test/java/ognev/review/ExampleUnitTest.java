package ognev.review;

import ognev.review.impl.CalcPresenter;
import ognev.review.models.Joke;
import ognev.review.presenters.NetworkPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, application = MyApp.class)
public class ExampleUnitTest {
  private MainActivity activity;
  private CalcPresenter presenter;
  private NetworkPresenter netPresenter;

  @Before
  public void setup() {
    activity = Mockito.spy(Robolectric.buildActivity(MainActivity.class)
        .create()
        .visible()
        .get());
    presenter = Mockito.spy(activity.calcPresenter);
    netPresenter = Mockito.spy(activity.networkPresenter);
  }
  @Test public void addition_isCorrect() {
    //Joke car = Mockito.mock(Joke.class);
    //
    //when(car.getName()).thenReturn("BMW");
    //when(car.getId()).thenReturn(43);
    //
    //car.setId(12);
    //car.getId();
    //car.getId();
    //
    //verify(car).setId(ArgumentMatchers.eq(12));
    //
    //verify(car, times(2)).getId();
    //
    //verify(car, never()).setName("BMW");
    //verify(car, atLeastOnce()).getId();
    //verify(car, atLeast(2)).getId();
    ////verify(car, times(5)).setName("never called 5 times");
    //verify(car, atMost(5)).getId();
    //assertEquals("BMW", car.getName());
  }

  @Test
  public void testService() {
    netPresenter.getJoke();
    verify(netPresenter).getJoke();
  }
  @Test
  public void testAndroidActivity() {
    assertNotNull(activity);
    assertNotNull(activity.calcPresenter);
  }

  @Test
  public void testDisplayResult() {
    activity.displayResult("20");
    assertEquals(activity.input.getText().toString(), "20");
    verify(activity).displayResult("20");
  }

  @Test
  public void testPlus() {
    presenter.calculate("10", "2", Sign.PLUS);
    assertEquals(activity.input.getText().toString(), "12");
    verify(presenter).showResult("12");
  }

  @Test
  public void testMinus() {
    presenter.calculate("10", "2", Sign.MINUS);
    assertEquals(activity.input.getText().toString(), "8");
    verify(presenter).showResult("8");
  }

  @Test
  public void testMultiply() {
    presenter.calculate("10", "2", Sign.MULTIPLY);
    assertEquals(activity.input.getText().toString(), "20");
    verify(presenter).showResult("20");
  }


  @Test
  public void testEquals() {
    presenter.calculate("10", "2", Sign.MULTIPLY);
    assertEquals(activity.input.getText().toString(), "20");
    verify(presenter).showResult("20");
    presenter.calculate("10", "2", Sign.EQUALS);
    presenter.calculate("10", "2", Sign.EQUALS);
    verify(presenter, times(2)).showResult("0");
    verify(presenter, times(2)).calculate("10", "2", Sign.EQUALS);

  }
}