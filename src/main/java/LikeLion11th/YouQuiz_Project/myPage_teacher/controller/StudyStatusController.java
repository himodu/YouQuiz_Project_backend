package LikeLion11th.YouQuiz_Project.myPage_teacher.controller;

import LikeLion11th.YouQuiz_Project.myPage_teacher.service.Teacher_StudyStatusService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudyStatusController {
    private final Teacher_StudyStatusService studyStatusService;

    private StudyStatusController(Teacher_StudyStatusService studyStatusService) {
        this.studyStatusService = studyStatusService;
    }

    @GetMapping(value = "/teacher/{teacher_id}/studystatus")
    @ResponseBody
    public JSONObject findYoutubeURLByStuID(@PathVariable Long teacher_id) { // Find YoutubeLink using StudentID
        JSONObject result = studyStatusService.SearchStudyStatus(teacher_id);
        return result;
    }
}
