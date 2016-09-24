package com.ditzms.fireandice.data.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class Book extends SugarRecord {

    /**
     * url : https://anapioficeandfire.com/api/books/1
     * name : A Game of Thrones
     * isbn : 978-0553103540
     * authors : ["George R. R. Martin"]
     * numberOfPages : 694
     * publisher : Bantam Books
     * country : United States
     * mediaType : Hardcover
     * released : 1996-08-01T00:00:00
     * characters : ["https://anapioficeandfire.com/api/characters/2",..]
     * povCharacters : ["https://anapioficeandfire.com/api/characters/148",..]
     */

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @SerializedName("url")
    private String mUrl;
    @SerializedName("name")
    private String mName;
    @SerializedName("isbn")
    private String mIsbn;
    @SerializedName("numberOfPages")
    private int mNumberOfPages;
    @SerializedName("publisher")
    private String mPublisher;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("mediaType")
    private String mMediaType;
    @SerializedName("released")
    private String mReleased;
    @SerializedName("authors")
    private String[] mAuthors;
    @SerializedName("characters")
    private String[] mCharacters;
    @SerializedName("povCharacters")
    private String[] mPovCharacters;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public Book() {

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

    public String getIsbn() {
        return mIsbn;
    }

    public void setIsbn(String isbn) {
        mIsbn = isbn;
    }

    public int getNumberOfPages() {
        return mNumberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        mNumberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public String getReleased() {
        return mReleased;
    }

    public void setReleased(String released) {
        mReleased = released;
    }

    public String[] getAuthors() {
        return mAuthors;
    }

    public void setAuthors(String[] authors) {
        mAuthors = authors;
    }

    public String[] getCharacters() {
        return mCharacters;
    }

    public void setCharacters(String[] characters) {
        mCharacters = characters;
    }

    public String[] getPovCharacters() {
        return mPovCharacters;
    }

    public void setPovCharacters(String[] povCharacters) {
        mPovCharacters = povCharacters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (mUrl != null ? !mUrl.equals(book.mUrl) : book.mUrl != null) return false;
        return mName != null ? mName.equals(book.mName) : book.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mUrl != null ? mUrl.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }
}
