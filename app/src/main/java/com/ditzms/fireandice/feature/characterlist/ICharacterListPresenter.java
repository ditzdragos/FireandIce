package com.ditzms.fireandice.feature.characterlist;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

interface ICharacterListPresenter {

    void attachView(ICharacterListView view);

    void detachView();

    void loadCharacters();

}
