package com.bitcoin.presentation.view.mainview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bitcoin.R;
import com.bitcoin.presentation.di.components.ApplicationComponent;
import com.bitcoin.presentation.di.modules.MainViewModule;
import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import com.bitcoin.presentation.view.BaseActivity;
import com.bitcoin.presentation.view.chart.LineChart;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainViewCallback {
  @Inject MainViewPresenter presenter;
  @BindView(R.id.chart) LineChart lineChart;
  @BindView(R.id.progress) ProgressBar progressBar;
  private Unbinder unbinder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    unbinder = ButterKnife.bind(this);
    initChart();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  private void initChart() {
    lineChart.setLegendLineColor(Color.DKGRAY);
    lineChart.setLegendTextColor(Color.WHITE);
    lineChart.setChartColor(Color.parseColor("#1976D2"));
  }

  @Override public void attachPresenter() {
    attachPresenter(presenter);
  }

  @Override public void showLoading() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override public void setData(List<BitcoinPriceViewModel> data) {
    lineChart.setValues(data);
  }

  @Override protected void setupDagger(ApplicationComponent appComponent) {
    appComponent.plus(new MainViewModule(this)).inject(this);
  }

  public interface Injector {
    void inject(MainActivity mainActivity);
  }
}
