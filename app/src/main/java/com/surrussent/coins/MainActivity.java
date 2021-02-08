package com.surrussent.coins;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surrussent.coins.API.CoinViewModel;
import com.surrussent.coins.Adapter.CoinsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Initialize ViewModel object
    private CoinViewModel coinViewModel;

    // Initialize layout object
    private ProgressBar progressBar;

    // Initialize Recyclerview to show coins object
    private RecyclerView recycler_coin;
    private CoinsAdapter coinsAdapter;

    // Initialize List of coins to empty
    private JSONArray mCoins = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // provider view model from ViewModel class
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);

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
        // Call and get data from view model
        coinViewModel.getCoinList().observe(MainActivity.this, item -> {
            progressBar.setVisibility(View.GONE);
            // Map object to json objects
            JSONObject obj_data = new JSONObject(item);
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
        if (CoinViewModel.getCountDownTimer() != null) CoinViewModel.cancelCountDownTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When close app countdown timer is cancel
        if (CoinViewModel.getCountDownTimer() != null) CoinViewModel.cancelCountDownTimer();
    }
}