package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository1 extends CrudRepository<AnswerEntity, Long> {

    @Query(
            value = "select * from answer where chapter_id = :chapterId AND student_id = :studentId"
            , nativeQuery = true
    )
    Optional<AnswerEntity> findByChapterEntityAndStudentEntity(@Param("chapterId") Long chapid, @Param("studentId") Long studentid);

    @Query(
            value = "select * from answer a where chapter_id = :chapterId AND student_id = :studentId"
            , nativeQuery = true
    )
    public Optional<AnswerEntity> findByChapterEntityAndStudentEntity1(Long chapterId, Long studentId);

}
