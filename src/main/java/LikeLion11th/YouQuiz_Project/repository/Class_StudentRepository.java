package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_StudentRepository {
    List<Long> findClassIdByStuId(Long studentId); // Find ClassID using StudentID
    List<Long> findStuIdByClassId(Long classId); // Find StudentID using ClassID
    List<Long> CountStudent(Long classId); // Count the number of Student in Class using ClassID
}
