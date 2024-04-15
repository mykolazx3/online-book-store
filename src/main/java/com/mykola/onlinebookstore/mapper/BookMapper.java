package com.mykola.onlinebookstore.mapper;

import com.mykola.onlinebookstore.config.MapperConfig;
import com.mykola.onlinebookstore.dto.BookDto;
import com.mykola.onlinebookstore.dto.CreateBookRequestDto;
import com.mykola.onlinebookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
