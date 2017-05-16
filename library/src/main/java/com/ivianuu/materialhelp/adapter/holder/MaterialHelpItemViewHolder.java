package com.ivianuu.materialhelp.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivianuu.materialhelp.R;

/**
 * Author IVIanuu.
 */

public class MaterialHelpItemViewHolder extends MaterialHelpViewHolder {

    public TextView title;
    public ImageView expandArrow;
    public TextView expandedText;
    public View separator;

    public MaterialHelpItemViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        expandArrow = (ImageView) itemView.findViewById(R.id.expand_arrow);
        expandedText = (TextView) itemView.findViewById(R.id.expanded_text);
        separator = itemView.findViewById(R.id.separator);

    }
}
