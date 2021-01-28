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

    // Initialize Adapter variable
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
        // Set recyclerview layout
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_coin, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        // Get coin object by Json Object with position
        JSONObject jObj = mData.get(position);
        try {
            // Get icon, name, description of coin object
            String url_ico = jObj.getString("iconUrl");
            String name = jObj.getString("name");
            String description = jObj.getString("description");

            // Load image from url (GlideToVectorYou Library)
            GlideToVectorYou
                    .init()
                    .with(mActivity)
                    .withListener(new GlideToVectorYouListener() {
                        @Override
                        public void onLoadFailed() {
                            // If image not load. I am load image after that set image to background (Like WebView but it's cool).
                            GlideToVectorYou.justLoadImageAsBackground(mActivity, Uri.parse(url_ico), holder.coin_img);
                        }
                        @Override
                        public void onResourceReady() {
                            // Bug fixed when image load to background is duplicate so I set background to null.
                            holder.coin_img.setBackgroundResource(0);
                        }
                    })
                    .load(Uri.parse(url_ico), holder.coin_img);

            // Set name to TextView coin_name
            holder.coin_name.setText(name);
            // Set description to TextView coin_desc
            // Convert html string to string and replace double line feed to whitespace because I saw text description have double line feed.
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                // If your sdk over than Android N this method is improve
                holder.coin_desc.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString().replaceAll("\n\n", " ").trim());
            } else {
                holder.coin_desc.setText(Html.fromHtml(description).toString().replaceAll("\n\n", " ").trim());
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

        // Initialize View Object in recyclerview
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