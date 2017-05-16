package com.ivianuu.materialhelp.adapter;

import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivianuu.materialhelp.ColorHelper;
import com.ivianuu.materialhelp.MaterialHelpActivity;
import com.ivianuu.materialhelp.R;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpItemViewHolder;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpSubHeaderViewHolder;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpViewHolder;

import com.ivianuu.materialhelp.interfaces.MaterialHelpViewTypes;
import com.ivianuu.materialhelp.model.MaterialHelpItem;
import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.materialhelp.model.MaterialHelpSubHeader;
import com.ivianuu.stickyheaders.StickyHeaders;

/**
 * Author IVIanuu.
 */

public class MaterialHelpAdapter extends RecyclerView.Adapter<MaterialHelpViewHolder> implements StickyHeaders, StickyHeaders.ViewSetup {

    private AppCompatActivity mActivity;
    private MaterialHelpList mMaterialHelpList;

    private int mExpandedPosition = RecyclerView.NO_POSITION;

    private final int mAccentColor;
    private final int mPrimaryTextColor;

    private final float mStuckHeaderElevation;

    public MaterialHelpAdapter(AppCompatActivity activity, MaterialHelpList materialHelpList) {
        mActivity = activity;
        mMaterialHelpList = materialHelpList;

        mAccentColor = ColorHelper.getAccentColor(mActivity);
        mPrimaryTextColor = ColorHelper.getPrimaryTextColor(mActivity);

        mStuckHeaderElevation = mActivity.getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
    }

    public void swapMaterialHelpList(@NonNull MaterialHelpList materialHelpList) {
        mMaterialHelpList = materialHelpList;
        notifyDataSetChanged();
    }

    @Override
    public MaterialHelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MaterialHelpViewTypes.SUB_HEADER:
                return new MaterialHelpSubHeaderViewHolder(inflateView(R.layout.material_help_sub_header, parent));
            case MaterialHelpViewTypes.ITEM:
                return new MaterialHelpItemViewHolder(inflateView(R.layout.material_help_item, parent));
        }

        return null;
    }

    private View inflateView(int resourceId, ViewGroup parent) {
        return LayoutInflater.from(mActivity).inflate(resourceId, parent, false);
    }

    @Override
    public void onBindViewHolder(final MaterialHelpViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MaterialHelpViewTypes.SUB_HEADER:
                MaterialHelpSubHeaderViewHolder subHeaderViewHolder = (MaterialHelpSubHeaderViewHolder) holder;
                MaterialHelpSubHeader materialHelpSubHeader = (MaterialHelpSubHeader) mMaterialHelpList.getItems().get(position);

                String headerTitle;
                if (materialHelpSubHeader.getTitle() != null) {
                    headerTitle = materialHelpSubHeader.getTitle();
                } else {
                    headerTitle = mActivity.getString(materialHelpSubHeader.getTitleRes());
                }

                subHeaderViewHolder.subHeaderTitle.setText(headerTitle.toUpperCase());
                break;
            case MaterialHelpViewTypes.ITEM:
                MaterialHelpItemViewHolder itemViewHolder = (MaterialHelpItemViewHolder) holder;
                MaterialHelpItem materialHelpItem = (MaterialHelpItem) mMaterialHelpList.getItems().get(position);

                String itemTitle;
                if (materialHelpItem.getTitle() != null) {
                    itemTitle = materialHelpItem.getTitle();
                } else {
                    itemTitle = mActivity.getString(materialHelpItem.getTitleRes());
                }

                itemViewHolder.title.setText(itemTitle);

                String expandedText;
                if (materialHelpItem.getExpandedText() != null) {
                    expandedText = materialHelpItem.getExpandedText();
                } else {
                    expandedText = mActivity.getString(materialHelpItem.getExpandedTextRes());
                }

                itemViewHolder.expandedText.setText(expandedText);

                itemViewHolder.separator.setVisibility(
                        isNextItemHeader(holder) || isLastItem(holder) ? View.GONE : View.VISIBLE);

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mExpandedPosition == holder.getAdapterPosition()) {
                            // this item is expanded so collapse it
                            mExpandedPosition = RecyclerView.NO_POSITION;
                            notifyItemChanged(holder.getAdapterPosition());
                        } else {
                            int oldExpandedPosition = mExpandedPosition;

                            // expand this item
                            mExpandedPosition = holder.getAdapterPosition();
                            notifyItemChanged(mExpandedPosition);

                            // collapse old expanded item
                            if (oldExpandedPosition != RecyclerView.NO_POSITION)
                                notifyItemChanged(oldExpandedPosition);
                        }
                    }
                });

                boolean expanded = isExpanded(holder);

                // show expanded text
                itemViewHolder.expandedText.setVisibility(expanded ? View.VISIBLE : View.GONE);


                // set title and arrow color
                int titleAndArrowColor = expanded ? mAccentColor : mPrimaryTextColor;

                itemViewHolder.title.setTextColor(titleAndArrowColor);
                itemViewHolder.expandArrow.getDrawable().setColorFilter(titleAndArrowColor, PorterDuff.Mode.SRC_IN);

                // animate arrow
                itemViewHolder.expandArrow.animate()
                        .rotation(expanded ? 180f : 0f)
                        .setDuration(expanded ? 200 : 300)
                        .start();

                break;
        }
    }

    private boolean isExpanded(RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition() == mExpandedPosition;
    }

    private boolean isNextItemHeader(RecyclerView.ViewHolder holder) {
        return !isLastItem(holder) && getItemViewType(holder.getAdapterPosition() + 1) == MaterialHelpViewTypes.SUB_HEADER;
    }

    private boolean isLastItem(RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition() == getItemCount() -1;
    }

    @Override
    public int getItemViewType(int position) {
        return mMaterialHelpList.getItems().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mMaterialHelpList != null ? mMaterialHelpList.getItems().size() : 0;
    }

    @Override
    public boolean isStickyHeader(int position) {
        return getItemViewType(position) == MaterialHelpViewTypes.SUB_HEADER;
    }

    @Override
    public void setupStickyHeaderView(View view) {
        ((CardView) view).setCardElevation(mStuckHeaderElevation);
    }

    @Override
    public void teardownStickyHeaderView(View view) {
        ((CardView) view).setCardElevation(0f);
    }
}
