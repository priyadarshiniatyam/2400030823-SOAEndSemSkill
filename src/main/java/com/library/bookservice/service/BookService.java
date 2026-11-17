package com.library.bookservice.service;

import com.library.bookservice.entity.Book;
import com.library.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        if (repository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBook(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found: " + id));
    }

    public Book updateBook(Long id, Book updated) {
        Book book = getBook(id);

        if (!book.getIsbn().equals(updated.getIsbn())
                && repository.existsByIsbn(updated.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }

        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setIsbn(updated.getIsbn());

        return repository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBook(id);
        repository.delete(book);
    }
}