package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.ChapterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChapterRepository extends CrudRepository<ChapterEntity, Long> {
    List<String> findURLByChapID(Long chapId); // Find YoutubeURL using ChapterID
}
