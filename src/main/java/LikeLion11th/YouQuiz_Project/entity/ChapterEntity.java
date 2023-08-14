package LikeLion11th.YouQuiz_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name="CORRECT_ANSWER_LIST", joinColumns =  @JoinColumn(name="chapter_id", referencedColumnName = "id"))
    private List<Integer> correct_answerList = new ArrayList<>();

    private String youtube_link;

    @JsonIgnore
    @OneToMany(
            targetEntity = Class_ChapterEntity.class,
            mappedBy = "chapterEntity",
            fetch = FetchType.LAZY
    )
    private List<Class_ChapterEntity> class_ChapterEntityList = new ArrayList<>();

    @OneToMany(
            targetEntity = QuizEntity.class,
            mappedBy = "chapterEntity",
            fetch = FetchType.LAZY
    )
    private List<QuizEntity> quizEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
