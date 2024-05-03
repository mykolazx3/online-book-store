package com.mykola.onlinebookstore.service;

import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.BookSearchParameters;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import com.mykola.onlinebookstore.exception.EntityNotFoundException;
import com.mykola.onlinebookstore.mapper.BookMapper;
import com.mykola.onlinebookstore.model.Book;
import com.mykola.onlinebookstore.repository.book.BookRepository;
import com.mykola.onlinebookstore.repository.book.BookSpecificationBuilder;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(book -> bookMapper.toDto(book))
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        Optional<Book> bookFromDB = bookRepository.findById(id);
        return bookMapper.toDto(bookFromDB.orElseThrow(() ->
                new EntityNotFoundException("Can`t find book with id " + id + " in DB")));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book updatedBook = bookRepository.findById(id).map(bookById -> {
            Book book = bookMapper.toModel(requestDto);
            book.setId(bookById.getId());
            return book;
        }).orElseThrow(() -> new EntityNotFoundException("Can't update book with id " + id));
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    public List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
