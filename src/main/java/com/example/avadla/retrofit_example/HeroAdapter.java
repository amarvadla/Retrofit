package com.example.avadla.retrofit_example;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by avadla on 11/23/2017.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.MyViewHolder> {

    Context context;
    List list;
    private int oldPosition = -1;
    public HeroAdapter(MainActivity mainActivity, List list) {
        this.context = mainActivity;
        this.list = list;
    }

    @Override
    public HeroAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.list_layout_heroes,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroAdapter.MyViewHolder holder,final int position) {
        final Info info = (Info) list.get(position);
        holder.tv4.setText(info.getName());
        holder.tv.setText(info.getFirstappearance());
        holder.tv2.setText(info.getPublisher());
        holder.tv3.setText(info.getTeam());


        holder.cardView.setCardBackgroundColor(info.isSelected() ? Color.parseColor("#E0FEFC") : Color.parseColor("#ffffff") );
        holder.cardView.setCardElevation(info.isSelected() ? 30 : 10);
        holder.cardView.setEnabled(info.isSelected() ? true : false);
        holder.imageView.setImageResource(info.isSelected() ? R.drawable.ic_check : R.drawable.ic_check_grey );


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldPosition == -1 || oldPosition == position) {
                    info.setSelected(!info.isSelected());
                    notifyItemChanged(position);
                }
                else{
                    Info info1 = (Info) list.get(oldPosition);
                    info1.setSelected(false);
                    notifyItemChanged(oldPosition);

                    info.setSelected(!info.isSelected());
                    notifyItemChanged(position);
                }

                oldPosition = position;
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv,tv2,tv3,tv4;
        CardView cardView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView3);
            tv2 = (TextView) itemView.findViewById(R.id.textView4);
            tv3 = (TextView) itemView.findViewById(R.id.textView5);
            tv4 = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView2);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }


}
