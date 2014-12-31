package com.david74.kpapp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.api.model.KpAlbumInfo;
import com.david74.kpapp.app.viewholder.BaseViewHolder;
import com.david74.kpapp.app.viewmodel.KpAlbumViewModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class KpAlbumAdapter extends RecyclerView.Adapter<KpAlbumAdapter.ViewHolder> {

    List<KpAlbumViewModel> albumModelList;

    public KpAlbumAdapter() {
        albumModelList = new ArrayList<KpAlbumViewModel>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_album, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KpAlbumViewModel albumModel = albumModelList.get(position);

        holder.modelTitle.setText(albumModel.getTitle());
        ImageLoader.getInstance().displayImage(albumModel.getImageUrl(), holder.modelView);
    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public void add(KpAlbumViewModel albumModel) {
        albumModelList.add(albumModel);
        notifyDataSetChanged();
    }

    public void add(List<KpAlbumViewModel> albumModelList) {
        this.albumModelList.addAll(albumModelList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends BaseViewHolder {

        @InjectView(R.id.model_image)
        ImageView modelView;

        @InjectView(R.id.model_title)
        TextView modelTitle;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
