package com.cc.ui.karaoke.presenter.song.karaoke;

import android.database.Cursor;
import android.util.Log;

import com.cc.ui.karaoke.data.database.helper.karaoke.VMSongKaraokeScope;
import com.cc.ui.karaoke.data.database.helperImp.karaoke.VMSongArirangScopeImp;
import com.cc.ui.karaoke.data.model.karaoke.VMSongKaraoke;
import com.cc.ui.karaoke.presenter.song.base.VMBaseSongPresenterImp;
import com.cc.ui.karaoke.presenter.song.karaoke.base.VMSongKaraokePresenter;
import com.cc.ui.karaoke.ui.fragment.song.karaoke.VMSongArirangView;

/**
 * Author: NT
 * Email: duynguyen.developer@yahoo.com
 */
public class VMSongArirangPresenterImp extends VMBaseSongPresenterImp implements
        VMSongKaraokePresenter<VMSongArirangView> {
    private static final String TAG = VMSongArirangPresenterImp.class.getSimpleName();

    private VMSongKaraokeScope songCommonScope;
    private VMSongArirangView mView;



    @Override
    public void getAllSong() {
         Cursor cursor = songCommonScope.getCursor();
        if (cursor != null) {
            mView.setCursor(cursor);
            mView.hideLoading();
        } else {
         }

    }

    @Override
    public void searchSong(String key, int idVols) {
        Log.d(TAG, "searchSong key = " + key);
        Cursor cursor = songCommonScope.searchQuery(key, String.valueOf(idVols));
        if (cursor != null) {
            mView.setCursor(cursor);
        } else {
         }
    }

    @Override
    public void getListVols(int value) {
       mView.setDataVols(songCommonScope.getListVols(value));
    }

    @Override
    public void getSongWithVols(String volsName, String language) {
        Cursor cursor = songCommonScope.getCursorWithVolsName(volsName, language);
        if (cursor != null) {
            mView.setCursor(cursor);
            mView.hideLoading();
        } else {
        }
    }

    @Override
    public void addSongToFavorite(VMSongKaraoke songKaraoke) {
        songCommonScope.addFavorite(songKaraoke);
    }

    @Override
    public void removeSongToFavorite(long idSong) {
        songCommonScope.removeSongFavorite(idSong);
    }


    @Override
    public void setView(VMSongArirangView view) {
        this.mView = view;
        this.songCommonScope = new VMSongArirangScopeImp();
    }

    @Override
    public void destroyView() {
        this.mView = null;
    }


}
