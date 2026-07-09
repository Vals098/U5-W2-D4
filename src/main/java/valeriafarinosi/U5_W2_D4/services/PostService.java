package valeriafarinosi.U5_W2_D4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valeriafarinosi.U5_W2_D4.entities.BlogAuthor;
import valeriafarinosi.U5_W2_D4.entities.BlogPost;
import valeriafarinosi.U5_W2_D4.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D4.payloads.PostRequestDTO;
import valeriafarinosi.U5_W2_D4.repositories.PostRepository;

import java.util.List;

@Service
@Slf4j
public class PostService {

//    private List<BlogPost> postsDB = new ArrayList<>();

    //    PostRepository's DI
    private final PostRepository postRepository;
    //    AuthorService's DI for authorId
    private final AuthorService authorService;

    public PostService(PostRepository postRepository, AuthorService authorService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
    }


    //    1.
    public List<BlogPost> getAllPosts() {
        return this.postRepository.findAll();
    }


    //    2.
    public BlogPost findById(int postId) {
//        ----------------------------------- List ------------------------------------------
//        BlogPost found = null;
//
//        for (BlogPost blogPost : this.postsDB) {
//            if (blogPost.getPostId() == postId) found = blogPost;
//        }
//        if (found == null) throw new NotFoundException(postId);
//
//        return found;

//        ----------------------------------- Repository ------------------------------------------
        BlogPost found = this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));

        return found;

    }


    //    3.
    public BlogPost savePost(PostRequestDTO payload) {

        BlogAuthor author = this.authorService.findById(payload.authorId());

        BlogPost newPost = new BlogPost(
                payload.blogCategory(),
                payload.title(),
                payload.readingTime(),
                payload.content(),
                author);

        BlogPost saved = this.postRepository.save(newPost);
        log.info("The post " + saved + " has been created!");
        return saved;

    }


    //    4.
    public BlogPost findByIdAndUpdate(int postId, PostRequestDTO payload) {
//        ----------------------------------- List ------------------------------------------
//        BlogPost found = null;
//
//        for (BlogPost post : this.postsDB) {
//            if (post.getPostId() == postId) {
//                found = post;
//                found.setTitle(payload.getTitle());
//                found.setBlogCategory(payload.getBlogCategory());
//                found.setContent(payload.getContent());
//                found.setReadingTime(payload.getReadingTime());
//            }
//        }
//        if (found == null) throw new NotFoundException(postId);
//
//        return found;

//        ----------------------------------- Repository ------------------------------------------
        BlogPost found = this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));

        BlogAuthor author = this.authorService.findById(payload.authorId());

        found.setTitle(payload.title());
        found.setBlogCategory(payload.blogCategory());
        found.setContent(payload.content());
        found.setReadingTime(payload.readingTime());
        found.setAuthor(author);

        return this.postRepository.save(found);

    }


    // 5.
    public void findByIdAndDelete(int postId) {
//        ----------------------------------- List ------------------------------------------
//        BlogPost found = null;
//
//        for (BlogPost post : this.postsDB) {
//            if (post.getPostId() == postId) found = post;
//        }
//
//        if (found == null) throw new NotFoundException(postId);
//
//        this.postsDB.remove(found);

//        ----------------------------------- Repository ------------------------------------------

        BlogPost found = this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));

        this.postRepository.delete(found);

    }

}