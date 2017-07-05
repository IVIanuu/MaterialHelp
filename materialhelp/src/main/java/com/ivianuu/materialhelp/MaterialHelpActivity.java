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
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.ivianuu.materialhelp.adapter.MaterialHelpAdapter;
import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.recyclerviewhelpers.stickyheaders.StickyHeadersLinearLayoutManager;

/**
 * Author IVIanuu.
 */

// TODO: 16.05.2017 searchable, fragment

public abstract class MaterialHelpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView helpList;

    private MaterialHelpAdapter helpAdapter;

    private MaterialHelpList materialHelpList = new MaterialHelpList.Builder().build();

    private ListTask listTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verifyAttributesExist();

        setContentView(R.layout.activity_material_help);

        initToolbar();
        initRecyclerView();

        // Get material list
        listTask = new ListTask(this);
        listTask.execute();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getActivityTitle();

        if (title == null) title = getString(R.string.material_help_title);

        //noinspection ConstantConditions
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Resources.Theme theme = getTheme();
        TypedArray lightTheme = theme.obtainStyledAttributes(new int[]{R.attr.material_help_lightActionBar});

        if (lightTheme.getBoolean(0, true)) {
            toolbar.setTitleTextColor(Color.BLACK);
            //noinspection ConstantConditions
            toolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        } else {
            toolbar.setTitleTextColor(Color.WHITE);
            //noinspection ConstantConditions
            toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
    }

    private void initRecyclerView() {
        helpList = (RecyclerView) findViewById(R.id.help_list);
        helpList.setLayoutManager(new StickyHeadersLinearLayoutManager<MaterialHelpAdapter>(this));

        helpAdapter = new MaterialHelpAdapter(this, materialHelpList);
        helpList.setAdapter(helpAdapter);

        // disable default animations
        helpList.setItemAnimator(null);
    }

    private void verifyAttributesExist() {
        TypedValue typedValue = new TypedValue();
        boolean lightActionBarExists =
                getTheme().resolveAttribute(R.attr.material_help_lightActionBar, typedValue, true);
        if (!lightActionBarExists) {
            throw new IllegalStateException("Use a theme provided by the library");
        }
    }

    private void onTaskFinished(MaterialHelpList materialHelpList) {
        if (materialHelpList != null) {
            setMaterialHelpList(materialHelpList);
        } else {
            // why we remain here anyway
            finish();
        }
    }

    protected void setMaterialHelpList(MaterialHelpList materialHelpList) {
        this.materialHelpList = materialHelpList;
        helpAdapter.swapMaterialHelpList(materialHelpList);
    }

    protected void refreshMaterialAboutList() {
        helpAdapter.notifyDataSetChanged();
    }

    protected void setScrollToolbar(boolean scrollToolbar) {
        if (toolbar != null) {
            AppBarLayout.LayoutParams params =
                    (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
            if (scrollToolbar) {
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            } else {
                params.setScrollFlags(0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // finish on back click
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // stop list task
        listTask.cancel(true);
    }

    protected abstract MaterialHelpList getMaterialHelpList(Context context);

    protected abstract String getActivityTitle();

    private static class ListTask extends AsyncTask<String, String, MaterialHelpList> {

        private MaterialHelpActivity context;

        ListTask(MaterialHelpActivity context) {
            this.context = context;
        }

        @Override
        protected MaterialHelpList doInBackground(String... params) {
            return isCancelled() ? null : context.getMaterialHelpList(context);
        }

        @Override
        protected void onPostExecute(MaterialHelpList materialHelpList) {
            super.onPostExecute(materialHelpList);

            if (!context.isFinishing()) {
                context.onTaskFinished(materialHelpList);
            }

            context = null;
        }
    }

}
