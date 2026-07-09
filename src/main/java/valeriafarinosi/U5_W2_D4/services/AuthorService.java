package valeriafarinosi.U5_W2_D4.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valeriafarinosi.U5_W2_D4.entities.BlogAuthor;
import valeriafarinosi.U5_W2_D4.exceptions.BadRequestException;
import valeriafarinosi.U5_W2_D4.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D4.payloads.AuthorRequestDTO;
import valeriafarinosi.U5_W2_D4.repositories.AuthorRepository;

import java.util.List;

@Service
@Slf4j
public class AuthorService {

    //    private List<BlogAuthor> authorDB = new ArrayList<>();

    //    AuthorRepository's DI
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    //    1.
    public List<BlogAuthor> getAllAuthors() {
        return this.authorRepository.findAll();
    }


    //    2.
    public BlogAuthor findById(int authorId) {
//        ----------------------------------- List ------------------------------------------
//        BlogAuthor found = null;
//
//        for (BlogAuthor blogAuthor : this.authorDB) {
//            if (blogAuthor.getAuthorId() == authorId) found = blogAuthor;
//        }
//        if (found == null) throw new NotFoundException(authorId);
//
//        return found;

//        ----------------------------------- Repository ------------------------------------------
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

    }


    //    3.
    public BlogAuthor saveAuthor(AuthorRequestDTO payload) {

//        controlli
        if (this.authorRepository.existsByEmail(payload.email()))
            throw new BadRequestException("The email address " + payload.email() + " is already in use.");

        BlogAuthor newAuthor = new BlogAuthor(payload.name(), payload.surname(), payload.email(), payload.birthDate());

        BlogAuthor saved = this.authorRepository.save(newAuthor);
        log.info("The author " + newAuthor + " has been saved!");
        return saved;

    }


    //    4.
    public BlogAuthor findByIdAndUpdate(int authorId, AuthorRequestDTO payload) {
//        ----------------------------------- List ------------------------------------------
//        BlogAuthor found = null;

//        for (BlogAuthor author : this.authorDB) {
//            if (author.getAuthorId() == authorId) {
//                found = author;
//                found.setName(payload.getName());
//                found.setSurname(payload.getSurname());
//                found.setEmail(payload.getEmail());
//                found.setBirthDate(payload.getBirthDate());
//            }
//        }
//        if (found == null) throw new NotFoundException(authorId);
//
//        return found;

//        ----------------------------------- Repository ------------------------------------------
        BlogAuthor found = this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setBirthDate(payload.birthDate());

        return this.authorRepository.save(found);
    }


    // 5.
    public void findByIdAndDelete(int authorId) {
//        ----------------------------------- List ------------------------------------------
//        BlogAuthor found = null;
//
//        for (BlogAuthor author : this.authorDB) {
//            if (author.getAuthorId() == authorId) found = author;
//        }
//
//        if (found == null) throw new NotFoundException(authorId);
//
//        this.authorRepository.delete(found);

//        ----------------------------------- Repository ------------------------------------------
        BlogAuthor found = this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

        this.authorRepository.delete(found);

    }


}
