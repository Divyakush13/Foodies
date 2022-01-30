package com.example.myracipy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myracipy.ExtendedIngredient;
import com.example.myracipy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngedientViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public IngedientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngedientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredient, parent, false));

    }
    @Override
    public void onBindViewHolder(@NonNull IngedientViewHolder holder, int position) {

        holder.textview_meal_name.setText(list.get(position).name);
        holder.textview_meal_name.setSelected(true);
        holder.textView_ingredient_quantity.setText(list.get(position).original);
        holder.textView_ingredient_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredient_100x100/"+list.get(position).image).into(holder.imageview_ingredient);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class  IngedientViewHolder extends RecyclerView.ViewHolder {
    TextView textView_ingredient_quantity,textview_meal_name;
    ImageView imageview_ingredient;
    public IngedientViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ingredient_quantity = itemView.findViewById(R.id.textView_ingredient_quantity);
        textview_meal_name = itemView.findViewById(R.id.textview_meal_name);
        imageview_ingredient = itemView.findViewById(R.id.imageview_ingredient);


    }
}
