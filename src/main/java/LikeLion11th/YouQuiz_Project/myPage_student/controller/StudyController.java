package LikeLion11th.YouQuiz_Project.myPage_student.controller;

import LikeLion11th.YouQuiz_Project.myPage_student.service.StudyStatusService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StudyController {
    private final StudyStatusService studyStatusService;

    public StudyController(StudyStatusService studyListService) {
        this.studyStatusService = studyListService;
    }

    @GetMapping(value = "/student/{student_id}")
    @ResponseBody
    public JSONObject findYoutubeURLByStuID(@PathVariable Long student_id) { // Find YoutubeLink using StudentID
        JSONObject result = studyStatusService.findYoutubeURLByStuID(student_id);
        return result;
    }

    @GetMapping(value = "/student/{student_id}/{chapter_id}")
    @ResponseBody
    public JSONObject findStudyStatus(@PathVariable Map<String, String> pathVarsMap) { // Find Learning Status of Each Chapter
        Long student_id = Long.parseLong(pathVarsMap.get("student_id"));
        Long chapter_id = Long.parseLong(pathVarsMap.get("chapter_id"));
        JSONObject result = studyStatusService.findStudyStatus(student_id, chapter_id);
        return result;
    }
}
