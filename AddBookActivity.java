package com.example.test1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextBookTitle, editTextBookAuthor, editTextBookGenre, editTextBookDescription;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Book");
        }

        progressBar = findViewById(R.id.progress_bar);
        editTextBookTitle = findViewById(R.id.editText_book_title);
        editTextBookAuthor = findViewById(R.id.editText_book_author);
        editTextBookGenre = findViewById(R.id.editText_book_genre);
        editTextBookDescription = findViewById(R.id.editText_book_description);

        Button buttonAddBook = findViewById(R.id.button_addBook);
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }

    private void addBook() {
        String bookTitle = editTextBookTitle.getText().toString().trim();
        String bookAuthor = editTextBookAuthor.getText().toString().trim();
        String bookGenre = editTextBookGenre.getText().toString().trim();
        String bookDescription = editTextBookDescription.getText().toString().trim();

        if (bookTitle.isEmpty()) {
            editTextBookTitle.setError("Please enter book title");
            editTextBookTitle.requestFocus();
            return;
        }

        if (bookAuthor.isEmpty()) {
            editTextBookAuthor.setError("Please enter book author");
            editTextBookAuthor.requestFocus();
            return;
        }

        if (bookGenre.isEmpty()) {
            editTextBookGenre.setError("Please enter book genre");
            editTextBookGenre.requestFocus();
            return;
        }

        if (bookDescription.isEmpty()) {
            editTextBookDescription.setError("Please enter book description");
            editTextBookDescription.requestFocus();
            return;
        }

        // Proceed with adding the book to Firebase Database
        progressBar.setVisibility(View.VISIBLE);

        DatabaseReference databaseBooks = FirebaseDatabase.getInstance().getReference("Books");
        String bookId = databaseBooks.push().getKey();

        Book book = new Book(bookId, bookTitle, bookAuthor, bookGenre, bookDescription);

        if (bookId != null) {
            databaseBooks.child(bookId).setValue(book, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                    progressBar.setVisibility(View.GONE);
                    if (error == null) {
                        Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(AddBookActivity.this, "Failed to add book: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void clearFields() {
        editTextBookTitle.setText("");
        editTextBookAuthor.setText("");
        editTextBookGenre.setText("");
        editTextBookDescription.setText("");
    }
}
