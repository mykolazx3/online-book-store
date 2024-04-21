package com.mykola.onlinebookstore.repository.book;

import com.mykola.onlinebookstore.dto.BookSearchParameters;
import com.mykola.onlinebookstore.model.Book;
import com.mykola.onlinebookstore.repository.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book, BookSearchParameters> {

    private final BookSpecificationProviderManager bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {

        Specification<Book> spec = Specification.where(null);

        if (searchParameters.getTitles() != null && searchParameters.getTitles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParameters.getTitles()));
        }

        if (searchParameters.getAuthors() != null && searchParameters.getAuthors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.getAuthors()));
        }

        return spec;
    }
}
