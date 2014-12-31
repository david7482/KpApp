package com.david74.kpapp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.api.model.KpAlbumInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    List<KpAlbumInfo> albumInfoList;

    public AlbumAdapter() {
        albumInfoList = new ArrayList<KpAlbumInfo>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_album, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KpAlbumInfo albumInfo = albumInfoList.get(position);

        holder.modelTitle.setText(albumInfo.getTitle());
        holder.modelSubtitle.setText(albumInfo.getDescription());
        holder.modelView.setImageResource(android.R.color.transparent);
        ImageLoader.getInstance().displayImage(albumInfo.getThumbnails().getSmall(), holder.modelView);
    }

    @Override
    public int getItemCount() {
        return albumInfoList.size();
    }

    public void add(KpAlbumInfo albumInfo) {
        albumInfoList.add(albumInfo);
        notifyDataSetChanged();
    }

    public void add(List<KpAlbumInfo> albumInfoList) {
        this.albumInfoList.addAll(albumInfoList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        @InjectView(R.id.model_image)
        ImageView modelView;
        @InjectView(R.id.model_title)
        TextView modelTitle;
        @InjectView(R.id.model_subtitle)
        TextView modelSubtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.view = itemView;
        }
    }
}
