package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository { // Find YoutubeURL using ChapterID
    String findURLByChapID(Long chapId);

}
