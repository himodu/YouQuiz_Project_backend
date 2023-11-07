package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    @Override
    Optional<CommentEntity> findById(Long commentId);

    @Query(
            value = "select comment from comment where id = :commentId"
            , nativeQuery = true
    )
    String findTeacherCommentByCommentID(@Param("commentId") Long commentId);
}
