package LikeLion11th.YouQuiz_Project.myPage_student.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaClass_ChapterRepository implements Class_ChapterRepository {
    private final EntityManager em;
    public JpaClass_ChapterRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Long> findChapIdByClassId(Long classId) { // Find ChapterID using ClassID
        List<Long> data = em.createQuery("select c.chapterEntity.id from Class_ChapterEntity c where class_id = :classId", Long.class)
                .setParameter("classId", classId)
                .getResultList(); // Extracting the data which meets the criterion using SQL Query, and Saving as a list (criterion: ClassID)
        return data;
    }
}
