package com.surrussent.coins;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surrussent.coins.API.HttpManager;
import com.surrussent.coins.Adapter.CoinsAdapter;
import com.surrussent.coins.Modal.Coins;
import com.surrussent.coins.Modal.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Initialize View Object
    private ProgressBar progressBar;

    // Initialize Recyclerview to show coins object
    private RecyclerView recycler_coin;
    private CoinsAdapter coinsAdapter;

    // Initialize List of coins to empty
    private List<JSONObject> mCoins = new ArrayList<JSONObject>();

    // Initialize variable
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recycler_coin = findViewById(R.id.recycler_coin);

        // Set Recyclerview is fit screen size.
        recycler_coin.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_coin.setLayoutManager(linearLayoutManager);

        // Load data from API.
        loadData();
    }

    private void loadData() {
        // Call API by okhttp and retrofit with Method GET
        Call<Collection> call = HttpManager.getInstance().getService().getAPICoins();
        call.enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(Call<Collection> call, Response<Collection> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    // String json to data access objects
                    Collection dao = response.body();

                    // Get list coins in data access objects
                    List<Coins> coins = dao.getData().getCoins();
                    // Size of list coins
                    int size_coins = coins.size();

                    // Reset List of coins to empty
                    mCoins = new ArrayList<JSONObject>();

                    // Convert coins object to json array
                    JSONArray coin_arr = new JSONArray(coins);

                    for (int i=0; i < size_coins; i++) {
                        try {
                            // Add coin object of coins array by index of json object
                            mCoins.add(coin_arr.getJSONObject(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // Set variable coins adapter to config display
                    // - Put Application context to adapter
                    // - Put mCoins (list of coins object
                    // - Put Activity to adapter
                    coinsAdapter = new CoinsAdapter(getApplicationContext(), mCoins, MainActivity.this);
                    // Display coin to recyclerview
                    recycler_coin.setAdapter(coinsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Collection> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                // Show exception error
                Toast.makeText(MainActivity.this
                        , t.toString()
                        , Toast.LENGTH_LONG)
                        .show();

                // Reconnect when error occurs (10s)
                if (countDownTimer != null) countDownTimer.cancel();
                countDownTimer = new CountDownTimer(10000, 1000) {
                    public void onFinish() {
                        loadData();
                    }
                    public void onTick(long millisUntilFinished) { }
                }.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // When start app if array coins empty, It call load data
        if (mCoins.size() == 0) loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When stop app countdown timer is cancel
        if (countDownTimer != null) countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When close app countdown timer is cancel
        if (countDownTimer != null) countDownTimer.cancel();
    }
}