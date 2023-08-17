package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChapterRepository {
    List<String> findURLByChapID(Long chapId); // Find YoutubeURL using ChapterID

    List<String> findYoutubeTitleByChapID(Long chapId); // Find Youtube Title using ChapterID
    List<Integer> findAnswerListByChapID(Long chapId); // Find Answer-List using ChapterID
}
