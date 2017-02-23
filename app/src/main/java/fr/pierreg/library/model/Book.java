package fr.pierreg.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PierreG on 23/02/17.
 *
 */

public class Book implements Parcelable {

    private String isbn;
    private String title;
    private String price;
    private String cover;

    protected Book(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        price = in.readString();
        cover = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getCover() {
        return cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn.equals(book.isbn);

    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(cover);
    }
}