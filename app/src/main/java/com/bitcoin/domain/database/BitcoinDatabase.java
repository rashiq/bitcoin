package com.bitcoin.domain.database;

import android.content.Context;
import com.bitcoin.domain.entity.BitcoinDatabaseEntity;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.data.adapter.SQLiteDatabaseWrapper;
import com.yahoo.squidb.sql.Table;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BitcoinDatabase extends SquidDatabase {

  private static final int VERSION = 1;

  @Inject public BitcoinDatabase(Context context) {
    super(context);
  }

  @Override public String getName() {
    return "bitcoin.db";
  }

  @Override protected Table[] getTables() {
    return new Table[] {
        BitcoinDatabaseEntity.TABLE
    };
  }

  @Override protected boolean onUpgrade(SQLiteDatabaseWrapper db, int oldVersion, int newVersion) {
    return false;
  }

  @Override protected int getVersion() {
    return VERSION;
  }
}