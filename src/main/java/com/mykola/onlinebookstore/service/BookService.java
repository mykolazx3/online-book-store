package com.mykola.onlinebookstore.service;

import com.mykola.onlinebookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
