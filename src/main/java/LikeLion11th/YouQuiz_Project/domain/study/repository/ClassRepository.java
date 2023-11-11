package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.ClassEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends CrudRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByCode(String code);

    @Query(
            value = "select * from class where teacher_id =:teacherId"
            , nativeQuery = true
    )
    Optional<ClassEntity> findByTeacherEntity(@Param("teacherId") Long teacherId);


    @Query(
            value = "select c.id from class c where c.teacher_id = :teacherId"
            , nativeQuery = true
    )
    List<Long> findClassIdByTeacherId(@Param("teacherId") Long teacherId); // Find ClassId using TeacherID
    @Query(
            value = "select c.grade from class c where c.id = :classId"
            , nativeQuery = true
    )
    List<Integer> findGradeByClassId(@Param("classId") Long classId); // Find Grade using ClassID
    @Query(
            value = "select c.class_num from class c where c.id = :classId"
            , nativeQuery = true
    )
    List<Integer> findClassByClassId(@Param("classId")Long classId); // Find Class_Num using ClassID
    @Query(
            value = "select c.school_name from class c where c.id = :classId"
            , nativeQuery = true
    )
    List<String> findSchoolNameByClassId(@Param("classId") Long classId); // Find SchoolName using ClassID

}
