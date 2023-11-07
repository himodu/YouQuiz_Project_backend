package LikeLion11th.YouQuiz_Project.domain.study.entity;

import LikeLion11th.YouQuiz_Project.global.Login.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answer")
public class AnswerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name="ANSWER_LIST",
            joinColumns =  @JoinColumn(name="answer_id", referencedColumnName = "id")
    )
    private List<Integer> answersList = new ArrayList<>();

    private String answer_sentence;

    private int score;

    @JsonIgnore
    @ManyToOne(
            targetEntity = ChapterEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "CHAPTER_ID")
    private ChapterEntity chapterEntity;

    @JsonIgnore
    @ManyToOne(
            targetEntity = StudentEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity studentEntity;

    @JsonIgnore
    @OneToOne
    private CommentEntity commentEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Integer> answersList) {
        this.answersList = answersList;
    }

    public String getAnswer_sentence() {
        return answer_sentence;
    }

    public void setAnswer_sentence(String answer_sentence) {
        this.answer_sentence = answer_sentence;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ChapterEntity getChapterEntity() {
        return chapterEntity;
    }

    public void setChapterEntity(ChapterEntity chapterEntity) {
        this.chapterEntity = chapterEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", answersList=" + answersList +
                ", answer_sentence='" + answer_sentence + '\'' +
                ", score=" + score +
                ", chapterEntity=" + chapterEntity +
                ", studentEntity=" + studentEntity +
                '}';
    }
}
