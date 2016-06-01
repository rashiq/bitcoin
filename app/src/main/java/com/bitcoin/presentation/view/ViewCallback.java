package com.bitcoin.presentation.view;

public interface ViewCallback {
  void showLoading();

  void hideLoading();

  void showError(String message);
}
