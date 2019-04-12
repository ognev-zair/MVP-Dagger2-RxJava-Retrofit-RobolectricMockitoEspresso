package ognev.review.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class ModelWrapper<T> {
  @SerializedName("type")
  private String type;

  @SerializedName("value")
  private T value;

  public String getType() {
    return type;
  }

  public T getValue() {
    return value;
  }
}
