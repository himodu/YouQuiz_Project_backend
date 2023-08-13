package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository { // Find UserName using StudentID
    List<String> findUserNameByStuId(Long studentId);
}
