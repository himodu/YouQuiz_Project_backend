package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaAnswerRepository implements AnswerRepository {
    private final EntityManager em;
    public JpaAnswerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Long> checkStudyStatus(Long studentId, Long chapId) { // Check Status of Chapter Learning
        List<Long> data = em.createQuery("select id from AnswerEntity a where a.studentEntity.id = :studentId AND a.chapterEntity.id = :chapId", Long.class)
                .setParameter("studentId", studentId)
                .setParameter("chapId", chapId)
                .getResultList(); // After Compare studentID & chapterID of saved data, Only if matched, Add answer's ID to Return Data
        return data;
    }

    @Override
    public List<Long> findTeacherCommentID(Long studentId, Long chapId) { // Find Teacher's Comment ID using StudentID & ChapterID
        List<Long> data = em.createNativeQuery("select comment_entity_id from answer where chapter_id = :chapId AND student_id = :studentId")
                .setParameter("chapId", chapId)
                .setParameter("studentId", studentId)
                .getResultList();
        return data;
    }

    @Override
    public List<String> findAnswerSentence(Long studentId, Long chapId) { // Find Student's Answer_Sentence Using StudentID & ChapterID
        List<String> data = em.createQuery("select answer_sentence from AnswerEntity a where a.studentEntity.id = :studentId AND a.chapterEntity.id = :chapId", String.class)
                .setParameter("studentId", studentId)
                .setParameter("chapId", chapId)
                .getResultList(); // After Compare studentID & chapterID of saved data, Only if matched, Add Answer_Sentence to Return Data
        return data;
    }

    @Override
    public List<Integer> findStuAnswerList(Long studentId, Long chapId) { // Find Student's Answer_List Using StudentID & ChapterID
        List<Long> IdData = em.createQuery("select id from AnswerEntity a where a.studentEntity.id = :studentId AND a.chapterEntity.id = :chapId", Long.class)
                .setParameter("studentId", studentId)
                .setParameter("chapId", chapId)
                .getResultList(); // After Compare studentID & chapterID of saved data, Only if matched, Add AnswerID to Return Data
        List<Integer> data = em.createNativeQuery("SELECT answers_list FROM ANSWER_LIST WHERE answer_id = :answerId")
                .setParameter("answerId", IdData.get(0))
                .getResultList(); // After Compare studentID & chapterID of saved data, Only if matched, Add Answer_List to Return Data
        return data;
    }

    @Override
    public List<Integer> findScore(Long studentId, Long chapId) { // Find Student's Score Using StudentID & ChapterID
        List<Integer> data = em.createQuery("select score from AnswerEntity a where a.studentEntity.id = :studentId AND a.chapterEntity.id = :chapId", Integer.class)
                .setParameter("studentId", studentId)
                .setParameter("chapId", chapId)
                .getResultList(); // After Compare studentID & chapterID of saved data, Only if matched, Add Score to Return Data
        return data;
    }

    @Override
    public List<Long> CountComment(Long studentId, Long chapId) { // Count the number Teacher's Comment using StudentID and ChapterID
        List<Long> data = em.createQuery("select a.commentEntity.id from AnswerEntity a where a.studentEntity.id = :studentId AND a.chapterEntity.id = :chapId", Long.class)
                .setParameter("studentId", studentId)
                .setParameter("chapId", chapId)
                .getResultList();
        return data;
    }
}
