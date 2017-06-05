package com.ivianuu.materialhelp.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ivianuu.materialhelp.MaterialHelpActivity;
import com.ivianuu.materialhelp.model.MaterialHelpItem;
import com.ivianuu.materialhelp.model.MaterialHelpList;
import com.ivianuu.materialhelp.model.MaterialHelpSubHeader;

/**
 * Author IVIanuu.
 */

public class HelpActivity extends MaterialHelpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScrollToolbar(true);
    }

    @Override
    protected MaterialHelpList getMaterialHelpList(Context context) {
        MaterialHelpList.Builder helpListBuilder = new MaterialHelpList.Builder();

        MaterialHelpSubHeader helpHeader = new MaterialHelpSubHeader.Builder()
                .withTitle("Fragen und Antworten")
                .build();
        helpListBuilder.addItem(helpHeader);

        MaterialHelpItem mamasFrage = new MaterialHelpItem.Builder()
                .withTitle("Wann essen wir heute")
                .withExpandedText("Du kannst jetzt essen machen gehen")
                .build();
        helpListBuilder.addItem(mamasFrage);

        MaterialHelpSubHeader animalHeader = new MaterialHelpSubHeader.Builder()
                .withTitle("Animals")
                .build();
        helpListBuilder.addItem(animalHeader);

        MaterialHelpItem dogItem = new MaterialHelpItem.Builder()
                .withTitle("Dog")
                .withExpandedText("Has 4 feet")
                .build();
        helpListBuilder.addItem(dogItem);

        MaterialHelpItem catItem = new MaterialHelpItem.Builder()
                .withTitle("Cat")
                .withExpandedText("Hallo Mama das ist eine Katze")
                .build();
        helpListBuilder.addItem(catItem);

        MaterialHelpSubHeader drinkSubHeader = new MaterialHelpSubHeader.Builder()
                .withTitle("Drinks")
                .build();
        helpListBuilder.addItem(drinkSubHeader);

        MaterialHelpItem colaItem = new MaterialHelpItem.Builder()
                .withTitle("Cola")
                .withExpandedText("Is a soft drink")
                .build();
        helpListBuilder.addItem(colaItem);

        MaterialHelpItem fantaItem = new MaterialHelpItem.Builder()
                .withTitle("Fanta")
                .withExpandedText("is also an soft drink haha")
                .build();
        helpListBuilder.addItem(fantaItem);

        MaterialHelpItem spriteItem = new MaterialHelpItem.Builder()
                .withTitle("Sprite")
                .withExpandedText("Not that bad")
                .build();
        helpListBuilder.addItem(spriteItem);

        MaterialHelpItem redBullItem = new MaterialHelpItem.Builder()
                .withTitle("Red Bull")
                .withExpandedText("I'm going to fly away haha")
                .build();
        helpListBuilder.addItem(redBullItem);

        MaterialHelpSubHeader footSubHeader = new MaterialHelpSubHeader.Builder()
                .withTitle("Foots")
                .build();
        helpListBuilder.addItem(footSubHeader);

        MaterialHelpItem pizzaItem = new MaterialHelpItem.Builder()
                .withTitle("Pizza")
                .withExpandedText("So nice i love pizza")
                .build();
        helpListBuilder.addItem(pizzaItem);

        MaterialHelpItem kebabItem = new MaterialHelpItem.Builder()
                .withTitle("Kebab")
                .withExpandedText("A foot which is so nice but only well known in germany")
                .build();
        helpListBuilder.addItem(kebabItem);

        MaterialHelpItem tunaItem = new MaterialHelpItem.Builder()
                .withTitle("Tuna")
                .withExpandedText("type of fish")
                .build();
        helpListBuilder.addItem(tunaItem);

        MaterialHelpItem pastaItem = new MaterialHelpItem.Builder()
                .withTitle("Pasta")
                .withExpandedText("pasta is so nice")
                .build();
        helpListBuilder.addItem(pastaItem);

        MaterialHelpSubHeader randomSubHeader = new MaterialHelpSubHeader.Builder()
                .withTitle("Random sh***t")
                .build();
        helpListBuilder.addItem(randomSubHeader);

        MaterialHelpItem veryLongItem = new MaterialHelpItem.Builder()
                .withTitle("Very looong item")
                .withExpandedText("kjangknawdklmnlskmnlkysncjklnylsnclknaskjnjakwnckjynsckjnskljycnkjanvkljnaeljkvnlwsnmcln<cjknjlynckj<ncjk<njkn<  I LOVE VAGINAS knafknjawnjknsdcknjawnsjkasncanjkcnjakcnnakcnakjcnkancvkanjkad  AND TITS kjynfkjfnsajkcnksdnjksnkncknckjsknacsnkacsnacssckjnaca OH MEN AND SOME NICE BUTTS uafnksnfjksnckjsnckancnskkjscnjjkscnkjscnkjsnjcksncjkjncKcnKCNC OK THIS SHOULD BE ENOUGH")
                .build();
        helpListBuilder.addItem(veryLongItem);

        MaterialHelpItem veryShortItem = new MaterialHelpItem.Builder()
                .withTitle("Very short item")
                .withExpandedText(" ")
                .build();
        helpListBuilder.addItem(veryShortItem);

        MaterialHelpItem dummy1 = new MaterialHelpItem.Builder()
                .withTitle("dummy 1")
                .withExpandedText("")
                .build();
        helpListBuilder.addItem(dummy1);

        MaterialHelpItem dummy2 = new MaterialHelpItem.Builder()
                .withTitle("dummy 2")
                .withExpandedText("")
                .build();
        helpListBuilder.addItem(dummy2);

        MaterialHelpItem dummy3 = new MaterialHelpItem.Builder()
                .withTitle("dummy 3")
                .withExpandedText("")
                .build();
        helpListBuilder.addItem(dummy3);

        MaterialHelpItem dummy4 = new MaterialHelpItem.Builder()
                .withTitle("dummy 4")
                .withExpandedText("")
                .build();
        helpListBuilder.addItem(dummy4);

        MaterialHelpItem dummy5 = new MaterialHelpItem.Builder()
                .withTitle("dummy 5")
                .withExpandedText("")
                .build();
        helpListBuilder.addItem(dummy5);

        return helpListBuilder.build();
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.help_title);
    }
}
