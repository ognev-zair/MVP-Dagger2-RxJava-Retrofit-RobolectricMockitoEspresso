package ognev.review.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class Joke {
  @SerializedName("id")
  private int id;
  @SerializedName("joke")
  private String joke;


  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getJoke() {
    return joke;
  }
}
