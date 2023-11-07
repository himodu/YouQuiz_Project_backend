package LikeLion11th.YouQuiz_Project.global.Register.controller;


import LikeLion11th.YouQuiz_Project.global.Login.dto.TeacherDto;
import LikeLion11th.YouQuiz_Project.global.Register.service.RegisterService;
import LikeLion11th.YouQuiz_Project.global.Register.dto.ClassDto;
import LikeLion11th.YouQuiz_Project.global.Login.dto.StudentDto;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register")
public class RegisterController {

    private final RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("student")
    public void register_student(
            @RequestBody StudentDto studentDto
    ){
        registerService.createStudent(studentDto);
    }

    @PostMapping("teacher")
    public void register_student(
            @RequestBody TeacherDto teacherDto
    ){
        registerService.createTeacher(teacherDto);
    }

    @GetMapping("schoolAuth")
    public ClassDto AuthSchool(
            @RequestBody ClassDto classDto
    ){
        ClassDto NewClassDto = registerService.AuthSchool(classDto);
        return NewClassDto ;
    }

}
