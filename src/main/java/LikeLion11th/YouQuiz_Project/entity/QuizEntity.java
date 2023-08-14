package LikeLion11th.YouQuiz_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String writer;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ElementCollection
    @CollectionTable(name="EXAMPLE_LIST", joinColumns =  @JoinColumn(name="quiz_id", referencedColumnName = "id"))
    private List<String> exampleList= new ArrayList<>();

    @JsonIgnore
    @ManyToOne(
            targetEntity = ChapterEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "CHAPTER_ID")
    private ChapterEntity chapterEntity;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<String> exampleList) {
        this.exampleList = exampleList;
    }

    public ChapterEntity getChapterEntity() {
        return chapterEntity;
    }

    public void setChapterEntity(ChapterEntity chapterEntity) {
        this.chapterEntity = chapterEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", exampleList=" + exampleList +
                ", chapterEntity=" + chapterEntity +
                '}';
    }

}
