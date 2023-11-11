package LikeLion11th.YouQuiz_Project.domain.study.dto;

public class CommentListDto {
    private String comment;
    private Long studentId;

    public CommentListDto(String comment, Long studentId) {
        this.comment = comment;
        this.studentId = studentId;
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

}
