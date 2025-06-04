package com.example.javaxml.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaxml.R;
import com.example.javaxml.api.Hit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Holder> {

    public List<Hit> hits;
    public AdapterClass(List<Hit> hits){
        this.hits = hits;
    }
    public static class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.text.setText(hits.get(position).getUser());
        Picasso.get()
               .load(hits.get(position).getWebformatURL())
               .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }


}
