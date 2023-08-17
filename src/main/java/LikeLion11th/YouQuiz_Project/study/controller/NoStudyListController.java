package LikeLion11th.YouQuiz_Project.study.controller;

import LikeLion11th.YouQuiz_Project.study.service.NoStudyListService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class NoStudyListController {
    private final NoStudyListService noStudyListService;
    public NoStudyListController(NoStudyListService noStudyListService) {
        this.noStudyListService = noStudyListService;
    }

    @GetMapping(value = "/student/{student_id}/study")
    @ResponseBody
    public JSONObject findYoutubeURLByStuID(@PathVariable Long student_id) { // Find YoutubeLink using StudentID
        JSONObject result = noStudyListService.findYoutubeURLByStuID(student_id);
        return result;
    }
}
