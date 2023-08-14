package LikeLion11th.YouQuiz_Project.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClassRepository {
    List<Long> findClassIdByTeacherId(Long teacherId); // Find ClassId using TeacherID
    List<Integer> findGradeByClassId(Long classId); // Find Grade using ClassID
    List<Integer> findClassByClassId(Long classId); // Find Class_Num using ClassID
    List<String> findSchoolNameByClassId(Long classId); // Find SchoolName using ClassID
=======
import LikeLion11th.YouQuiz_Project.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClassRepository extends CrudRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByCode(String code);

>>>>>>> parent of 1bd9613 (Merge branch 'main' into Dong)
}
