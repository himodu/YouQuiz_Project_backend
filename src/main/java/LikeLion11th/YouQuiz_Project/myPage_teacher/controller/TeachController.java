package LikeLion11th.YouQuiz_Project.myPage_teacher.controller;

import LikeLion11th.YouQuiz_Project.myPage_teacher.service.Teacher_EvaluationStatusService;
import LikeLion11th.YouQuiz_Project.myPage_teacher.service.Teacher_StudyStatusService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeachController {
    private final Teacher_StudyStatusService teacherStudyStatusService;
    private final Teacher_EvaluationStatusService teacherEvaluationStatusService;

    private TeachController(Teacher_StudyStatusService teacherStudyStatusService, Teacher_EvaluationStatusService teacherEvaluationStatusService) {
        this.teacherStudyStatusService = teacherStudyStatusService;
        this.teacherEvaluationStatusService = teacherEvaluationStatusService;
    }

    @GetMapping(value = "/teacher/{teacher_id}/studystatus")
    @ResponseBody
    public JSONObject findStudentStatusByTeacherID(@PathVariable Long teacher_id) { // Find Student Status in Class using TeacherID
        JSONObject result = teacherStudyStatusService.SearchStudyStatus(teacher_id);
        return result;
    }

    @GetMapping(value = "/teacher/{teacher_id}/evaluationstatus")
    @ResponseBody
    public JSONObject findEvaluationStatusByTeacherID(@PathVariable Long teacher_id) { // Find Evaluation Status of Student in Class using TeacherID
        JSONObject result = teacherEvaluationStatusService.SearchEvaluationStatus(teacher_id);
        return result;
    }
}
