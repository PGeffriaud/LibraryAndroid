package fr.pierreg.library.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fr.pierreg.library.R;
import fr.pierreg.library.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent intent = new Intent(this, BookListActivity.class);
            Book selectedBook = getIntent().getParcelableExtra(BookDetailFragment.ARG_ITEM_ID);
            intent.putExtra(BookDetailFragment.ARG_ITEM_ID, selectedBook);
            navigateUpTo(intent);
        } else {

            setContentView(R.layout.activity_book_detail);
            Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
            setSupportActionBar(toolbar);

            // Show the Up button in the action bar.
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            if (savedInstanceState == null) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(BookDetailFragment.ARG_ITEM_ID,
                        getIntent().getParcelableExtra(BookDetailFragment.ARG_ITEM_ID));
                BookDetailFragment fragment = new BookDetailFragment();
                fragment.setArguments(arguments);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.book_detail_container, fragment)
                        .commit();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, BookListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
