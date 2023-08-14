package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
    List<Long> checkStudyStatus(Long studentId, Long chapId); // Check Status of Chapter Learning
    List<String> findAnswerSentence(Long studentId, Long chapId); // Find Student's Answer_Sentence using StudentID & ChapterID
    List<Integer> findAnswerList(Long studentId, Long chapId); // Find Student's Answer_List using StudentID & ChapterID
    List<Integer> findScore(Long studentId, Long chapId); // Find Student's Score using StudentID & ChapterID
    List<Long> CountComment(Long studentId, Long chapId); // Count the number Teacher's Comment using StudentID and ChapterID
}
