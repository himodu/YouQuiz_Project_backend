package LikeLion11th.YouQuiz_Project.Register.controller;

import LikeLion11th.YouQuiz_Project.Register.service.RegisterService;
import LikeLion11th.YouQuiz_Project.model.ClassDto;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("register")
public class RegisterController {

    private final RegisterService registerService;


    public RegisterController(
            @Autowired RegisterService registerService) {
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
