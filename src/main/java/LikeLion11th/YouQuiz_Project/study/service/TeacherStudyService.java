package LikeLion11th.YouQuiz_Project.study.service;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.entity.CommentEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.model.CommentDto;
import LikeLion11th.YouQuiz_Project.repository.AnswerRepository1;
import LikeLion11th.YouQuiz_Project.repository.CommentRepository1;
import LikeLion11th.YouQuiz_Project.repository.TeacherRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TeacherStudyService {
    private final TeacherRepository1 teacherRepository1;
    private final CommentRepository1 commentRepository1;
    private final AnswerRepository1 answerRepository1;

    public TeacherStudyService(
            @Autowired TeacherRepository1 teacherRepository1,
            @Autowired CommentRepository1 commentRepository1,
            @Autowired AnswerRepository1 answerRepository1
    ){
        this.teacherRepository1 = teacherRepository1;
        this.commentRepository1 = commentRepository1;
        this.answerRepository1 = answerRepository1;
    }

    public void createComment(CommentDto commentDto, Long teacher_id, Long answer_id){
        CommentEntity commentEntity = new CommentEntity();

        Optional<TeacherEntity> teacherEntity = teacherRepository1.findById(Long.valueOf(teacher_id));
        if(teacherEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<AnswerEntity> answerEntity = answerRepository1.findById(Long.valueOf(answer_id));
        if(answerEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(commentRepository1.findById(commentDto.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }

        commentEntity.setId(commentDto.getId());
        commentEntity.setComment(commentDto.getComment());
        commentEntity.setAnswerEntity(answerEntity.get());
        commentEntity.setTeacherEntity(teacherEntity.get());

        this.commentRepository1.save(commentEntity);
    }
}
