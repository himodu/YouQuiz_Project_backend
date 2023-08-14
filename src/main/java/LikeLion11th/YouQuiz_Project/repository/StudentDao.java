package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentDao extends CrudRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByUserId(String userid);
}
