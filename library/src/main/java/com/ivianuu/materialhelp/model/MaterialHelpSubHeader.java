package com.ivianuu.materialhelp.model;

import com.ivianuu.materialhelp.interfaces.MaterialHelpInterface;
import com.ivianuu.materialhelp.interfaces.MaterialHelpViewTypes;

/**
 * Author IVIanuu.
 */

public class MaterialHelpSubHeader implements MaterialHelpInterface {

    private String title;
    private int titleRes;

    private MaterialHelpSubHeader(Builder builder) {
        this.title = builder.title;
        this.titleRes = builder.titleRes;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleRes() {
        return titleRes;
    }

    @Override
    public int getType() {
        return MaterialHelpViewTypes.SUB_HEADER;
    }

    public static class Builder {

        private String title;
        private int titleRes;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withTitleRes(int titleRes) {
            this.titleRes = titleRes;
            return this;
        }

        public MaterialHelpSubHeader build() {
            return new MaterialHelpSubHeader(this);
        }
    }
}
