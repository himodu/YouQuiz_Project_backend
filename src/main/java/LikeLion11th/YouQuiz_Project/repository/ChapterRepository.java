package LikeLion11th.YouQuiz_Project.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChapterRepository {
    List<String> findURLByChapID(Long chapId); // Find YoutubeURL using ChapterID
=======
import LikeLion11th.YouQuiz_Project.entity.ChapterEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<ChapterEntity, Long> {
>>>>>>> parent of 1bd9613 (Merge branch 'main' into Dong)
}
