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
import fr.pierreg.library.activity.BookListActivity;
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
        Book book = books.get(position);
        holder.mItem = book;
        holder.title.setText(book.getTitle());
        holder.price.setText(book.getPrice() + "€");
        Glide.with(holder.image.getContext()).load(book.getCover()).into(holder.image);

       /* holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLandscapeMode) {
                    Bundle arguments = new Bundle();
                    arguments.putString(BookDetailFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.hashCode()));
                    BookDetailFragment fragment = new BookDetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.book_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra(BookDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView title;
        public final TextView price;
        public final ImageView image;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.nameTextView);
            price = (TextView) view.findViewById(R.id.priceTextView);
            image = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}
