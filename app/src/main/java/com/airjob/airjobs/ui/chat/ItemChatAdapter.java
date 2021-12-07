package com.airjob.airjobs.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airjob.airjobs.R;

import java.util.List;

public class ItemChatAdapter extends RecyclerView.Adapter<ItemChatAdapter.ViewHolder> {

    List<ModelChat> itemChatList1;
    private Context context;

    public ItemChatAdapter(List<ModelChat> itemChatList, Context context) {

        this.itemChatList1=itemChatList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemImage.setImageResource(itemChatList1.get(position).getImage());
        holder.itemName.setText(itemChatList1.get(position).getName());
        holder.itemHours.setText(itemChatList1.get(position).getHours());

    }

    @Override
    public int getItemCount() {
        return itemChatList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName,itemHours;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.ivChatProfil);
            itemName = itemView.findViewById(R.id.tvNameChat);
            itemHours = itemView.findViewById(R.id.tvHoursChat);
        }
    }
}
