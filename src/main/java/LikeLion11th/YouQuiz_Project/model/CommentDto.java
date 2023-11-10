package LikeLion11th.YouQuiz_Project.model;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;

public class CommentDto {
    private Long id;
    private String comment;
    private Long studentId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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
