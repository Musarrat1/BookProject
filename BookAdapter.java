package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.test1.databinding.ActivityAddBookBinding;

import java.util.List;

public class BookAdapter extends ArrayAdapter<BookData> {
    private final Context context;
    private final int resource;
    private final List<BookData> bookList;

    public BookAdapter(Context context, int resource, List<BookData> bookList) {
        super(context, resource, bookList);
        this.context = context;
        this.resource = resource;
        this.bookList = bookList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        BookData book = bookList.get(position);

        EditText BookTitle = listItemView.findViewById(R.id.editText_book_title);
        EditText Author = listItemView.findViewById(R.id.editText_book_author);
        EditText Genre = listItemView.findViewById(R.id.editText_book_genre);
        EditText Description = listItemView.findViewById(R.id.editText_book_description);

        BookTitle.setText(book.getBookTitle());
        Author.setText("Author: " + book.getAuthor());
        Genre.setText("Genre: " + book.getGener());
        Description.setText("Description: " + book.getDescription());
        return listItemView;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item =menu.findItem(R.id.search_bar);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionalMenu(menu);


    }

    private ActivityAddBookBinding getMenuInflater() {
    }

}
