package LikeLion11th.YouQuiz_Project.myPage_student.repository;

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
    public String findURLByChapID(Long chapId) { // Find YoutubeURL using ChapterID
        List<String> data = em.createQuery("select c.youtube_link from ChapterEntity c where c.id = :chapId", String.class)
                .setParameter("chapId", chapId)
                .getResultList(); // Extracting the data which meets the criterion using SQL Query, and Saving as a list (criterion: ChapterID)
        return data.get(0);
    }
}
