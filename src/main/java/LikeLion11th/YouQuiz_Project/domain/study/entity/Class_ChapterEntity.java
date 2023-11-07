package LikeLion11th.YouQuiz_Project.domain.study.entity;

import LikeLion11th.YouQuiz_Project.global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "class_chapter")
public class Class_ChapterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(
            targetEntity = ClassEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "CLASS_ID")
    private ClassEntity classEntity;

    @JsonIgnore
    @ManyToOne(
            targetEntity = ChapterEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "CHAPTER_ID")
    private ChapterEntity chapterEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public ChapterEntity getChapterEntity() {
        return chapterEntity;
    }

    public void setChapterEntity(ChapterEntity chapterEntity) {
        this.chapterEntity = chapterEntity;
    }

    @Override
    public String toString() {
        return "Class_ChapterEntity{" +
                "id=" + id +
                ", classEntity=" + classEntity +
                ", chapterEntity=" + chapterEntity +
                '}';
    }
}
