package com.ditzms.fireandice.data.model;

import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
@Table
public class Character {

    /**
     * {
     * "url": "https://anapioficeandfire.com/api/characters/2",
     * "name": "Walder",
     * "gender": "Male",
     * "culture": "",
     * "born": "",
     * "died": "",
     * "titles": [],
     * "aliases": ["Hodor"],
     * "father": "",
     * "mother": "",
     * "spouse": "",
     * "allegiances": ["https://anapioficeandfire.com/api/houses/362"],
     * "books": ["https://anapioficeandfire.com/api/books/1",..],
     * "povBooks": [],
     * "tvSeries": ["Season 1",..],
     * "playedBy": ["Kristian Nairn"]
     * }
     */

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private Long id;  /*Needed by SugarORM*/

    @SerializedName("url")
    private String mUrl;
    @SerializedName("name")
    private String mName;
    @SerializedName("gender")
    private String mGender;


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public Character() {

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////


    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

}
