package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> { // Find UserName using StudentID
    Optional<StudentEntity> findByUserId(String userid);
    List<String> findUserNameByStuId(Long studentId);
}