package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.Class_StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Class_StudentRepository extends CrudRepository<Class_StudentEntity, Long> {

    @Query(
            value = "select c.classEntity.id from Class_StudentEntity c where student_id = :studentId"
            , nativeQuery = true
    )
    List<Long> findClassIdByStuId(@Param("studentId") Long studentId); // Find ClassID using StudentID
    @Query(
            value = "select c.studentEntity.id from Class_StudentEntity c where c.classEntity.id = :classId"
            , nativeQuery = true
    )
    List<Long> findStuIdByClassId(@Param("classId")Long classId); // Find StudentID using ClassID
    @Query(
            value = "select count(c.studentEntity.id) from Class_StudentEntity c where c.classEntity.id = :classId"
            , nativeQuery = true
    )
    List<Long> CountStudent(@Param("classId")Long classId); // Count the number of Student in Class using ClassID
}
