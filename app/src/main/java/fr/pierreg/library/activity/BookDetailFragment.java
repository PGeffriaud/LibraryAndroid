package fr.pierreg.library.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.stream.Collectors;

import fr.pierreg.library.R;
import fr.pierreg.library.model.Book;

public class BookDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Book book;

    public BookDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            book = getArguments().getParcelable(ARG_ITEM_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_detail, container, false);

        if (book != null) {
            ((TextView) rootView.findViewById(R.id.book_title)).setText(book.getTitle());

            ImageView cover = (ImageView) rootView.findViewById(R.id.book_cover);
            Glide.with(this).load(book.getCover()).into(cover);

            ((TextView)rootView.findViewById(R.id.book_price)).setText(book.getPrice() + getResources().getString(R.string.money_symbol));

            String synopsis = Arrays.asList(book.getSynopsis()).stream().collect(Collectors.joining("\n"));
            ((TextView)rootView.findViewById(R.id.book_synopsis)).setText(synopsis);


        }

        return rootView;
    }
}
