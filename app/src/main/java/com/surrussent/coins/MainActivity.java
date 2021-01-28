package com.surrussent.coins;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.surrussent.coins.API.HttpManager;
import com.surrussent.coins.Adapter.CoinsAdapter;
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
    private RecyclerView recycle_coin;
    private CoinsAdapter coinsAdapter;
    private List<JSONObject> mCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recycle_coin = findViewById(R.id.reccoin);
        recycle_coin.setHasFixedSize(true);
        recycle_coin.setLayoutManager(linearLayoutManager);

        loadData();
    }

    private void loadData() {
        Call<Collection> call = HttpManager.getInstance().getService().getAPICoins();
        call.enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(Call<Collection> call, Response<Collection> response) {
                if (response.isSuccessful()) {
                    Collection dao = response.body();
                    List coins = dao.getData().getCoins();
                    int size_coins = coins.size();
                    mCoins = new ArrayList<JSONObject>();
                    JSONArray coin_arr = new JSONArray(coins);
                    for (int i=0; i < size_coins; i++) {
                        try {
                            JSONObject jObj = coin_arr.getJSONObject(i);
                            mCoins.add(jObj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    coinsAdapter = new CoinsAdapter(getApplicationContext(), mCoins, MainActivity.this);
                    recycle_coin.setAdapter(coinsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Collection> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , t.toString()
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}