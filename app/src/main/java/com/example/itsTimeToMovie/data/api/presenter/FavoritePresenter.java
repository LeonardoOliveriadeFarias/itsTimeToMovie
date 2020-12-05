package com.example.itsTimeToMovie.data.api.presenter;

import android.content.Context;

import com.example.itsTimeToMovie.data.Model.Favorite;
import com.example.itsTimeToMovie.data.Model.User;
import com.example.itsTimeToMovie.data.api.contract.FavoriteContract;

public class FavoritePresenter implements FavoriteContract.ListFavoritePresenter {

    private FavoriteContract.listFavoriteView view;
    private User user;

    public FavoritePresenter(FavoriteContract.listFavoriteView view, User user) {
        this.view = view;
        this.user = user;
    }

    @Override
    public void takeFavorite(Context context) {
        new Thread() {
            @Override
            public void run() {

                view.showFavorite(new Favorite(context).list());

            }
        }.start();
    }

    @Override
    public void destroyView(){this.view = null;}
}
