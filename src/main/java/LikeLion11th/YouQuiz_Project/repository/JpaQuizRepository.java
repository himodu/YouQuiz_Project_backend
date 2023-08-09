package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaQuizRepository implements QuizRepository {
    private final EntityManager em;

    public JpaQuizRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public String findQuestion(Long chapId) { // Find Question Sentence using ChapterID
        List<String> data = em.createQuery("select question from QuizEntity q where q.chapterEntity.id = :chapId", String.class)
                .setParameter("chapId", chapId)
                .getResultList();
        return data.get(0);
    }
}
