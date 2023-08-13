package LikeLion11th.YouQuiz_Project.study.controller;

import LikeLion11th.YouQuiz_Project.model.AnswerDto;
import LikeLion11th.YouQuiz_Project.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("student/{student_id}/study")
public class StudyController {
    private final StudyService studyService;

    public StudyController(@Autowired StudyService answerService) {
        this.studyService = answerService;
    }

    @PostMapping("{chap_id}")
    public void createAnswer(
            @RequestBody AnswerDto answerDto,
            @PathVariable("student_id") int student_id,
            @PathVariable("chap_id") int chapter_Id
            ){
        this.studyService.createAnswer(answerDto, student_id, chapter_Id);
    }
}
