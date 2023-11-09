package LikeLion11th.YouQuiz_Project.domain.myPage_student.repository;

import LikeLion11th.YouQuiz_Project.domain.myPage_student.entity.QuizEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends CrudRepository<QuizEntity, Long> { // Find Question Sentence using ChapterID

    @Query(
            value = "select question from quiz q where q.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<String> findQuestion(@Param("chapId") Long chapId);

}
