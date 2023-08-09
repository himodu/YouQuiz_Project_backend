package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository { // Find Question Sentence using ChapterID
    String findQuestion(Long chapId);
}
