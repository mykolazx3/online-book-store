package com.mykola.onlinebookstore.service;

import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import com.mykola.onlinebookstore.model.Book;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findBookById(Long id);
}
