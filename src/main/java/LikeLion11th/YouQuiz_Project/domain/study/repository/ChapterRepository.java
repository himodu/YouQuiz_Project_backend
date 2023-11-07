package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.ChapterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepository extends CrudRepository<ChapterEntity, Long> {

    @Query(
            value = "select c.youtube_link from ChapterEntity c where c.id = :chapId"
            , nativeQuery = true
    )
    List<String> findURLByChapID(@Param("chapId") Long chapId); // Find YoutubeURL using ChapterID
    @Query(
            value = "select c.title from ChapterEntity c where c.id = :chapId"
            , nativeQuery = true
    )
    List<String> findYoutubeTitleByChapID(@Param("chapId") Long chapId); // Find Youtube Title using ChapterID
}
