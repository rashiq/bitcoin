package com.bitcoin.presentation.di.modules;

import com.bitcoin.domain.network.BitcoinService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class NetworkModule {
  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

  @Provides @Singleton BitcoinService provideBitcoinService(OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://blockchain.info/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .build();
    return retrofit.create(BitcoinService.class);
  }
}
