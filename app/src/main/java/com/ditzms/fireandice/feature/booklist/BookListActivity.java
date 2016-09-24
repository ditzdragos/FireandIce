package com.ditzms.fireandice.feature.booklist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ditzms.fireandice.R;
import com.ditzms.fireandice.data.model.Book;
import com.ditzms.fireandice.feature.booklist.recyclerview.BookAdapter;
import com.ditzms.fireandice.feature.characterlist.CharacterListActivity;
import com.ditzms.fireandice.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements IBookListView {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Static methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private final List<Book> mBookList = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private IBookListPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

    public static Intent createIntent(Context context) {
        return new Intent(context, BookListActivity.class);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        mPresenter = new BookListPresenter();
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        if (mBookList.isEmpty()) {
            mPresenter.loadBooks();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    public void onBookListAvailable(List<Book> list) {
        mBookList.clear();
        mBookList.addAll(list);
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.progress_bar).setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mBookAdapter.notifyDataSetChanged();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Private methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        mBookAdapter = new BookAdapter(this, mBookList, new BookAdapter.Listener() {
            @Override
            public void onItemClick(int position) {
                startActivity(CharacterListActivity.createIntent(BookListActivity.this, position));
            }
        });
        mRecyclerView.setAdapter(mBookAdapter);
    }


}
