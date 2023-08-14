package LikeLion11th.YouQuiz_Project.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_StudentRepository {
    List<Long> findClassIdByStuId(Long studentId); // Find ClassID using StudentID
    List<Long> findStuIdByClassId(Long classId); // Find StudentID using ClassID
    List<Long> CountStudent(Long classId); // Count the number of Student in Class using ClassID
=======
import LikeLion11th.YouQuiz_Project.entity.Class_StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface Class_StudentRepository extends CrudRepository<Class_StudentEntity, Long> {
>>>>>>> parent of 1bd9613 (Merge branch 'main' into Dong)
}
