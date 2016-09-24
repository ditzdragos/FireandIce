package com.ditzms.fireandice.data.repository;

import com.ditzms.fireandice.data.model.Character;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

public interface ICharacterListRepository {

    void addAll(List<Character> list);

    void clear();

    Character get(int position);

    List<Character> getList();

}
