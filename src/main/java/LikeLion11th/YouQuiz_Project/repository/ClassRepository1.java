package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.entity.ClassEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassRepository1 extends CrudRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByCode(String code);

    @Query(
            value = "select * from class where teacher_id =:teacherId"
            , nativeQuery = true
    )
    Optional<ClassEntity> findByTeacherEntity(@Param("teacherId") Long teacherId);

}
