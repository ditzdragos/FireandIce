package com.ditzms.fireandice.feature.booklist.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ditzms.fireandice.R;
import com.ditzms.fireandice.data.model.Book;

import java.util.List;

/**
 * Created by Dragos-Daniel Dit on 9/24/2016.
 */
public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Fields
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private Context mContext;
    private List<Book> mBookList;
    private Listener mListener;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Constructor
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public BookAdapter(Context context, List<Book> bookList, Listener listener) {
        mContext = context;
        mBookList = bookList;
        mListener = listener;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // 
    //  Public methods
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookHolder holder, int position) {
        holder.textView.setText(mBookList.get(position).getName());
        holder.textView.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Click listener
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public interface Listener {
        public void onItemClick(int position);
    }
}
