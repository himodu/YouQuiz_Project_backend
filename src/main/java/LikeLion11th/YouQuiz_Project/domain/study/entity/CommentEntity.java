package LikeLion11th.YouQuiz_Project.domain.study.entity;

import LikeLion11th.YouQuiz_Project.global.Login.entity.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @JsonIgnore
    @OneToOne(
            targetEntity = AnswerEntity.class,
            mappedBy = "commentEntity",
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ANSWER_ID")
    private AnswerEntity answerEntity;

    @JsonIgnore
    @ManyToOne(
            targetEntity = TeacherEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "TEACHER_ID")
    private TeacherEntity teacherEntity;

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AnswerEntity getAnswerEntity() {
        return answerEntity;
    }

    public void setAnswerEntity(AnswerEntity answerEntity) {
        this.answerEntity = answerEntity;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", answerEntity=" + answerEntity +
                '}';
    }
}
