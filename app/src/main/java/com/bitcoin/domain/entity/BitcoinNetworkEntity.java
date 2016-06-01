package com.bitcoin.domain.entity;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class BitcoinNetworkEntity {

  @SerializedName("values") private List<BitcoinValueNetworkEntity> values = new ArrayList<>();

  public List<BitcoinValueNetworkEntity> getValues() {
    return values;
  }
}