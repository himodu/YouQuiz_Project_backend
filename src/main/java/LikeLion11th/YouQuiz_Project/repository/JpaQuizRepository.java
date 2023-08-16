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
    public List<String> findQuestion(Long chapId) { // Find Question Sentence using ChapterID
        List<String> data = em.createQuery("select question from QuizEntity q where q.chapterEntity.id = :chapId", String.class)
                .setParameter("chapId", chapId)
                .getResultList();
        return data;
    }

    @Override
    public List<Long> findQuizIdByChapId(Long chapId) { // Find QuizID using ChapterID
        List<Long> data = em.createQuery("select q.id from QuizEntity q where q.chapterEntity.id = :chapId", Long.class)
                .setParameter("chapId", chapId)
                .getResultList();
        return data;
    }

    @Override
    public List<String> findMultipleChoiceByQuizId(Long quizId) { // Find findMultipleChoice using QuizID
        List<String> data = em.createNativeQuery("SELECT e.example_list from example_list e where e.quiz_id = :quizId")
                .setParameter("quizId", quizId)
                .getResultList();
        return data;
    }
}
