package com.ivianuu.materialhelp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.ivianuu.materialhelp.adapter.MaterialHelpAdapter;

import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.stickyheaders.StickyHeadersLinearLayoutManager;

/**
 * Author IVIanuu.
 */

public abstract class MaterialHelpActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private MaterialHelpAdapter mMaterialHelpAdapter;

    private MaterialHelpList mMaterialHelpList = new MaterialHelpList.Builder().build();

    private ListTask mListTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyAttributesExist();

        setContentView(R.layout.activity_material_help);

        initToolbar();
        initRecyclerView();

        // Get material list
        mListTask = new ListTask(this);
        mListTask.execute();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        String title = getActivityTitle();

        if (title == null) title = getString(R.string.material_help_title);

        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Resources.Theme theme = getTheme();
        TypedArray lightTheme = theme.obtainStyledAttributes(new int[]{R.attr.material_help_lightActionBar});

        if (lightTheme.getBoolean(0, true)) {
            mToolbar.setTitleTextColor(Color.BLACK);
            mToolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        } else {
            mToolbar.setTitleTextColor(Color.WHITE);
            mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StickyHeadersLinearLayoutManager<MaterialHelpAdapter>(this));

        mMaterialHelpAdapter = new MaterialHelpAdapter(this, mMaterialHelpList);
        mRecyclerView.setAdapter(mMaterialHelpAdapter);

        // disable default animations
        mRecyclerView.setItemAnimator(null);
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
        mMaterialHelpList = materialHelpList;
        mMaterialHelpAdapter.swapMaterialHelpList(mMaterialHelpList);
    }

    protected void refreshMaterialAboutList() {
        mMaterialHelpAdapter.notifyDataSetChanged();
    }

    protected void setScrollToolbar(boolean scrollToolbar) {
        if (mToolbar != null) {
            AppBarLayout.LayoutParams params =
                    (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
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
        mListTask.cancel(true);
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
