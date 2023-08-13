package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaClassRepository implements ClassRepository {
    private final EntityManager em;
    public JpaClassRepository (EntityManager em) {
     this.em = em;
    }

    @Override
    public List<Long> findClassIdByTeacherId(Long teacherId) { // Find ClassId using TeacherID
        List<Long> data = em.createQuery("select c.id from ClassEntity c where c.teacherEntity.id = :teacherId", Long.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
        return data;
    }

    @Override
    public List<Integer> findGradeByClassId(Long classId) { // Find Grade using ClassID
        List<Integer> data = em.createQuery("select c.grade from ClassEntity c where c.id = :classId", Integer.class)
                .setParameter("classId", classId)
                .getResultList();
        return data;
    }

    @Override
    public List<Integer> findClassByClassId(Long classId) { // Find Class_Num using ClassID
        List<Integer> data = em.createQuery("select c.class_num from ClassEntity c where c.id = :classId", Integer.class)
                .setParameter("classId", classId)
                .getResultList();
        return data;
    }

    @Override
    public List<String> findSchoolNameByClassId(Long classId) { // Find SchoolName using ClassID
        List<String> data = em.createQuery("select c.school_name from ClassEntity c where c.id = :classId", String.class)
                .setParameter("classId", classId)
                .getResultList();
        return data;
    }
}
