package com.bitcoin.domain.entity;

import com.google.gson.annotations.SerializedName;

public class BitcoinValueNetworkEntity {

  @SerializedName("x") public int x;
  @SerializedName("y") public double y;

  public int getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}