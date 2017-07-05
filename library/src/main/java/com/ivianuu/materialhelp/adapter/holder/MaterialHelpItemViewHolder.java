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

package com.ivianuu.materialhelp.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivianuu.materialhelp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

        title = itemView.findViewById(R.id.title);
        expandArrow = itemView.findViewById(R.id.expand_arrow);
        expandedText = itemView.findViewById(R.id.expanded_text);
        separator = itemView.findViewById(R.id.separator);
    }

}
