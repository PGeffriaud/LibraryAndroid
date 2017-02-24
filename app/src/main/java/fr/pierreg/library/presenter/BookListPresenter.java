package fr.pierreg.library.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.List;

import fr.pierreg.library.R;
import fr.pierreg.library.activity.BookDetailActivity;
import fr.pierreg.library.activity.BookDetailFragment;
import fr.pierreg.library.model.Book;
import fr.pierreg.library.service.HenriPotierService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PierreG on 23/02/17.
 *
 */

public class BookListPresenter {


    public Call<List<Book>> getBooks() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HenriPotierService service = retrofit.create(HenriPotierService.class);

        return service.listBooks();
    }

    public void openBookDetail(View v, Book book, boolean isLandscapeMode) {
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

}
