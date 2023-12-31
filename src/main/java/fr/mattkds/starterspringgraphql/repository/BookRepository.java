package fr.mattkds.starterspringgraphql.repository;

import fr.mattkds.starterspringgraphql.Book;
import fr.mattkds.starterspringgraphql.Rating;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Book repository
 * To keep this starter simple, we just store simple data on a collection
 * To Persist this data, a more complex implementation could be done by using Spring Data JPA and a real Database.
 */
@Repository
public class BookRepository {

    private final AuthorRepository authorRepository;

    /**
     * This is the default list of authors build on the initialisation, this list is filled in with the init method
     */
    private List<Book> books = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findOne(Integer id) {
        return books.stream()
                .filter(book -> book.id() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
    }

    /**
     * Method to initialize a simple Database
     * PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization.
     */
    @PostConstruct
    private void init() {
        books.add(new Book(1,
                "Reactive Spring",
                484,
                Rating.FIVE_STARS,
                authorRepository.findByName("Josh Long")));
        books.add(new Book(2,
                "Spring Boot Up & Running",
                328,
                Rating.FIVE_STARS,
                authorRepository.findByName("Mark Heckler")));
        books.add(new Book(3,
                "Hacking with Spring Boot 2.3",
                392,
                Rating.FIVE_STARS,
                authorRepository.findByName("Greg Turnquist")));
    }

}