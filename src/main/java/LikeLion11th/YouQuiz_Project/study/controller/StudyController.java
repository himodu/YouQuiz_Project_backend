package LikeLion11th.YouQuiz_Project.study.controller;

import LikeLion11th.YouQuiz_Project.model.AnswerDto;
import LikeLion11th.YouQuiz_Project.model.ChapterDto;
import LikeLion11th.YouQuiz_Project.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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

    @GetMapping("{chapter_id}")
    @ResponseBody
    public ChapterDto readQuizByChapterID(@PathVariable("chapter_id") int id){
        return this.studyService.readChapter(id);
    }


}
