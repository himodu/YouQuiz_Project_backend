package LikeLion11th.YouQuiz_Project.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaStudentRepository implements StudentRepository {
    private final EntityManager em;
    public JpaStudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<String> findUserNameByStuId(Long studentId) { // Find UserName using StudentID
        List<String> data = em.createQuery("select s.username from StudentEntity s where s.id = :studentId", String.class)
                .setParameter("studentId", studentId)
                .getResultList();
        return data;
    }
}
