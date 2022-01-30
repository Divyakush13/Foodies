package com.example.myracipy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myracipy.Listner.RacipyClickListner;
import com.example.myracipy.R;
import com.example.myracipy.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class RandomRecipyAdapter extends RecyclerView.Adapter<RandomRecipyViewHolder>{
    Context context;
    List<Recipe> list;
    RacipyClickListner listner;

    public RandomRecipyAdapter(Context context, List<Recipe> list,RacipyClickListner listner) {

        this.context=context;
        this.list=list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public RandomRecipyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipy,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipyViewHolder holder, int position) {
        holder.textview_tiltle.setText(list.get(position).title);
        holder.textview_tiltle.setSelected(true);
        holder.Textview_like.setText(list.get(position).aggregateLikes+" Likes");
        holder.Textview_serving.setText(list.get(position).servings+" Servings");
        holder.Textview_time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.Image_food);

        holder.random_list_contneir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onRecipyclicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class  RandomRecipyViewHolder extends RecyclerView.ViewHolder {

    CardView random_list_contneir;
    TextView textview_tiltle, Textview_serving, Textview_like, Textview_time;
    ImageView Image_food;

    public RandomRecipyViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_contneir = itemView.findViewById(R.id.random_list_contneir);
        textview_tiltle = itemView.findViewById(R.id.textview_tiltle);
        Textview_serving = itemView.findViewById(R.id.Textview_serving);
        Textview_like = itemView.findViewById(R.id.Textview_like);
        Textview_time = itemView.findViewById(R.id.Textview_time);
        Image_food = itemView.findViewById(R.id.Image_food);

    }
}