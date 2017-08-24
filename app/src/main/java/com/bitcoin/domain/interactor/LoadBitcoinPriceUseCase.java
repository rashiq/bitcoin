package com.bitcoin.domain.interactor;

import com.bitcoin.domain.repository.BitcoinRepository;
import javax.inject.Inject;
import rx.Observable;

public class LoadBitcoinPriceUseCase extends UseCase {
  private BitcoinRepository repository;

  @Inject public LoadBitcoinPriceUseCase(BitcoinRepository repository) {
    this.repository = repository;
  }

  @Override protected Observable buildUseCase() {
    return repository.bitcoins();
  }
}
