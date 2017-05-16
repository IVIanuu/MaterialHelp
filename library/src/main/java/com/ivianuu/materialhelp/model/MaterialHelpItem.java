package com.ivianuu.materialhelp.model;

import com.ivianuu.materialhelp.interfaces.MaterialHelpInterface;
import com.ivianuu.materialhelp.interfaces.MaterialHelpViewTypes;

/**
 * Author IVIanuu.
 */

public class MaterialHelpItem implements MaterialHelpInterface {

    private String title;
    private int titleRes;

    private String expandedText;
    private int expandedTextRes;

    private MaterialHelpItem(Builder builder) {
        this.title = builder.title;
        this.titleRes = builder.titleRes;
        this.expandedText = builder.expandedText;
        this.expandedTextRes = builder.expandedTextRes;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public String getExpandedText() {
        return expandedText;
    }

    public int getExpandedTextRes() {
        return expandedTextRes;
    }

    @Override
    public int getType() {
        return MaterialHelpViewTypes.ITEM;
    }

    public static class Builder {

        private String title;
        private int titleRes;

        private String expandedText;
        private int expandedTextRes;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withTitleRes(int titleRes) {
            this.titleRes = titleRes;
            return this;
        }

        public Builder withExpandedText(String expandedText) {
            this.expandedText = expandedText;
            return this;
        }

        public Builder withExpandedTextRes(int expandedTextRes) {
            this.expandedTextRes = expandedTextRes;
            return this;
        }

        public MaterialHelpItem build() {
            return new MaterialHelpItem(this);
        }
    }
}
