package com.ditzms.fireandice.data.network;

import com.ditzms.fireandice.data.model.Book;
import com.ditzms.fireandice.data.model.Character;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */

public interface FireAndIceApi {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET
    Call<Book> getBook(@Url String url);

    @GET
    Call<Character> getCharacter(@Url String url);

}
