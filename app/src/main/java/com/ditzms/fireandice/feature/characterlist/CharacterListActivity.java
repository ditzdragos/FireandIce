package com.ditzms.fireandice.feature.characterlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ditzms.fireandice.R;
import com.ditzms.fireandice.feature.characterlist.recyclerview.CharacterAdapter;
import com.ditzms.fireandice.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterListActivity extends AppCompatActivity implements ICharacterListView {

    public static final String POSITION = "POSITION";
    private final List<String> mCharacterUrls = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private int mPosition;
    private ICharacterListPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private CharacterAdapter mCharacterAdapter;

    public static Intent createIntent(Context context, int position) {
        Intent intent = new Intent(context, CharacterListActivity.class);
        intent.putExtra(POSITION, position);
        return intent;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Intent intent = getIntent();
        if (intent != null) {
            mPosition = intent.getIntExtra(POSITION, -1);
        }

        mPresenter = new CharacterListPresenter(mPosition);
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        if (mCharacterUrls.isEmpty()) {
            mPresenter.loadCharacters();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    public void onCharacterUrlListAvailable(String[] urlArrays) {
        mCharacterUrls.clear();
        mCharacterUrls.addAll(Arrays.asList(urlArrays));
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.progress_bar).setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mCharacterAdapter.notifyDataSetChanged();
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
        mCharacterAdapter = new CharacterAdapter(this, mCharacterUrls);
        mRecyclerView.setAdapter(mCharacterAdapter);
    }


}
