package com.example.safetyformsnewversion.rigsafetyform.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safetyformsnewversion.R;

import java.util.List;

public class RecyclerViewImagesAdapter extends RecyclerView.Adapter<RecyclerViewImagesAdapter.CostomViewHolder>{


    List<byte[]> mList;
    Context mcontext;


    public RecyclerViewImagesAdapter(List<byte[]> mList, Context mcontext)
    {
        this.mList = mList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public CostomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.image_listview, null);
        CostomViewHolder holder = new CostomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CostomViewHolder holder, int position)
    {
        byte[] bytes = mList.get(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.imageView_photo.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CostomViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView_photo;

        public CostomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_photo = itemView.findViewById(R.id.imageView_list_image);
        }
    }
}

