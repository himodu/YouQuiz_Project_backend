package LikeLion11th.YouQuiz_Project.Login.controller;

import LikeLion11th.YouQuiz_Project.Login.service.LoginService;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(@Autowired LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("student")
    public StudentDto Stdent_Login(
            @RequestBody StudentDto studentDto
    ){

        StudentDto new_studentDto = this.loginService.Student_login(studentDto);

        return new_studentDto;
    }

    @PostMapping("teacher")
    public TeacherDto teacher_Login(
            @RequestBody TeacherDto teacherDto
    ){

        TeacherDto new_teacherDto = this.loginService.Teacher_login(teacherDto);

        return new_teacherDto;
    }
}
