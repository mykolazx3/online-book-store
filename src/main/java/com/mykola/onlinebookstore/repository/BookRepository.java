package com.mykola.onlinebookstore.repository;

import com.mykola.onlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
