package com.bitcoin.domain.repository.datasource;

import com.bitcoin.domain.database.BitcoinDatabase;
import com.bitcoin.domain.entity.BitcoinDatabaseEntity;
import com.bitcoin.domain.entity.BitcoinValueNetworkEntity;
import com.bitcoin.presentation.model.mapper.BitcoinDatabaseMapper;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseDataStore {
  private BitcoinDatabase db;
  private BitcoinDatabaseMapper bitcoinDatabaseMapper;

  @Inject
  public DatabaseDataStore(BitcoinDatabase database, BitcoinDatabaseMapper bitcoinDatabaseMapper) {
    this.db = database;
    this.bitcoinDatabaseMapper = bitcoinDatabaseMapper;
  }

  public Observable<List<BitcoinDatabaseEntity>> getBitcoinPrice() {
    return Observable.fromAsync((Action1<AsyncEmitter<BitcoinDatabaseEntity>>) emitter -> {
      SquidCursor<BitcoinDatabaseEntity> cursor = db.query(
          BitcoinDatabaseEntity.class,
          Query.select().orderBy(BitcoinDatabaseEntity.ID.asc())
      );
      try {
        while (cursor.moveToNext()) {
          BitcoinDatabaseEntity entity = new BitcoinDatabaseEntity();
          entity.readPropertiesFromCursor(cursor);
          emitter.onNext(entity);
        }
      } catch (Exception e) {
        emitter.onError(e);
      } finally {
        cursor.close();
        emitter.onCompleted();
      }
    }, AsyncEmitter.BackpressureMode.BUFFER).toList();
  }

  public boolean inTransaction() {
    return db.inTransaction();
  }

  public boolean hasData() {
    return db.countAll(BitcoinDatabaseEntity.class) > 0;
  }

  public void saveEntries(Observable<List<BitcoinValueNetworkEntity>> entities) {
    entities.flatMap(Observable::from)
        .map(bitcoinDatabaseMapper::transform)
        .subscribeOn(Schedulers.io())
        .subscribe(db::persist);
  }
}
