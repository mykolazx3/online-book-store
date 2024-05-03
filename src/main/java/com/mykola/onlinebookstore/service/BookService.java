package com.mykola.onlinebookstore.service;

import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.BookSearchParameters;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findBookById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable);
}
