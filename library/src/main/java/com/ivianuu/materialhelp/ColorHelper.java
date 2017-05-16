package com.ivianuu.materialhelp;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Author IVIanuu.
 */

public class ColorHelper {

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
