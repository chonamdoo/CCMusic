package com.cc.ui.karaoke.presenter.song.karaoke;

import android.database.Cursor;
import android.util.Log;

import com.cc.ui.karaoke.data.database.helper.karaoke.VMSongKaraokeScope;
import com.cc.ui.karaoke.data.database.helperImp.karaoke.VMSongVietKtvScopeImp;
import com.cc.ui.karaoke.data.model.karaoke.VMSongKaraoke;
import com.cc.ui.karaoke.presenter.song.base.VMBaseSongPresenterImp;
import com.cc.ui.karaoke.presenter.song.karaoke.base.VMSongKaraokePresenter;
import com.cc.ui.karaoke.ui.fragment.song.karaoke.VMSongVietKtvView;

/**
 * Author: NT
 * Email: duynguyen.developer@yahoo.com
 */
public class VMSongVietKtvPresenterImp extends VMBaseSongPresenterImp implements
        VMSongKaraokePresenter<VMSongVietKtvView> {
    private static final String TAG = VMSongVietKtvPresenterImp.class.getSimpleName();

    private VMSongKaraokeScope songCommonScope;
    private VMSongVietKtvView mView;


    @Override
    public void getAllSong() {
        Log.d(TAG, "getAllSongDevices");
        Cursor cursor = songCommonScope.getCursor();
        if (cursor != null) {
            mView.hideLoading();
            mView.setCursor(cursor);
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
    public void setView(VMSongVietKtvView view) {
        this.mView = view;
        this.songCommonScope = new VMSongVietKtvScopeImp();
    }

    @Override
    public void destroyView() {
        this.mView = null;
    }


}
