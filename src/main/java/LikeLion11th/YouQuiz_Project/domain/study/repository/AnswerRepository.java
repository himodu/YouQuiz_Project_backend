package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.AnswerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

    @Query(
            value = "select * from answer where chapter_id = :chapterId AND student_id = :studentId"
            , nativeQuery = true
    )
    Optional<AnswerEntity> findByChapterEntityAndStudentEntity(@Param("chapterId") Long chapid, @Param("studentId") Long studentid);

    @Query(
            value = "select id from answer a where a.student_id = :studentId AND a.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<Long> checkStudyStatus(@Param("studentId")Long studentId, @Param("chapId")Long chapId); // Check Status of Chapter Learning
    @Query(
            value = "select answer_sentence from answer a where a.student_id = :studentId AND a.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<String> findAnswerSentence(@Param("studentId")Long studentId, @Param("chapId")Long chapId); // Find Student's Answer_Sentence using StudentID & ChapterID

    @Query(
            value = "select id from answer a where a.student_id = :studentId AND a.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<Integer> findStuAnswerList(@Param("studentId")Long studentId, @Param("chapId")Long chapId); // Find Student's Answer_List using StudentID & ChapterID
    @Query(
            value = "select score from answer a where a.student_id = :studentId AND a.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<Integer> findScore(@Param("studentId") Long studentId, @Param("chapId") Long chapId); // Find Student's Score using StudentID & ChapterID
    @Query(
            value = "select a.commentEntity.id from answer a where a.student_id = :studentId AND a.chapter_id = :chapId"
            , nativeQuery = true
    )
    List<Long> CountComment(@Param("studentId") Long studentId,@Param("chapId") Long chapId); // Count the number Teacher's Comment using StudentID and ChapterID
    @Query(
            value = "select comment_entity_id from answer where chapter_id = :chapId AND student_id = :studentId"
            , nativeQuery = true
    )
    Long findTeacherCommentID(@Param("studentId") Long studentId,@Param("chapId") Long chapId); // Find Teacher's Comment using StudentID & ChapterID
}
