package valeriafarinosi.U5_W2_D4.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import valeriafarinosi.U5_W2_D4.enums.BLOG_CATEGORY;

public record PostRequestDTO(BLOG_CATEGORY blogCategory,
                             @NotBlank(message = "You must insert a title for the post, no blank space.")
                             @Size(min = 2, max = 50, message = "The title must contain between 2 and 50 letters.")
                             String title,
                             @NotBlank(message = "You must insert the content for the post, no blank space.")
                             String content,
                             @NotBlank(message = "You must insert the post reading time, no blank space.")
                             int readingTime,
                             @NotBlank(message = "You must insert the id of the author, no blank space.")
                             int authorId) {
}
