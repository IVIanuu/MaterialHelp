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

package com.ivianuu.materialhelp.model;

import com.ivianuu.materialhelp.interfaces.MaterialHelpInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds and manages items
 */

public final class MaterialHelpList {

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