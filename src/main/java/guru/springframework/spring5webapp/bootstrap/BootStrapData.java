package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRepository;
import guru.springframework.spring5webapp.respositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("MyPublishing");
        publisher.setCity("Gent");
        publisher.setState("Oost Vlaandern");

        publisherRepository.save(publisher);
        System.out.println("Number of publishers: " + publisherRepository.count());


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "111132");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author mika = new Author("micco", "jicco");
        Book nuro = new Book("Nuro Bezede", "123444");
        mika.getBooks().add(nuro);
        nuro.getAuthors().add(mika);
        nuro.setPublisher(publisher);
        publisher.getBooks().add(nuro);

        authorRepository.save(mika);
        bookRepository.save(nuro);

        System.out.println("Started in BootStrap");
        System.out.println("Number of Books "+ bookRepository.count());
        System.out.println("Publisher no of books " + publisher.getBooks().size());



    }
}
