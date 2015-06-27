package com.example.kikogonzalez.material_linearrecyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAnimatedAdapter
        extends RecyclerView.Adapter<RecyclerViewAnimatedAdapter.ViewHolder> {

    private int mLastPosition = -1;

    private List<ViewModel> items;

    public RecyclerViewAnimatedAdapter(List<ViewModel> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ViewModel viewModel = items.get(position);
        holder.avatar.setText(String.valueOf(viewModel.getNumber()));
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Demo purpose
            }
        });
        holder.carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Demo purpose
            }
        });
        if (position > mLastPosition) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(holder.itemView, "translationY",
                           holder.itemView.getMeasuredHeight() * 2, 0),
                    ObjectAnimator.ofFloat(holder.itemView, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.5f, 1),
                    ObjectAnimator.ofFloat(holder.itemView, "scaleY", 0.5f, 1)
            );
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.start();

            mLastPosition = position;
        } else {
            ViewHelper.clear(holder.itemView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView avatar;
        public CardView carView;
        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (TextView) itemView.findViewById(R.id.avatar);
            carView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class ViewHelper {

        public static void clear(View v) {
            ViewCompat.setAlpha(v, 1);
            ViewCompat.setTranslationY(v, 0);
            v.setPivotY(v.getMeasuredHeight() / 2);
            ViewCompat.setScaleX(v, 1);
            ViewCompat.setScaleY(v, 1);
            ViewCompat.animate(v).setInterpolator(null);
        }
    }

}
