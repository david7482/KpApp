package com.david74.kpapp.app.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.david74.kpapp.R;
import com.david74.kpapp.app.viewholder.BaseViewHolder;
import com.david74.kpapp.app.model.Model;
import com.david74.kpapp.util.screen.Screen;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class KpAlbumAdapter extends RecyclerView.Adapter<KpAlbumAdapter.ViewHolder> {

    List<Model> modelList;
    private int lastAnimatedPosition = -1;

    public KpAlbumAdapter() {
        modelList = new ArrayList<Model>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_album, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Setup the enter animation
        runEnterAnimation(holder.itemView, position);

        Model albumModel = modelList.get(position);
        holder.modelTitle.setText(albumModel.getTitle());
        ImageLoader.getInstance().displayImage(albumModel.getImageUrl(), holder.modelView);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    private void runEnterAnimation(final View view, int position) {
        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;

            Point screenSize = Screen.getScreenSize();

            final int duration = 500;
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", screenSize.y, 0);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }
            });

            AnimatorSet set = new AnimatorSet();
            set.play(animator);
            set.setInterpolator(new DecelerateInterpolator());
            set.setDuration(duration);
            set.setStartDelay(50);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.INVISIBLE);
                }
            });
            set.start();
        }
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

    public Model get(int position) {
        return this.modelList.get(position);
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
