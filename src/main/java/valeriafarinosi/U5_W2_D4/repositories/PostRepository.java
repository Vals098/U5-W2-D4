package valeriafarinosi.U5_W2_D4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriafarinosi.U5_W2_D4.entities.BlogPost;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Integer> {
}
