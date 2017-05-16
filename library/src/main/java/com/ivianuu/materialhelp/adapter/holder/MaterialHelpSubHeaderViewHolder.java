package com.ivianuu.materialhelp.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.ivianuu.materialhelp.R;

/**
 * Author IVIanuu.
 */

public class MaterialHelpSubHeaderViewHolder extends MaterialHelpViewHolder {

    public TextView subHeaderTitle;

    public MaterialHelpSubHeaderViewHolder(View itemView) {
        super(itemView);

        subHeaderTitle = (TextView) itemView.findViewById(R.id.sub_header_title);
    }
}
