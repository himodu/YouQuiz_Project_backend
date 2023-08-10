package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Class_ChapterRepository {
    List<Long> findChapIdByClassId(Long classId); // Find ChapterID using ClassID
    List<Long> CountChapter(Long classId); // Count the number of Chapter assigned Class using ClassID
}
