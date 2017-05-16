package com.ivianuu.materialhelp.model;

import com.ivianuu.materialhelp.interfaces.MaterialHelpInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author IVIanuu.
 */

public class MaterialHelpList {

    private List<MaterialHelpInterface> items = new ArrayList<>();

    private MaterialHelpList(Builder builder) {
        this.items = builder.items;
    }

    public MaterialHelpList addItem(MaterialHelpInterface item) {
        items.add(item);
        return this;
    }

    public MaterialHelpList clearItems() {
        items.clear();
        return this;
    }

    public List<MaterialHelpInterface> getItems() {
        return items;
    }

    public static class Builder {
        private List<MaterialHelpInterface> items = new ArrayList<>();

        public Builder addItem(MaterialHelpInterface item) {
            this.items.add(item);
            return this;
        }

        public MaterialHelpList build() {
            return new MaterialHelpList(this);
        }
    }
}