package LikeLion11th.YouQuiz_Project.domain.myPage_student.controller;

import LikeLion11th.YouQuiz_Project.domain.myPage_student.service.Student_StudyStatusService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*")
@Controller
public class StudyStatusController {
    private final Student_StudyStatusService studyStatusService;
    public StudyStatusController(Student_StudyStatusService studyListService) {
        this.studyStatusService = studyListService;
    }

    @GetMapping(value = "/student/{student_id}/studystatus")
    @ResponseBody
    public JSONObject findStudiedChapByStuID(@PathVariable Long student_id) { // Find YoutubeLink using StudentID
        JSONObject result = studyStatusService.findStudiedChapByStuID(student_id);
        return result;
    }

    @GetMapping(value = "/student/{student_id}/studystatus/{chapter_id}")
    @ResponseBody
    public JSONObject findStudyStatus(
            @PathVariable("student_id") long studentId,
            @PathVariable("chapter_id") long chapterId
            ) { // Find Learning Status of Each Chapter

        JSONObject result = studyStatusService.findStudyStatus(studentId, chapterId);
        return result;
    }}