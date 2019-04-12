package ognev.review.network;

import io.reactivex.Flowable;
import ognev.review.models.Joke;
import ognev.review.models.ModelWrapper;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 *///https://jobs.search.gov/jobs/search.json?query=nursing+jobs&tags=federal
public interface NetworkService {

  @GET("/jokes/random")
  Flowable<ModelWrapper<Joke>> searchJob(@Query("firstName") String query, @Query("lastName") String tags);
}
