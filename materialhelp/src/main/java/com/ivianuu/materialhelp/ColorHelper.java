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

package com.ivianuu.materialhelp;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Author IVIanuu.
 */

public final class ColorHelper {

    public static int getPrimaryColor(Context context) {
        return resolveColor(context, R.attr.colorPrimary);
    }

    public static int getAccentColor(Context context) {
        return resolveColor(context, R.attr.colorAccent);
    }

    public static int getPrimaryTextColor(Context context) {
        return resolveColor(context, android.R.attr.textColorPrimary);
    }

    private static int resolveColor(Context context, int attr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    }
}
