package fr.pierreg.library;

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


import fr.pierreg.library.adapter.RecyclerViewAdapter;
import fr.pierreg.library.dummy.DummyContent;
import fr.pierreg.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private boolean isLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);

        if (findViewById(R.id.book_detail_container) != null) {
            isLandscapeMode = true;
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(getBooks(), isLandscapeMode));
    }

    // TO REMOVE
    List<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for(int i=0; i<20; i++) {
            books.add(new Book("Henry Pottier Tome "+i, "20 €"));
        }
        return books;
    }

}
