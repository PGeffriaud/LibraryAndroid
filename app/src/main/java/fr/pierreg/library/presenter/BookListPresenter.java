package fr.pierreg.library.presenter;

import java.util.List;

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

    private Book lastBookSelected;

    public Call<List<Book>> getBooks() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HenriPotierService service = retrofit.create(HenriPotierService.class);

        return service.listBooks();
    }

    public Book getLastBookSelected() {
        return lastBookSelected;
    }

    public void setLastBookSelected(Book lastBookSelected) {
        this.lastBookSelected = lastBookSelected;
    }
}
