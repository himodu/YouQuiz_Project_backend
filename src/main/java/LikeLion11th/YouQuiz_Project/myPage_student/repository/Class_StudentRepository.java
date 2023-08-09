package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_StudentRepository { // Find ClassID using StudentID
    List<Long> findClassIdByStuId(Long studentId);
}
