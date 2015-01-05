package com.david74.kpapp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.viewholder.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class KpPhotoAdapter extends RecyclerView.Adapter<KpPhotoAdapter.ViewHolder> {
    List<Model> albumModelList;

    public KpPhotoAdapter() {
        albumModelList = new ArrayList<Model>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_photo, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model photoModel = albumModelList.get(position);
        ImageLoader.getInstance().displayImage(photoModel.getImageUrl(), holder.modelView);
    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public void add(Model model) {
        albumModelList.add(model);
        notifyDataSetChanged();
    }

    public void add(List<Model> modelList) {
        this.albumModelList.addAll(modelList);
        notifyDataSetChanged();
    }

    public Model get(int position) {
        return this.albumModelList.get(position);
    }

    public static class ViewHolder extends BaseViewHolder {

        @InjectView(R.id.model_image)
        ImageView modelView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
