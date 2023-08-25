package LikeLion11th.YouQuiz_Project.repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface CommentRepository {
    List<String> findTeacherCommentByCommentID(Long commentId);
}
