package LikeLion11th.YouQuiz_Project.Login.service;

import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import LikeLion11th.YouQuiz_Project.repository.StudentRepository;
import LikeLion11th.YouQuiz_Project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public LoginService(@Autowired StudentRepository studentRepository, @Autowired TeacherRepository teacherRepository) {

        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public StudentDto Student_login(StudentDto studentDto){


        Optional<StudentEntity> targetStudent = studentRepository.findByUserId(studentDto.getUserId());
        if(targetStudent.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        if(targetStudent.get().getPassword().equals(studentDto.getPassword())){
            StudentDto returnStudent = new StudentDto();
            returnStudent.setId(targetStudent.get().getId().intValue());
            returnStudent.setUsername(targetStudent.get().getUsername());

            return returnStudent;
        }
        return null;
    }

    public TeacherDto Teacher_login(TeacherDto teacherDto){

        Optional<TeacherEntity> targetTeacher = this.teacherRepository.findByUserId(teacherDto.getUserId());
        if(targetTeacher.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        if(targetTeacher.get().getPassword().equals(teacherDto.getPassword())){
            TeacherDto returnTeacher = new TeacherDto();
            returnTeacher.setId(targetTeacher.get().getId().intValue());
            returnTeacher.setUsername(targetTeacher.get().getUsername());

            return returnTeacher;
        }
        return null;
    }



}
