package valeriafarinosi.U5_W2_D4.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.U5_W2_D4.entities.BlogPost;
import valeriafarinosi.U5_W2_D4.exceptions.ValidationException;
import valeriafarinosi.U5_W2_D4.payloads.PostRequestDTO;
import valeriafarinosi.U5_W2_D4.payloads.PostResponseDTO;
import valeriafarinosi.U5_W2_D4.services.PostService;

import java.util.List;

@RestController
//specalizzazione di @Component
//ogni metodo del controller corrisponderà ad un endpoint differente
@RequestMapping("/blogPosts") //http://localhost:3003/blogPosts  prefisso comune ad ogni endpoint di questo controller
public class PostController {

    //    PostService's DI
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //    1. GET http://localhost:3003/blogPosts -> List<BlogPost>
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return this.postService.getAllPosts();
    }

    //    2. GET http://localhost:3003/blogPosts/{postId} -> one blogPost - path par
    @GetMapping("/{postId}")
    public BlogPost findById(@PathVariable int postId) {
        return this.postService.findById(postId);
    }

    //    3. POST http://localhost:3003/blogPosts + payload -> create a new BlogPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public PostResponseDTO createPost(@RequestBody @Validated PostRequestDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
//            List<String> ma anche: stringa unica oppure HashMap
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);

        }

        BlogPost saved = this.postService.savePost(payload);
        return new PostResponseDTO(saved.getPostId());
    }

    // 4. PUT http://localhost:3003/blogPosts/{postId} + payload -> update the BlogPost with id = postId
    @PutMapping("/{postId}")
    public BlogPost findByIdAndUpdate(@PathVariable int postId, @RequestBody @Validated PostRequestDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
//            List<String> ma anche: stringa unica oppure HashMap
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);

        }

        return this.postService.findByIdAndUpdate(postId, payload);

    }

    // 5. DELETE http://localhost:3003/blogPosts/{postId} -> delete post with id = postId
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void findByIdAndDelete(@PathVariable int postId) {
        this.postService.findByIdAndDelete(postId);
    }


}
