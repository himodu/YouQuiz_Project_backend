package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository { // Find YoutubeURL using ChapterID
    String findURLByChapID(Long chapId);

}
