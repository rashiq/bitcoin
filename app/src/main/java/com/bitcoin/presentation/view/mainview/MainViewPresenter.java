package com.bitcoin.presentation.view.mainview;

import android.support.annotation.NonNull;
import com.bitcoin.domain.interactor.LoadBitcoinPriceUseCase;
import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import com.bitcoin.presentation.view.Presenter;
import java.util.List;
import javax.inject.Inject;

public class MainViewPresenter extends Presenter {
  private MainViewCallback callback;
  private LoadBitcoinPriceUseCase loadBitcoinPriceUseCase;

  @Inject public MainViewPresenter(
      @NonNull MainViewCallback callback,
      @NonNull LoadBitcoinPriceUseCase loadBitcoinPriceUseCase) {
    this.callback = callback;
    this.loadBitcoinPriceUseCase = loadBitcoinPriceUseCase;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    loadBitcoinPriceUseCase.unsubscribe();
  }

  @Override public void onStart() {
    super.onStart();
    loadBitcoinPrice();
  }

  private void loadBitcoinPrice() {
    callback.showLoading();
    loadBitcoinPriceUseCase.execute(
        value -> callback.setData((List<BitcoinPriceViewModel>) value),
        throwable -> callback.showError(throwable.getMessage()),
        () -> callback.hideLoading()
    );
  }
}
