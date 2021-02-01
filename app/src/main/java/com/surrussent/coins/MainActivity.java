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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

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
    private JSONArray mCoins = new JSONArray();

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
        Call<Map> call = HttpManager.getInstance().getService().getAPICoins();
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    // Map object to json objects
                    assert response.body() != null;
                    JSONObject obj_data = new JSONObject(response.body());
                    try {
                        // Create coins object to json array
                        mCoins = obj_data.getJSONObject("data").getJSONArray("coins");

                        // Set variable coins adapter to config display
                        // - Put Application context to adapter
                        // - Put mCoins (json array of coins object)
                        // - Put Activity of MainActivity to adapter
                        coinsAdapter = new CoinsAdapter(getApplicationContext(), mCoins, MainActivity.this);
                        // Display coin to recyclerview
                        recycler_coin.setAdapter(coinsAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
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
        if (mCoins.length() == 0) loadData();
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