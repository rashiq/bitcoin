package com.bitcoin.presentation.view.mainview;

import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import com.bitcoin.presentation.view.ViewCallback;
import java.util.List;

public interface MainViewCallback extends ViewCallback {
  void setData(List<BitcoinPriceViewModel> data);
}
