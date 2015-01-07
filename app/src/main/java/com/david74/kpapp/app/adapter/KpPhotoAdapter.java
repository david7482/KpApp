package com.david74.kpapp.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.david74.kpapp.R;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.app.viewholder.BaseViewHolder;
import com.david74.kpapp.util.customview.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.InjectView;

public class KpPhotoAdapter extends RecyclerView.Adapter<KpPhotoAdapter.ViewHolder> {

    private List<Model> modelList;

    private SparseArray<Double> positionHeightRatios = new SparseArray<Double>();

    public KpPhotoAdapter() {
        modelList = new ArrayList<Model>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_photo, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model photoModel = modelList.get(position);

        holder.modelView.setHeightRatio(getPositionRatio(position));

        ImageLoader.getInstance().displayImage(photoModel.getImageUrl(), holder.modelView);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void add(Model model) {
        modelList.add(model);
        notifyItemInserted(modelList.size() - 1);
    }

    public void add(List<Model> modelList) {
        int positionStart = this.modelList.size();
        this.modelList.addAll(modelList);
        notifyItemRangeInserted(positionStart, modelList.size());
    }

    private double getPositionRatio(final int position) {
        double ratio = positionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = (new Random().nextDouble() / 2.0) + 1.0;
            positionHeightRatios.append(position, ratio);
        }
        return ratio;
    }

    public Model get(int position) {
        return modelList.get(position);
    }

    public List<Model> getAll() {
        return new ArrayList<Model>(modelList);
    }

    public static class ViewHolder extends BaseViewHolder {

        @InjectView(R.id.model_image)
        DynamicHeightImageView modelView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
