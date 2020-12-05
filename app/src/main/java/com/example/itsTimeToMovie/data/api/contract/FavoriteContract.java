package com.example.itsTimeToMovie.data.api.contract;

import android.content.Context;

import com.example.itsTimeToMovie.data.Model.Filme;

import java.util.List;

public interface FavoriteContract {

    interface listFavoriteView {
        void showFavorite(List<Filme> favoritos);
        void showError();
    }

    interface ListFavoritePresenter {
        void takeFavorite(Context context);
        void destroyView();
    }
}
