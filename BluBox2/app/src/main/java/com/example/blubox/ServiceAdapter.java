package com.example.blubox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ServiceAdapter extends RecyclerView.Adapter {
    ItemClicked context;
    int id;


    private static final int SERVICE = 1;
    private static final int USER = 0;



    public interface ItemClicked {
        void onItemClicked(int index, ArrayList<Service> services) ;

    }


    ArrayList<Service> services;


    public ServiceAdapter(ArrayList<Service> services , Context context) {
        this.services = services;
        this.context = (ItemClicked) context;
    }





    @Override
    public int getItemViewType(int position) {
        Service card = (Service) services.get(position);

        if (card.getId() == 0) {
            // If the card is for displaying bio
            return USER;
        } else {
            // If card is used for diplaying service
            return SERVICE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == USER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_bio_template, parent, false);
            return new UserBioHolder(view);
        } else if (viewType == SERVICE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.services_template, parent, false);
            return new ServiceHolder(view);
        }
        return null;
    }
    //------------

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        id = i;
        View v;
        viewHolder.itemView.setTag(services.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Service serve = services.get(i);
        switch (viewHolder.getItemViewType()) {
            case USER:
                ((UserBioHolder) viewHolder).bind(serve);
                break;
            case SERVICE:
                ((ServiceHolder) viewHolder).bind(serve);
        }
    }

    private class UserBioHolder extends RecyclerView.ViewHolder  {
        TextView username , userbio ;
        ImageView profilepic;
        View itemView;
        ImageButton editbio;

        UserBioHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            profilepic = itemView.findViewById(R.id.profilepic);
            editbio = itemView.findViewById(R.id.editbio);
            username =  itemView.findViewById(R.id.username);
            userbio = itemView.findViewById(R.id.userbio);


        }

        void bind(Service serve) {
            itemView.setTag(serve);
            username.setText(serve.getService_Name());
            userbio.setText(serve.getService_Msg());
            profilepic.setImageDrawable(serve.getImg());


            editbio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(services.indexOf(serve),services);
                }
            });
        }


    }








    private class ServiceHolder extends RecyclerView.ViewHolder  {
        TextView tvName , tvMsg, tvTime;
        ImageView img;
        View itemView;
        ServiceHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName  =  itemView.findViewById(R.id.tvName);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            tvTime  = itemView.findViewById(R.id.tvTime);
            img = itemView.findViewById(R.id.img);
        }

        void bind(Service serve) {


            itemView.setTag(serve);
            tvName.setText(serve.getService_Name());
            tvMsg.setText(serve.getService_Msg());
            tvTime.setText(serve.getTime());
            img.setImageDrawable(serve.getImg());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(services.indexOf(serve),services);
                }
            });



        }

    }


    /*
    return the length of the list (services)
     */
    @Override
    public int getItemCount() {
        return services.size();
    }






}
