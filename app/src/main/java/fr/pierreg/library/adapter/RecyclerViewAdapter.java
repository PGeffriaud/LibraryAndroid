package fr.pierreg.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.pierreg.library.R;
import fr.pierreg.library.activity.BookDetailActivity;
import fr.pierreg.library.activity.BookDetailFragment;
import fr.pierreg.library.model.Book;

/**
 * Created by PierreG on 23/02/17.
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Book> books;
    private final Boolean isLandscapeMode;

    public RecyclerViewAdapter(List<Book> items, boolean isLandscapeMode) {
        this.books = items;
        this.isLandscapeMode = isLandscapeMode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        final Book book = books.get(position);
        holder.mItem = book;
        holder.title.setText(book.getTitle());
        holder.price.setText(book.getPrice() + holder.mView.getResources().getString(R.string.money_symbol));
        Glide.with(holder.mView.getContext()).load(book.getCover()).into(holder.image);

        holder.mView.setOnClickListener(v -> openBookDetail(v, book));
    }

    private void openBookDetail(View v, Book book) {
        if (isLandscapeMode) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(BookDetailFragment.ARG_ITEM_ID, book);
            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);

            ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.book_detail_container, fragment)
                    .commit();
        } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra(BookDetailFragment.ARG_ITEM_ID, book);

            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        final TextView title;
        final TextView price;
        final ImageView image;
        Book mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.nameTextView);
            price = (TextView) view.findViewById(R.id.priceTextView);
            image = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
