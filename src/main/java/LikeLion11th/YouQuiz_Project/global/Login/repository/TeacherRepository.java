package LikeLion11th.YouQuiz_Project.global.Login.repository;

import LikeLion11th.YouQuiz_Project.global.Login.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByUserId(String userid);
}
