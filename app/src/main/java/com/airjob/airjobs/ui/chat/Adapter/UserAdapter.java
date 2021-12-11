package com.airjob.airjobs.ui.chat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airjob.airjobs.ui.manageProfil.ModelProfilCandidat;
import com.airjob.airjobs.ui.chat.MessageActivity;
import com.airjob.airjobs.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<ModelProfilCandidat> mUsers;
    private boolean ischat;

    public UserAdapter(Context mContext, List<ModelProfilCandidat> mUsers, boolean ischat) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ModelProfilCandidat user = mUsers.get(position);
        holder.username.setText(user.getNom());
        if (user.getimageurl().equals("default")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(user.getimageurl()).into(holder.profile_image);
        }

        if (ischat) {
            if (user.getStatus().equals("Online")) {
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            } else {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        } else {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("idParticipantChat", user.getIDprofil());
                Log.i("##------->>>", "UserAdapter ----->   intent.putExtra : " + intent.getStringExtra("idParticipantChat"));
                Log.i("##------->>>", "UserAdapter ----->   mContext : " + mContext);


//                Log.i("##------->>>", "je suis au debut de onCreate +++");
//                Log.i("##------->>>", "je suis a la fin de onCreate ---");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);

        }
    }
}
