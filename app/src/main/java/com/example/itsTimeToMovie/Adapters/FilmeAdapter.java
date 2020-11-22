package com.example.itsTimeToMovie.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itsTimeToMovie.data.Model.Filme;
import com.example.itsTimeToMovie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.CatalogoViewHolder> {

    private List<Filme> filmeList;
    private static ItemFilmeClickListener itemFilmeClickListener;


    public FilmeAdapter(ItemFilmeClickListener itemFilmeClickListener){
        this.itemFilmeClickListener = itemFilmeClickListener;
        filmeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CatalogoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_filme, parent, false);

        return new CatalogoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogoViewHolder holder, int position) {
        holder.bind(filmeList.get(position));

    }

    @Override
    public int getItemCount() {
        return (filmeList != null && filmeList.size()>0)? filmeList.size() : 0;
    }

    static class CatalogoViewHolder extends RecyclerView.ViewHolder{

        private TextView textFilmeTitle;
        private ImageView imagePoster;
        private Filme filme;

        public CatalogoViewHolder(@NonNull View itemView) {
            super(itemView);

            textFilmeTitle = itemView.findViewById(R.id.text_filme_Title);
            imagePoster = itemView.findViewById(R.id.imagePoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemFilmeClickListener != null){
                        itemFilmeClickListener.onItemFilmeClicked(filme);
                    }
                }
            });

        }

        public void bind(Filme filme){
            this.filme = filme;

            textFilmeTitle.setText(filme.getTitle());
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342/" + filme.getPosterPath())
                    .into(imagePoster);
        }
    }

    public void setFilmes(List<Filme> filmes){
        this.filmeList = filmes;
        notifyDataSetChanged();
    }

    public interface ItemFilmeClickListener{
        void onItemFilmeClicked(Filme filme);

    }
}

