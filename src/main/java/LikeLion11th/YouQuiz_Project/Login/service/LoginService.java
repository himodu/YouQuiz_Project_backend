package LikeLion11th.YouQuiz_Project.Login.service;

import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import LikeLion11th.YouQuiz_Project.repository.StudentDao;
import LikeLion11th.YouQuiz_Project.repository.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    public LoginService(@Autowired StudentDao studentDao, @Autowired TeacherDao teacherDao) {

        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    public StudentDto Student_login(StudentDto studentDto){


        Optional<StudentEntity> targetStudent = studentDao.findByUserId(studentDto.getUserId());
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

        Optional<TeacherEntity> targetTeacher = this.teacherDao.findByUserId(teacherDto.getUserId());
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
