package LikeLion11th.YouQuiz_Project.repository;

import LikeLion11th.YouQuiz_Project.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClassRepository1 extends CrudRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByCode(String code);

}
