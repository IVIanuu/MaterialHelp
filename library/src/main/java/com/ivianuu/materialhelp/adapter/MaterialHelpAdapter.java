/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.materialhelp.adapter;

import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivianuu.materialhelp.ColorHelper;
import com.ivianuu.materialhelp.R;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpItemViewHolder;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpSubHeaderViewHolder;
import com.ivianuu.materialhelp.adapter.holder.MaterialHelpViewHolder;
import com.ivianuu.materialhelp.interfaces.MaterialHelpViewTypes;
import com.ivianuu.materialhelp.model.MaterialHelpItem;
import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.materialhelp.model.MaterialHelpSubHeader;
import com.ivianuu.recyclerviewhelpers.stickyheaders.StickyHeaders;

/**
 * Author IVIanuu.
 */

public class MaterialHelpAdapter extends RecyclerView.Adapter<MaterialHelpViewHolder> implements StickyHeaders, StickyHeaders.ViewSetup {

    private AppCompatActivity activity;
    private MaterialHelpList materialHelpList;

    private int expandedPosition = RecyclerView.NO_POSITION;

    private final int accentColor;
    private final int primaryTextColor;

    private final float stuckHeaderElevation;

    public MaterialHelpAdapter(AppCompatActivity activity, MaterialHelpList materialHelpList) {
        this.activity = activity;
        this.materialHelpList = materialHelpList;

        accentColor = ColorHelper.getAccentColor(activity);
        primaryTextColor = ColorHelper.getPrimaryTextColor(activity);

        stuckHeaderElevation = activity.getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
    }

    public void swapMaterialHelpList(@NonNull MaterialHelpList materialHelpList) {
        this.materialHelpList = materialHelpList;
        expandedPosition = RecyclerView.NO_POSITION;
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
        return LayoutInflater.from(activity).inflate(resourceId, parent, false);
    }

    @Override
    public void onBindViewHolder(final MaterialHelpViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MaterialHelpViewTypes.SUB_HEADER:
                MaterialHelpSubHeaderViewHolder subHeaderViewHolder = (MaterialHelpSubHeaderViewHolder) holder;
                MaterialHelpSubHeader materialHelpSubHeader = (MaterialHelpSubHeader) materialHelpList.getItems().get(position);

                String headerTitle;
                if (materialHelpSubHeader.getTitle() != null) {
                    headerTitle = materialHelpSubHeader.getTitle();
                } else {
                    headerTitle = activity.getString(materialHelpSubHeader.getTitleRes());
                }

                subHeaderViewHolder.subHeaderTitle.setText(headerTitle.toUpperCase());
                break;
            case MaterialHelpViewTypes.ITEM:
                final MaterialHelpItemViewHolder itemViewHolder = (MaterialHelpItemViewHolder) holder;
                final MaterialHelpItem materialHelpItem = (MaterialHelpItem) materialHelpList.getItems().get(position);

                String itemTitle;
                if (materialHelpItem.getTitle() != null) {
                    itemTitle = materialHelpItem.getTitle();
                } else {
                    itemTitle = activity.getString(materialHelpItem.getTitleRes());
                }

                itemViewHolder.title.setText(itemTitle);

                String expandedText;
                if (materialHelpItem.getExpandedText() != null) {
                    expandedText = materialHelpItem.getExpandedText();
                } else {
                    expandedText = activity.getString(materialHelpItem.getExpandedTextRes());
                }

                itemViewHolder.expandedText.setText(expandedText);

                itemViewHolder.separator.setVisibility(
                        isNextItemHeader(holder) || isLastItem(holder) ? View.GONE : View.VISIBLE);

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(expandedPosition == holder.getAdapterPosition()) {
                            // collapse this item
                            expandedPosition = RecyclerView.NO_POSITION;
                            notifyItemChanged(holder.getAdapterPosition());
                        } else {
                            int oldExpandedPosition = expandedPosition;

                            // expand this item
                            expandedPosition = holder.getAdapterPosition();
                            notifyItemChanged(expandedPosition);

                            // collapse last expanded item
                            if(oldExpandedPosition != -1)
                                notifyItemChanged(oldExpandedPosition);
                        }
                    }
                });

                boolean expanded = expandedPosition == holder.getAdapterPosition();

                // show expanded text
                itemViewHolder.expandedText.setVisibility(expanded ? View.VISIBLE : View.GONE);

                // set title and arrow color
                int titleAndArrowColor = expanded ? accentColor : primaryTextColor;

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

    private boolean isNextItemHeader(RecyclerView.ViewHolder holder) {
        return !isLastItem(holder) && getItemViewType(holder.getAdapterPosition() + 1) == MaterialHelpViewTypes.SUB_HEADER;
    }

    private boolean isLastItem(RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition() == getItemCount() -1;
    }

    @Override
    public int getItemViewType(int position) {
        return materialHelpList.getItems().get(position).getType();
    }

    @Override
    public int getItemCount() {
        return materialHelpList != null ? materialHelpList.getItems().size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return materialHelpList.getItems().get(position).hashCode();
    }

    @Override
    public boolean isStickyHeader(int position) {
        return getItemViewType(position) == MaterialHelpViewTypes.SUB_HEADER;
    }

    @Override
    public void setupStickyHeaderView(View view) {
        ((CardView) view).setCardElevation(stuckHeaderElevation);
    }

    @Override
    public void teardownStickyHeaderView(View view) {
        ((CardView) view).setCardElevation(0f);
    }
}
