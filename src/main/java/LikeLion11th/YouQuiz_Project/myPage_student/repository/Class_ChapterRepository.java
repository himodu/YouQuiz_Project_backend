package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_ChapterRepository { // Find ChapterID using ClassID
    List<Long> findChapIdByClassId(Long classId);
}
