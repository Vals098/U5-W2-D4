package valeriafarinosi.U5_W2_D4.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.U5_W2_D4.entities.BlogAuthor;
import valeriafarinosi.U5_W2_D4.exceptions.ValidationException;
import valeriafarinosi.U5_W2_D4.payloads.AuthorRequestDTO;
import valeriafarinosi.U5_W2_D4.payloads.AuthorResponseDTO;
import valeriafarinosi.U5_W2_D4.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    //    AuthorService's DI
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //    1. GET http://localhost:3003/authors -> List<BlogAuthor>
    @GetMapping
    public List<BlogAuthor> getAllAuthors() {
        return this.authorService.getAllAuthors();
    }

    //    2. GET http://localhost:3003/authors/{authorId} -> one BlogAuthor - path par
    @GetMapping("/{authorId}")
    public BlogAuthor findById(@PathVariable int authorId) {
        return this.authorService.findById(authorId);
    }

    //    3. POST http://localhost:3003/authors + payload -> create a new BlogAuthor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public AuthorResponseDTO createAuthor(@RequestBody @Validated AuthorRequestDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
//            List<String> ma anche: stringa unica oppure HashMap
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);

        }

        BlogAuthor saved = this.authorService.saveAuthor(payload);
        return new AuthorResponseDTO(saved.getAuthorId());
    }

    // 4. PUT http://localhost:3003/authors/{authorId} + payload -> update the BlogAuthor with id = authorId
    @PutMapping("/{authorId}")
    public BlogAuthor findByIdAndUpdate(@PathVariable int authorId, @RequestBody @Validated AuthorRequestDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
//            List<String> ma anche: stringa unica oppure HashMap
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);

        }

        return this.authorService.findByIdAndUpdate(authorId, payload);

    }

    // 5. DELETE http://localhost:3003/authors/{authorId} -> delete post with id = authorId
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void findByIdAndDelete(@PathVariable int authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }


}
