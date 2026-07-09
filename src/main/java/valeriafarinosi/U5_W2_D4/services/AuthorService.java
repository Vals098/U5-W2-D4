package valeriafarinosi.U5_W2_D4.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import valeriafarinosi.U5_W2_D4.entities.BlogAuthor;
import valeriafarinosi.U5_W2_D4.exceptions.BadRequestException;
import valeriafarinosi.U5_W2_D4.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D4.payloads.AuthorRequestDTO;
import valeriafarinosi.U5_W2_D4.repositories.AuthorRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthorService {


    //    AuthorRepository's DI
    private final AuthorRepository authorRepository;
    //    Cloudinary's DI
    private final Cloudinary fileUploader;

    public AuthorService(AuthorRepository authorRepository, Cloudinary fileUploader) {
        this.authorRepository = authorRepository;
        this.fileUploader = fileUploader;
    }


    //    1.
    public List<BlogAuthor> getAllAuthors() {
        return this.authorRepository.findAll();
    }


    //    2.
    public BlogAuthor findById(int authorId) {

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

        BlogAuthor found = this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setBirthDate(payload.birthDate());

        return this.authorRepository.save(found);
    }


    // 5.
    public void findByIdAndDelete(int authorId) {

        BlogAuthor found = this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

        this.authorRepository.delete(found);

    }

    // 6.   update avatar profile pic
    public void updateAvatar(int authorId, MultipartFile file) {
//        1. eventuali controlli tipo file/formato
//    2. findByIdAuthor
        BlogAuthor author = findById(authorId);
// 3. upload del file du Cloudinary
        try {
            Map result = fileUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            System.out.println(url);
//        4. se tutto va bene, Cloudinary restituirà l'url dell'img
            author.setAvatarURL(url);

            authorRepository.save(author);

        } catch (IOException e) {
            throw new BadRequestException("Error while loading the image.");
        }
    }

}
