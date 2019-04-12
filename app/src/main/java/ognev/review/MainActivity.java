package ognev.review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import ognev.review.components.DaggerMyComponent;
import ognev.review.components.MyComponent;
import ognev.review.impl.CalcPresenter;
import ognev.review.models.Joke;
import ognev.review.models.ModelWrapper;
import ognev.review.modules.MyModule;
import ognev.review.modules.NetworkModule;
import ognev.review.network.NetworkService;
import ognev.review.presenters.NetworkPresenter;
import ognev.review.views.MainView;
import org.reactivestreams.Subscription;

public class MainActivity extends AppCompatActivity implements MainView {

  @Inject CalcPresenter calcPresenter;
  @Inject NetworkPresenter networkPresenter;

  @BindView(R.id.input)
  TextView input;

  private StringBuilder inputBuilder;
  private String s1;
  private String s2;
  private Sign sign;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    inputBuilder = new StringBuilder();

    createComponent(this).inject(this);

    networkPresenter.getJoke();

  }


  @OnClick({R.id.num_0, R.id.num_1, R.id.num_2,
  R.id.num_3, R.id.num_4, R.id.num_5, R.id.num_6,
      R.id.num_7, R.id.num_8, R.id.num_9, R.id.minus,
      R.id.plus, R.id.multiply, R.id.equals})
  public void onNumClicked(View view) {
    switch (view.getId()) {
      case R.id.num_0:
        append("0");
        break;
      case R.id.num_1:
        append("1");
        break;
      case R.id.num_2:
        append("2");
        break;
      case R.id.num_3:
        append("3");
        break;
      case R.id.num_4:
        append("4");
        break;
      case R.id.num_5:
        append("5");
        break;
      case R.id.num_6:
        append("6");
        break;
      case R.id.num_7:
        append("7");
        break;
      case R.id.num_8:
        append("8");
        break;
      case R.id.num_9:
        append("9");
        break;
      case R.id.minus:
        sign = Sign.MINUS;
        if(TextUtils.isEmpty(s1)) {
          s1 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        if(TextUtils.isEmpty(s2)) {
          s2 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        break;
      case R.id.plus:
        sign = Sign.PLUS;

        if(TextUtils.isEmpty(s1)) {
          s1 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        if(TextUtils.isEmpty(s2)) {
          s2 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        break;
      case R.id.multiply:
        sign = Sign.MULTIPLY;
        if(TextUtils.isEmpty(s1)) {
          s1 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        if(TextUtils.isEmpty(s2)) {
          s2 = inputBuilder.toString();
          inputBuilder.setLength(0);
        }
        break;
      case R.id.equals:
        //

        if(TextUtils.isEmpty(s2)) {
          s2 = inputBuilder.toString();
        }
        if(sign == Sign.EQUALS) {
          input.setText("");
          inputBuilder.setLength(0);
        }
        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {

          calcPresenter.calculate(s1, s2, sign);
          s1 = null;
          s2 = null;
          sign = Sign.EQUALS;
        }


        break;
    }
  }

  private void append(String n) {

    inputBuilder.append(n);
    input.setText(inputBuilder.toString());
  }

  private MyComponent createComponent(MainView mainView) {
    return DaggerMyComponent.builder()
        .myModule(new MyModule(mainView))
        .networkModule(new NetworkModule(getApplication()))
        .build();
  }

  @Override public void displayResult(String result) {
    input.setText(result);
  }

  @Override public void onJokeResponse(ModelWrapper<Joke> o) {
      Toast.makeText(getApplicationContext(), o.getValue().getJoke(), Toast.LENGTH_SHORT)
          .show();
  }

  @Override public void onJokeError(Throwable t) {

  }
}
