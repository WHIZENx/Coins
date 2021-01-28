package com.surrussent.coins.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.surrussent.coins.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<JSONObject> mData;
    private final Activity mActivity;

    public CoinsAdapter(Context mContext, List<JSONObject> mData, Activity mActivity) {
        this.mContext = mContext;
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_coin, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        JSONObject jObj = mData.get(position);
        try {
            String url_img = jObj.getString("iconUrl");
            String name = jObj.getString("name");
            String description = jObj.getString("description");

            GlideToVectorYou
                    .init()
                    .with(mActivity)
                    .withListener(new GlideToVectorYouListener() {
                        @Override
                        public void onLoadFailed() {
                            GlideToVectorYou.justLoadImageAsBackground(mActivity, Uri.parse(url_img), holder.coin_img);
                        }
                        @Override
                        public void onResourceReady() {
                            holder.coin_img.setBackgroundResource(0);
                        }
                    }).load(Uri.parse(url_img), holder.coin_img);

            holder.coin_name.setText(name);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                holder.coin_desc.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString().replaceAll("\n", "").trim());
            } else {
                holder.coin_desc.setText(Html.fromHtml(description).toString().replaceAll("\n", "").trim());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView coin_name;
        TextView coin_desc;
        ImageView coin_img;

        public MyViewHolder(View itemView) {
            super(itemView);

            coin_name = itemView.findViewById(R.id.coin_name);
            coin_desc = itemView.findViewById(R.id.coin_desc);
            coin_img = itemView.findViewById(R.id.coin_img);
        }
    }
}