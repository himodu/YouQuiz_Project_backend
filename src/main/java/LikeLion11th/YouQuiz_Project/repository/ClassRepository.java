package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClassRepository {
    List<Long> findClassIdByTeacherId(Long teacherId); // Find ClassId using TeacherID
    List<Integer> findGradeByClassId(Long classId); // Find Grade using ClassID
    List<Integer> findClassByClassId(Long classId); // Find Class_Num using ClassID
    List<String> findSchoolNameByClassId(Long classId); // Find SchoolName using ClassID
}
