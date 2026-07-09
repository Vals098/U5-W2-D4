package valeriafarinosi.U5_W2_D4.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import valeriafarinosi.U5_W2_D4.enums.BLOG_CATEGORY;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogPost {

    @Id
    @GeneratedValue
    private int postId;
    @Column(name = "blog_category", nullable = false)
    private BLOG_CATEGORY blogCategory;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String cover;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private BlogAuthor author;

    public BlogPost(BLOG_CATEGORY blogCategory, String content, int readingTime, String title, BlogAuthor author) {
        this.title = title;
        this.blogCategory = blogCategory;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
        this.cover = "https://fastly.picsum.photos/id/1066/200/300.jpg?hmac=s9I5yyFYG2Di_yr3kg5mcCmdsZkNeQJVsYVhTpnSnnw";
    }


}
