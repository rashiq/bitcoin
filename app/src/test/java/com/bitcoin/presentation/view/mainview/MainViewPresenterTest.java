package com.bitcoin.presentation.view.mainview;

import com.bitcoin.domain.interactor.LoadBitcoinPriceUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.functions.Action0;
import rx.functions.Action1;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainViewPresenterTest {

  private MainViewPresenter mainViewPresenter;

  @Mock MainViewCallback mainViewCallback;
  @Mock LoadBitcoinPriceUseCase loadBitcoinPriceUseCase;

  @Before
  public void setUp() throws Exception {
    mainViewPresenter = new MainViewPresenter(mainViewCallback, loadBitcoinPriceUseCase);
  }

  @Test
  public void testLoadBitcoinPrice() {
    mainViewPresenter.onStart();
    verify(mainViewCallback).showLoading();
    verify(loadBitcoinPriceUseCase).execute(any(Action1.class), any(Action1.class), any(Action0.class));
  }
}