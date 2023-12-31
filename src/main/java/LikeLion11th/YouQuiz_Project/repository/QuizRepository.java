package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository { // Find Question Sentence using ChapterID
    List<String> findQuestion(Long chapId);

    List<Long> findQuizIdByChapId(Long chapId); // Find QuizID using ChapterID
    List<String> findMultipleChoiceByQuizId(Long quizId); // // Find findMultipleChoice using QuizID
}
