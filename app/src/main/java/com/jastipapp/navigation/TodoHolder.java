package com.jastipapp.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TodoHolder extends RecyclerView.ViewHolder {
    public TextView mTitle, mCount;
    public ImageView mThumbnail;

    public TodoHolder(@NonNull View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.title);
        mCount = itemView.findViewById(R.id.count);
        mThumbnail = itemView.findViewById(R.id.thumbnail);
    }


}
