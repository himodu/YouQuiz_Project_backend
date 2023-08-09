package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository { // Check Status of Chapter Learning
    List<Long> checkStudyStatus(Long studentId, Long chapId);
    String findAnswerSentence(Long studentId, Long chapId);
    List<Integer> findAnswerList(Long studentId, Long chapId);
    Integer findScore(Long studentId, Long chapId);
}
