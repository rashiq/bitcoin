package com.bitcoin.presentation.model.mapper;

import com.bitcoin.domain.entity.BitcoinDatabaseEntity;
import com.bitcoin.domain.entity.BitcoinValueNetworkEntity;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BitcoinDatabaseMapper {

  @Inject public BitcoinDatabaseMapper() {
  }

  public BitcoinDatabaseEntity transform(BitcoinValueNetworkEntity networkEntity) {
    BitcoinDatabaseEntity databaseEntity = new BitcoinDatabaseEntity();
    databaseEntity.setX(networkEntity.getX());
    databaseEntity.setY(networkEntity.getY());
    return databaseEntity;
  }
}
