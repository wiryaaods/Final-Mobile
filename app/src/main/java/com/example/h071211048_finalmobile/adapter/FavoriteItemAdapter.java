package com.example.h071211048_finalmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211048_finalmobile.R;
import com.example.h071211048_finalmobile.model.FavoriteItem;

import java.util.List;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.ViewHolder>{

    private List<FavoriteItem> favoriteItemList;
    private Context context;
    

    @NonNull
    @Override
    public FavoriteItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FavoriteItemAdapter.ViewHolder holder, int position) {
        FavoriteItem favoriteItem = favoriteItemList.get(position);
//        holder.setData(favoriteItem);

    }

    @Override
    public int getItemCount() {

        return favoriteItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_posterImg, iv_logo;
        private TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_posterImg = itemView.findViewById(R.id.iv_posterimg);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

//        public void setData(FavoriteItem favoriteItem) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if ()
//                }
//            });
//        }
    }
}
