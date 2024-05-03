package com.mykola.onlinebookstore.controller;

import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.BookSearchParameters;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import com.mykola.onlinebookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Return page of books",
            description = "Return page of books with pagination and sorting")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Return single book by id", description = "Return single book by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Create a new book if isbn uniq")
    public BookDto creteBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a book", description = "Update a book if exist")
    public BookDto update(@PathVariable Long id,
                          @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.updateById(id, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id if exist")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Return filtered page of books",
            description = "Return filtered page of books with pagination and sorting. "
                    + "Parameters: title, author, isbn, price")
    public List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

}
