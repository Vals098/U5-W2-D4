package valeriafarinosi.U5_W2_D4.payloads;

import valeriafarinosi.U5_W2_D4.enums.BLOG_CATEGORY;

public record PostRequestDTO(BLOG_CATEGORY blogCategory,
                             String title,
                             String content,
                             int readingTime,
                             int authorId) {
}
