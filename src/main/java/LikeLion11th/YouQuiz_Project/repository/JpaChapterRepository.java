package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaChapterRepository implements ChapterRepository {
    private final EntityManager em;
    public JpaChapterRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<String> findURLByChapID(Long chapId) { // Find YoutubeURL using ChapterID
        List<String> data = em.createQuery("select c.youtube_link from ChapterEntity c where c.id = :chapId", String.class)
                .setParameter("chapId", chapId)
                .getResultList(); // Extracting the data which meets the criterion using SQL Query, and Saving as a list (criterion: ChapterID)
        return data;
    }

    @Override
    public List<Integer> findAnswerListByChapID(Long chapId) { // Find Answer-List using ChapterID
        List<Integer> data = em.createNativeQuery("select c.correct_answer_list from correct_answer_list c where c.chapter_id = :chapId")
                .setParameter("chapId", chapId)
                .getResultList();
        return data;
    }
}
