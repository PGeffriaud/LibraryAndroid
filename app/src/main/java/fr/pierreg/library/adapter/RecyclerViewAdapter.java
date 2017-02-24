package fr.pierreg.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.pierreg.library.R;
import fr.pierreg.library.model.Book;
import fr.pierreg.library.presenter.BookListPresenter;

/**
 * Created by PierreG on 23/02/17.
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Book> books;
    private final Boolean isLandscapeMode;
    private final BookListPresenter presenter;

    public RecyclerViewAdapter(List<Book> items, boolean isLandscapeMode, BookListPresenter presenter) {
        this.books = items;
        this.isLandscapeMode = isLandscapeMode;
        this.presenter = presenter;
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

        holder.mView.setOnClickListener(v -> presenter.openBookDetail(v, book, isLandscapeMode));
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
