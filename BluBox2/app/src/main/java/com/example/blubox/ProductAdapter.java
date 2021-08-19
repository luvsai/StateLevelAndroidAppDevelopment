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



public class ProductAdapter extends RecyclerView.Adapter {
    QuestClicked context;
    int id;

    private static final int QUEST = 1;
    private static final int INTRO = 0;



    public interface QuestClicked {
        void onQuestClicked(int index, ArrayList<Quest> quests);

    }


    ArrayList<Quest> quests ;

    /*
    Constructor for the ServiceAdapter Class
     */

    public ProductAdapter( ArrayList<Quest> quests  , Context context) {
        this.quests  = quests ;
        this.context = (QuestClicked) context;
    }





    @Override
    public int getItemViewType(int position) {
        Quest card = (Quest) quests.get(position);

        if (card.getqId() == -1) {

            return INTRO;
        } else {

            return QUEST;
        }
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == INTRO) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_template, parent, false);
            return new IntroHolder(view);
        } else if (viewType == QUEST) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_template, parent, false);
            return new QuestHolder(view);
        }
        return null;
    }
    //------------

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        id = i;
        View v;
        viewHolder.itemView.setTag(quests.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Quest quest= quests.get(i);
        switch (viewHolder.getItemViewType()) {
            case INTRO:
                ((IntroHolder) viewHolder).bind(quest);
                break;
            case QUEST:
                ((QuestHolder) viewHolder).bind(quest);
        }
    }






    private class IntroHolder extends RecyclerView.ViewHolder  {
        View itemView;
        ImageButton createquest;
        ImageView qprofilepic;

        IntroHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;


        }

        void bind(Quest quest) {
            itemView.setTag(quest);



        }


    }





    /*
       product cards

     */


    private class QuestHolder extends RecyclerView.ViewHolder  {
        TextView productname , description, price , dprice,rating;



        View itemView;
        QuestHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            productname =  itemView.findViewById(R.id.productname);
            description =  itemView.findViewById(R.id.description);
            price =  itemView.findViewById(R.id.price);
            dprice =  itemView.findViewById(R.id.dprice);
            rating = itemView.findViewById(R.id.rating);


        }

        void bind(Quest quest) {


            itemView.setTag(quest);
            productname.setText(quest.getqTitle());
            description.setText(quest.getqMsg());
            price.setText("\nActual Price : \n\t"+quest.getQprice());
            dprice.setText("Discounted Price : \n\t"+quest.getDprice());
            rating.setText("Rating : " +String.valueOf(quest.getQrating())) ;





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.onQuestClicked(quests.indexOf(quest),quests);
                }
            });




        }
    }


    /*
    return the length of the list (products)
     */
    @Override
    public int getItemCount() {
        return quests.size();
    }






}
