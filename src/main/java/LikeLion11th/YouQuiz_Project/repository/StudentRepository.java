package LikeLion11th.YouQuiz_Project.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository { // Find UserName using StudentID
    List<String> findUserNameByStuId(Long studentId);
=======
import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByUserId(String userid);
>>>>>>> parent of 1bd9613 (Merge branch 'main' into Dong)
}
