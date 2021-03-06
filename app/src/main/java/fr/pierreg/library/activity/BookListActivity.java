package fr.pierreg.library.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import fr.pierreg.library.R;
import fr.pierreg.library.adapter.RecyclerViewAdapter;
import fr.pierreg.library.model.Book;
import fr.pierreg.library.presenter.BookListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BookListActivity extends AppCompatActivity {

    private boolean isLandscapeMode;

    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        bar.setTitle(R.string.app_name);

        isLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        Timber.plant(new Timber.DebugTree());

        presenter = new BookListPresenter();
        presenter.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(BookListActivity.this));
                recyclerView.setAdapter(new RecyclerViewAdapter(response.body(), isLandscapeMode, presenter));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.e(t);
            }
        });


        Book selectedBook = getIntent().getParcelableExtra(BookDetailFragment.ARG_ITEM_ID);
        Timber.i("Last book selected: " + String.valueOf(selectedBook));
        if(selectedBook != null) {
            presenter.openBookDetail(this.findViewById(R.id.list_layout), selectedBook, isLandscapeMode);
        }
    }
}
