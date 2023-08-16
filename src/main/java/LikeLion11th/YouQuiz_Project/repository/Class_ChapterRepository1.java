package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.Class_ChapterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Class_ChapterRepository1 extends CrudRepository<Class_ChapterEntity, Long> {
    @Override
    Optional<Class_ChapterEntity> findById(Long aLong);
}
