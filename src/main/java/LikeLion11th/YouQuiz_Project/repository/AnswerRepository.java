package LikeLion11th.YouQuiz_Project.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository {
    List<Long> checkStudyStatus(Long studentId, Long chapId); // Check Status of Chapter Learning
    List<String> findAnswerSentence(Long studentId, Long chapId); // Find Student's Answer_Sentence using StudentID & ChapterID
    List<Integer> findAnswerList(Long studentId, Long chapId); // Find Student's Answer_List using StudentID & ChapterID
    List<Integer> findScore(Long studentId, Long chapId); // Find Student's Score using StudentID & ChapterID
    List<Long> CountComment(Long studentId, Long chapId); // Count the number Teacher's Comment using StudentID and ChapterID
=======
import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.data.repository.CrudRepository;


public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

>>>>>>> parent of 1bd9613 (Merge branch 'main' into Dong)
}
