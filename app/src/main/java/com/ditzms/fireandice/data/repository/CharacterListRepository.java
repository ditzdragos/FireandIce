package com.ditzms.fireandice.data.repository;

import android.util.Log;

import com.ditzms.fireandice.data.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class CharacterListRepository implements ICharacterListRepository {

    private static final String TAG = "BookListRepository";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private List<Character> mCharacterList;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CharacterListRepository() {
        mCharacterList = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<Character> getList() {
        return mCharacterList;
    }

    @Override
    public void addAll(List<Character> list) {
        mCharacterList.addAll(list);
    }

    @Override
    public void clear() {
        mCharacterList.clear();
    }

    @Override
    public Character get(int position) {
        try {
            return mCharacterList.get(position);
        } catch (IndexOutOfBoundsException ex) {
            Log.e(TAG, "get: ", ex);
            return null;
        }
    }


}
