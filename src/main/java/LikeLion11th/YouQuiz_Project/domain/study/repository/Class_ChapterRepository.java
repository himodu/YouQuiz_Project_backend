package LikeLion11th.YouQuiz_Project.domain.study.repository;

import LikeLion11th.YouQuiz_Project.domain.study.entity.Class_ChapterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Class_ChapterRepository extends CrudRepository<Class_ChapterEntity, Long> {
    @Override
    Optional<Class_ChapterEntity> findById(Long aLong);
    @Query(
            value = "select c.chapterEntity.id from Class_ChapterEntity c where class_id = :classId"
            , nativeQuery = true
    )
    List<Long> findChapIdByClassId(@Param("classId") Long classId); // Find ChapterID using ClassID

    @Query(
            value = "select count(c.chapterEntity.id) from Class_ChapterEntity c where c.classEntity.id = :classId"
            , nativeQuery = true
    )
    List<Long> CountChapter(@Param("classId") Long classId); // Count the number of Chapter assigned Class using ClassID
}
