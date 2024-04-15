package com.mykola.onlinebookstore.service;

import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import com.mykola.onlinebookstore.exception.EntityNotFoundException;
import com.mykola.onlinebookstore.mapper.BookMapper;
import com.mykola.onlinebookstore.model.Book;
import com.mykola.onlinebookstore.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toDto(book))
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        Optional<Book> bookFromDB = bookRepository.findById(id);
        return bookMapper.toDto(bookFromDB.orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id " + id + " in DB")));
    }
}
