package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository {
    List<Long> checkStudyStatus(Long studentId, Long chapId); // Check Status of Chapter Learning
    List<String> findAnswerSentence(Long studentId, Long chapId); // Find Student's Answer_Sentence using StudentID & ChapterID
    List<Integer> findStuAnswerList(Long studentId, Long chapId); // Find Student's Answer_List using StudentID & ChapterID
    List<Integer> findScore(Long studentId, Long chapId); // Find Student's Score using StudentID & ChapterID
    List<Long> CountComment(Long studentId, Long chapId); // Count the number Teacher's Comment using StudentID and ChapterID
    Long findTeacherCommentID(Long studentId, Long chapId); // Find Teacher's Comment using StudentID & ChapterID


}
