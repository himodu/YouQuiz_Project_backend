package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaCommentRepository implements CommentRepository {
    private final EntityManager em;
    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<String> findTeacherCommentByCommentID(Long commentId) {
        List<String> data = em.createNativeQuery("select comment from comment where id = :commentId")
                .setParameter("commentId", commentId)
                .getResultList();
        return data;
    }
}
