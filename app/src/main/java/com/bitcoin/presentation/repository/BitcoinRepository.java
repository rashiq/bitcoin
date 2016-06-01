package com.bitcoin.presentation.repository;

import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import java.util.List;
import rx.Observable;

public interface BitcoinRepository {

  Observable<List<BitcoinPriceViewModel>> bitcoins();
}
