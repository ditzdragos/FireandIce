package com.ditzms.fireandice.feature.characterlist.recyclerview;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ditzms.fireandice.R;
import com.ditzms.fireandice.application.MyApplication;
import com.ditzms.fireandice.data.model.Character;
import com.ditzms.fireandice.utils.ThreadUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterHolder> {

    private static final String TAG = "CharacterAdapter";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private Context mContext;
    private List<String> mList;
    private HashMap<String, String> mCharacterNameMap = new HashMap<>();

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public CharacterAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharacterHolder holder, int position) {
        final String url = mList.get(position);

        if (mCharacterNameMap.containsKey(url)) {
            setTextInView(holder, mCharacterNameMap.get(url));

        } else {
            setTextInView(holder, R.string.loading);

            ThreadUtils.runOnBackgroundThread(new Runnable() {
                @Override
                public void run() {

                    Call<Character> call = MyApplication.getFireAndIceClient().getFireAndIceApi().getCharacter(url);

                    try {
                        Response<Character> response = call.execute();
                        if (response != null && response.isSuccessful()) {
                            mCharacterNameMap.put(url, response.body().getName());
                            setTextInView(holder, response.body().getName());
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "run: ", e);
                    }
                }
            });
        }

    }

    private void setTextInView(final CharacterHolder holder, final String text) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                holder.textView.setText(text);
            }
        });
    }

    private void setTextInView(final CharacterHolder holder, @StringRes final int stringId) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                holder.textView.setText(stringId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
