package fr.pierreg.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import fr.pierreg.library.R;
import fr.pierreg.library.adapter.RecyclerViewAdapter;
import fr.pierreg.library.dummy.DummyContent;
import fr.pierreg.library.model.Book;
import fr.pierreg.library.presenter.BookListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private boolean isLandscapeMode;

    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        if (findViewById(R.id.book_detail_container) != null) {
            isLandscapeMode = true;
        }

        Timber.plant(new Timber.DebugTree());

        presenter = new BookListPresenter();
        presenter.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
                recyclerView.setAdapter(new RecyclerViewAdapter(response.body(), isLandscapeMode));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.e(t);
            }
        });


    }

}
