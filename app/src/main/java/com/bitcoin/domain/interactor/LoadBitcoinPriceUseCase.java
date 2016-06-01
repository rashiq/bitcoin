package com.bitcoin.domain.interactor;

import com.bitcoin.domain.repository.BitcoinDataRepository;
import javax.inject.Inject;
import rx.Observable;

public class LoadBitcoinPriceUseCase extends UseCase {
  private BitcoinDataRepository repository;

  @Inject public LoadBitcoinPriceUseCase(BitcoinDataRepository repository) {
    this.repository = repository;
  }

  @Override protected Observable buildUseCase() {
    return repository.bitcoins();
  }
}
