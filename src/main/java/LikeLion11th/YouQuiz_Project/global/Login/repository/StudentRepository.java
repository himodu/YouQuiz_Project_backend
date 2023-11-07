package LikeLion11th.YouQuiz_Project.global.Login.repository;

import LikeLion11th.YouQuiz_Project.global.Login.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByUserId(String userid);
    @Query(
            value = "select s.username from StudentEntity s where s.id = :studentId"
            , nativeQuery = true
    )
    List<String> findUserNameByStuId(Long studentId);
}
