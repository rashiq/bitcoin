package com.bitcoin.domain.repository;

import com.bitcoin.domain.repository.datasource.DatabaseDataStore;
import com.bitcoin.domain.repository.datasource.NetworkDataStore;
import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import com.bitcoin.presentation.model.mapper.BitcoinViewMapper;
import com.bitcoin.presentation.repository.BitcoinRepository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class BitcoinDataRepository implements BitcoinRepository {

  private NetworkDataStore networkDataStore;
  private DatabaseDataStore databaseDataStore;

  private BitcoinViewMapper bitcoinViewMapper;

  @Inject public BitcoinDataRepository(NetworkDataStore networkDataStore,
      DatabaseDataStore databaseDataStore, BitcoinViewMapper bitcoinViewMapper) {
    this.networkDataStore = networkDataStore;
    this.databaseDataStore = databaseDataStore;
    this.bitcoinViewMapper = bitcoinViewMapper;
  }

  @Override public Observable<List<BitcoinPriceViewModel>> bitcoins() {
    if (!databaseDataStore.inTransaction() && databaseDataStore.hasData()) {
      return databaseDataStore.getBitcoinPrice()
          .flatMap(Observable::from)
          .map(bitcoinViewMapper::transform)
          .toList();
    } else {
      return networkDataStore.getBitcoinPrice()
          .flatMap(Observable::from)
          .map(bitcoinViewMapper::transform)
          .toList()
          // Save data after first request and never use network again
          .doOnCompleted(() ->
              databaseDataStore.saveEntries(networkDataStore.getBitcoinPrice())
          );
    }
  }
}
