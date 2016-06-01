package com.bitcoin.domain.network;

import com.bitcoin.domain.entity.BitcoinNetworkEntity;
import retrofit2.http.GET;
import rx.Observable;

public interface BitcoinService {
  @GET("/charts/market-price?format=json") Observable<BitcoinNetworkEntity> getBitcoinPrice();
}
