package com.bitcoin.domain.repository.datasource;

import com.bitcoin.domain.entity.BitcoinValueNetworkEntity;
import com.bitcoin.domain.network.BitcoinService;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

@Singleton
public class NetworkDataStore {

  private BitcoinService bitcoinService;

  @Inject public NetworkDataStore(BitcoinService bitcoinService) {
    this.bitcoinService = bitcoinService;
  }

  public Observable<List<BitcoinValueNetworkEntity>> getBitcoinPrice() {
    return bitcoinService.getBitcoinPrice()
        .flatMap(bitcoinNetworkEntity -> Observable.from(bitcoinNetworkEntity.getValues()))
        .toList();
  }
}
