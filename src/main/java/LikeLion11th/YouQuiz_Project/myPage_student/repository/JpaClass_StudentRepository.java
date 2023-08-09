package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaClass_StudentRepository implements Class_StudentRepository {
    private final EntityManager em;
    public JpaClass_StudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Long> findClassIdByStuId(Long studentId) { // Find ClassID using StudentID
        List<Long> data = em.createQuery("select c.classEntity.id from Class_StudentEntity c where student_id = :studentId", Long.class)
                .setParameter("studentId", studentId)
                .getResultList(); // Extracting the data which meets the criterion using SQL Query, and Saving as a list (criterion: StudentID)
        return data;
    }
}
