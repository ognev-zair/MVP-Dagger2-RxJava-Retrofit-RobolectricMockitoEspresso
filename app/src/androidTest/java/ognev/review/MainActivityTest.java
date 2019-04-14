package ognev.review;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<MainActivity>(MainActivity.class) {

    @Override protected void beforeActivityLaunched() {
      //MyApp myApp = (MyApp) InstrumentationRegistry.
      //    getInstrumentation().getTargetContext().getApplicationContext();
      //MyComponent component = DaggerMyComponentMock
      //    .builder()
      //    .myModuleMock(new MyModuleMock(testRule.getActivity()))
      //    .build();

    }
  };

  @Test
  public void testPlus() {
    onView(withId(R.id.num_4)).perform(click());
    onView(withId(R.id.plus)).perform(click());
    onView(withId(R.id.num_3)).perform(click());
    onView(withId(R.id.equals)).perform(click());
    onView(withId(R.id.input))
        .check(matches(withText("7")));
  }

  @Test
  public void testMinus() {
    onView(withId(R.id.num_4)).perform(click());
    onView(withId(R.id.minus)).perform(click());
    onView(withId(R.id.num_3)).perform(click());
    onView(withId(R.id.equals)).perform(click());
    onView(withId(R.id.input))
        .check(matches(withText("1")));
  }

  @Test
  public void showToast() {
    //onView(withId(R.id.input))
    //    .check(matches(withText("Test")));
  }
}
