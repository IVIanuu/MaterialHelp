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
