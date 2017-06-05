package com.ivianuu.materialhelp.sample;

import android.content.Context;

import com.ivianuu.materialhelp.MaterialHelpActivity;
import com.ivianuu.materialhelp.model.MaterialHelpItem;
import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.materialhelp.model.MaterialHelpSubHeader;

/**
 * Author IVIanuu.
 */

public class HelpActivity2 extends MaterialHelpActivity {

    @Override
    protected MaterialHelpList getMaterialHelpList(Context context) {
        MaterialHelpList.Builder listBuilder = new MaterialHelpList.Builder();

        MaterialHelpSubHeader meinHeader1 = new MaterialHelpSubHeader.Builder()
                .withTitle("Header 1")
                .build();
        listBuilder.addItem(meinHeader1);

        MaterialHelpItem wieVielUhrIstEsItem = new MaterialHelpItem.Builder()
                .withTitle("Wie viel Uhr ist es ?")
                .withExpandedText("Schau doch auf die Uhr du idiot")
                .build();
        listBuilder.addItem(wieVielUhrIstEsItem);

        return listBuilder.build();
    }

    @Override
    protected String getActivityTitle() {
        return "Meine Hilfe 2";
    }
}
