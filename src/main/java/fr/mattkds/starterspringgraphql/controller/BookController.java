package fr.mattkds.starterspringgraphql.controller;

import fr.mattkds.starterspringgraphql.Book;
import fr.mattkds.starterspringgraphql.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Simple RestController to expose endpoints which using GraphQL Query Language for this API
 * GraphQL isn't tied to any specific database or storage engine
 */
@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * findAll Endpoint, this endpoint is written with the basic GraphQL annotation syntax
     * The SchemaMapping annotation needs a typeName (Query, Mutation, Subscription) and the value of the schema type referenced in the schema.graphqls file
     * @return a list of Books
     */
    @SchemaMapping(typeName = "Query",value = "allBooks")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * This findOne endpoint is written with the spring GraphQL annotation
     * QueryMappping is referenced directly to a SchemaMapping on type Query
     * it could also be a MutationMapping or a SubscriptionMapping, the name of the schema is by default the method name
     * @param id the id of the book to return
     * @return the corresponding book data
     */
    @QueryMapping
    public Book findOne(@Argument Integer id) {
        return bookRepository.findOne(id);
    }
}