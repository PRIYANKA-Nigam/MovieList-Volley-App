package com.example.movielistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;private List<Model> list;

    public MovieAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
Model model=list.get(position);
holder.rating.setText(model.getRating().toString());
holder.overview.setText(model.getOverview());
holder.title.setText(model.getTitle());
Glide.with(context).load(model.getPoster()).into(holder.imageView);
holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,DetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("title",model.getTitle());
        bundle.putString("overview",model.getOverview());
        bundle.putString("poster",model.getPoster());
        bundle.putDouble("rating",model.getRating());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
ImageView imageView; CardView cardView;
TextView title,overview,rating;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.textView1);
            rating=itemView.findViewById(R.id.textView2);
            overview=itemView.findViewById(R.id.textView3);
             cardView=itemView.findViewById(R.id.card);
        }
    }
}
