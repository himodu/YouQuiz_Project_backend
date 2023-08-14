package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.Class_StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_StudentRepository extends CrudRepository<Class_StudentEntity, Long> {
    List<Long> findClassIdByStuId(Long studentId); // Find ClassID using StudentID
    List<Long> findStuIdByClassId(Long classId); // Find StudentID using ClassID
    List<Long> CountStudent(Long classId); // Count the number of Student in Class using ClassID
}
