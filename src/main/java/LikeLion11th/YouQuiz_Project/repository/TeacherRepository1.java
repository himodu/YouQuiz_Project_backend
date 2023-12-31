package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository1 extends CrudRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByUserId(String userid);
}
