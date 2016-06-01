package com.bitcoin.domain.entity;

import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "BitcoinDatabaseEntity", tableName = "bitcoin")
public class BitcoinDatabaseEntitySpec {
  public int x;
  public double y;
}
