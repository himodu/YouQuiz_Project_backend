package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository1 extends CrudRepository<AnswerEntity, Long> {

    @Query(
            value = "select * from answer a where chapter_id = :chapterId AND student_id = :studentId"
            , nativeQuery = true
    )
    AnswerEntity findByChapterEntityAndStudentEntity(@Param("chapterId") Long chapid, @Param("studentId") Long studentid);

}
