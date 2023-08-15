package LikeLion11th.YouQuiz_Project.study.controller;

import LikeLion11th.YouQuiz_Project.model.CommentDto;
import LikeLion11th.YouQuiz_Project.study.service.TeacherStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher/{teacher_id}/study")
public class TeacherStudyController {

    private final TeacherStudyService teacherStudyService;

    public TeacherStudyController(
            @Autowired TeacherStudyService teacherStudyService
    ){
        this.teacherStudyService = teacherStudyService;
    }

//    // 해당 chapter 학습 페이지 진입
//    @GetMapping("{chapter_id}")
//    public void view_page(
//
//    ){
//
//    }

    @PostMapping("{answer_id}/comment")
    public void write_comment(
            @RequestBody CommentDto commentDto,
            @PathVariable("teacher_id") Long teacher_id,
            @PathVariable("answer_id") Long answer_id
    ){
        teacherStudyService.createComment(commentDto, teacher_id, answer_id);
    }

}
