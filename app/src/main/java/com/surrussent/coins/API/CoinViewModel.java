package com.surrussent.coins.API;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinViewModel extends ViewModel {

    // Initialize live data
    private final MutableLiveData<Map> mutableLiveData = new MutableLiveData<>();

    // Initialize variable
    private static CountDownTimer countDownTimer;

    public CoinViewModel() {
        fetchData();
    }

    public void fetchData() {
        // Call API by okhttp and retrofit with Method GET
        HttpManager.getInstance().getService().getAPICoins().enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()) {
                    // Use observable to subscribe data
                    Observable.just(response.body())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(subscribeData());
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                // Reconnect when error occurs (10s)
                if (countDownTimer != null) countDownTimer.cancel();
                countDownTimer = new CountDownTimer(10000, 1000) {
                    public void onFinish() {
                        fetchData();
                    }
                    public void onTick(long millisUntilFinished) { }
                }.start();
            }
        });
    }

    private Observer<Map> subscribeData() {
        return new Observer<Map>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Map item) {
                mutableLiveData.setValue(item);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<Map> getCoinList() {
        return mutableLiveData;
    }

    public static CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public static void cancelCountDownTimer() {
        countDownTimer.cancel();
    }
}
