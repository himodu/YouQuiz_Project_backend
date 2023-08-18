package LikeLion11th.YouQuiz_Project.study.controller;

import LikeLion11th.YouQuiz_Project.model.CommentDto;
import LikeLion11th.YouQuiz_Project.model.InfoDto;
import LikeLion11th.YouQuiz_Project.model.TeacherChapterDto;
import LikeLion11th.YouQuiz_Project.model.TeacherChapterListDto;
import LikeLion11th.YouQuiz_Project.study.service.TeacherStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher/{teacher_id}/study")
public class TeacherStudyController {

    private final TeacherStudyService teacherStudyService;

    public TeacherStudyController(
            @Autowired TeacherStudyService teacherStudyService
    ){
        this.teacherStudyService = teacherStudyService;
    }

    // 교육자 학습 목록
    // 전체 class 돌면서 teacher_ids 갖는 class 가져와서
    // class_chapter 들어가서 chapter_id 받아와서
    // list에 추가하고 반환

    @GetMapping("")
    public TeacherChapterListDto readTeacherChapter(
            @PathVariable("teacher_id") Long teacher_id
    ){
        return this.teacherStudyService.readTeacherChapter(teacher_id);
    }

    // 교육자 학습 get
    // 해당 chapter 학습 페이지 진입
    // chapterdto, answerdto, commentdto
    @GetMapping("{class_id}/{chapter_id}")
    public InfoDto readAllAboutChapter(
            @PathVariable("class_id") Long class_id,
            @PathVariable("chapter_id") Long chapter_id,
            @PathVariable("teacher_id") Long teacher_id
    ){
       return this.teacherStudyService.readAllAboutChapter(class_id, chapter_id,teacher_id);
    }

    // 교육자 학습 post
    @PostMapping("{answer_id}/comment")
    public void createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("teacher_id") Long teacher_id,
            @PathVariable("answer_id") Long answer_id
    ){
        teacherStudyService.createComment(commentDto, teacher_id, answer_id);
    }

}
