package LikeLion11th.YouQuiz_Project.Login.controller;

import LikeLion11th.YouQuiz_Project.Login.service.LoginService;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(@Autowired LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("student")
    public StudentDto Stdent_Login(
            @RequestBody StudentDto studentDto
    ){

        StudentDto new_studentDto = this.loginService.Student_login(studentDto);

        return new_studentDto;
    }

    @GetMapping("teacher")
    public TeacherDto teacher_Login(
            @RequestBody TeacherDto teacherDto
    ){

        TeacherDto new_teacherDto = this.loginService.Teacher_login(teacherDto);

        return new_teacherDto;
    }
}
