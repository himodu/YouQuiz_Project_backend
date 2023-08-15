package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository1 extends CrudRepository<CommentEntity, Long> {
    @Override
    Optional<CommentEntity> findById(Long commentId);
}
