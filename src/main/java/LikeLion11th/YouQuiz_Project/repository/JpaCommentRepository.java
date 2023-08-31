package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
public class JpaCommentRepository implements CommentRepository {
    private final EntityManager em;
    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public String findTeacherCommentByCommentID(Long commentId) {
        Object data = em.createNativeQuery("select comment from comment where id = :commentId")
                .setParameter("commentId", commentId)
                .getSingleResult();
        return String.valueOf(data);
    }
}
