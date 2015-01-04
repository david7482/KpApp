package com.david74.kpapp.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.app.activity.AlbumDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;

public class KpAlbumDetailFragment extends BaseFragment {

    @InjectView(R.id.album_cover_image)
    ImageView albumImage;

    @InjectView(R.id.album_cover_title)
    TextView albumTitle;

    public static KpAlbumDetailFragment newInstance(Bundle bundle) {
        KpAlbumDetailFragment fragment = new KpAlbumDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public KpAlbumDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        albumTitle.setText(bundle.getString(AlbumDetailActivity.KEY_TITLE));
        ImageLoader.getInstance().displayImage(bundle.getString(AlbumDetailActivity.KEY_IMAGE_URL), albumImage);
    }
}
