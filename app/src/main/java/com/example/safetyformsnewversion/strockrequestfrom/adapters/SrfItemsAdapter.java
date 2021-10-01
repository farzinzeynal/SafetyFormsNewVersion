package com.example.safetyformsnewversion.strockrequestfrom.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.strockrequestfrom.fragments.StockRequestFormFragment;
import com.example.safetyformsnewversion.strockrequestfrom.models.Item;

import java.util.List;


public class SrfItemsAdapter extends RecyclerView.Adapter<SrfItemsAdapter.ItemsViewHolder>
{

    Context context;
    List<Item> mList;

    public SrfItemsAdapter(Context context, List<Item> mList) {
        this.context = context;
        this.mList = mList;
    }



    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.srf_item_view, null);
        ItemsViewHolder holder = new ItemsViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        Item item = mList.get(position);

        holder.textView_item.setText(item.getItem_code());
        holder.imageView_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StockRequestFormFragment.itemModelList.remove(position);
                StockRequestFormFragment.srfItemsAdapter.notifyDataSetChanged();
            }
        });

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }




    class ItemsViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_item;
        ImageView imageView_remove;

        public ItemsViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView_item= itemView.findViewById(R.id.textView_item);
            imageView_remove= itemView.findViewById(R.id.imageView_remove);

        }
    }

}
