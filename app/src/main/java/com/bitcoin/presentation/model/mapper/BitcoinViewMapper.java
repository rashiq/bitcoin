package com.bitcoin.presentation.model.mapper;

import com.bitcoin.domain.entity.BitcoinDatabaseEntity;
import com.bitcoin.domain.entity.BitcoinValueNetworkEntity;
import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BitcoinViewMapper {

  @Inject public BitcoinViewMapper() {
  }

  public BitcoinPriceViewModel transform(BitcoinValueNetworkEntity entity) {
    BitcoinPriceViewModel model = new BitcoinPriceViewModel();
    model.setX(entity.getX());
    model.setY(entity.getY());
    return model;
  }

  public BitcoinPriceViewModel transform(BitcoinDatabaseEntity entity) {
    BitcoinPriceViewModel model = new BitcoinPriceViewModel();
    model.setX(entity.getX());
    model.setY(entity.getY());
    return model;
  }
}
