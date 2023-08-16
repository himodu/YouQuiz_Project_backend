package LikeLion11th.YouQuiz_Project.model;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;

public class CommentDto {
    private Long id;
    private String comment;
    private AnswerEntity answerEntity;
    private TeacherEntity teacherEntity;

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

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", answerEntity=" + answerEntity +
                ", teacherEntity=" + teacherEntity +
                '}';
    }
}
