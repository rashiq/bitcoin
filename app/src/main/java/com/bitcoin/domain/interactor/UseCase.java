package com.bitcoin.domain.interactor;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {

  private Subscription subscription = Subscriptions.empty();

  protected abstract Observable buildUseCase();

  public void execute(Action1 onNext, Action1<Throwable> onError, Action0 onComplete) {
    this.subscription = this.buildUseCase()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            onNext::call,
            throwable -> onError.call((Throwable) throwable),
            onComplete::call
        );
  }

  public void execute(Action1 onNext, Action1<Throwable> onError) {
    this.subscription = this.buildUseCase()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            onNext::call,
            throwable -> onError.call((Throwable) throwable)
        );
  }

  public void execute(Action1 onNext) {
    this.subscription = this.buildUseCase()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext::call);
  }

  public void unsubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }
}
