package LikeLion11th.YouQuiz_Project.Register.service;

import LikeLion11th.YouQuiz_Project.entity.ClassEntity;
import LikeLion11th.YouQuiz_Project.entity.Class_StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.model.ClassDto;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import LikeLion11th.YouQuiz_Project.repository.ClassDao;
import LikeLion11th.YouQuiz_Project.repository.Class_StudentDao;
import LikeLion11th.YouQuiz_Project.repository.StudentDao;
import LikeLion11th.YouQuiz_Project.repository.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private final StudentDao studentDao;
    private final ClassDao classDao;
    private final Class_StudentDao class_studentDao;
    private final TeacherDao teacherDao;


    public RegisterService(
            @Autowired StudentDao studentDao,
            @Autowired ClassDao classDao,
            @Autowired Class_StudentDao class_studentDao,
            @Autowired TeacherDao teacherDao) {
        this.studentDao = studentDao;
        this.classDao = classDao;
        this.class_studentDao = class_studentDao;
        this.teacherDao = teacherDao;
    }

    public void createStudent(StudentDto studentDto){
        StudentEntity studentEntity = new StudentEntity();

        if(studentDao.findByUserId(studentDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
        studentEntity.setUserId(studentDto.getUserId());
        studentEntity.setPassword(studentDto.getPassword());
        studentEntity.setUsername(studentDto.getUsername());
        studentEntity.setBirth(studentDto.getBirth());
        studentEntity.setSex(studentDto.getSex());
        studentEntity.setPhoneNumber(studentDto.getPhoneNumber());

        this.studentDao.save(studentEntity);

        Optional<ClassEntity> classEntity = this.classDao.findById(Long.valueOf(studentDto.getClass_id()));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Class_StudentEntity class_studentEntity = new Class_StudentEntity();
        class_studentEntity.setClassEntity(classEntity.get());
        class_studentEntity.setStudentEntity(studentEntity);
        this.class_studentDao.save(class_studentEntity);

    }

    public void createTeacher(TeacherDto teacherDto){
        TeacherEntity teacherEntity = new TeacherEntity();

        if(teacherDao.findByUserId(teacherDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
        teacherEntity.setUserId(teacherDto.getUserId());
        teacherEntity.setPassword(teacherDto.getPassword());
        teacherEntity.setUsername(teacherDto.getUsername());
        teacherEntity.setBirth(teacherDto.getBirth());
        teacherEntity.setSex(teacherDto.getSex());
        teacherEntity.setPhoneNumber(teacherDto.getPhoneNumber());

        Optional<ClassEntity> classEntity = this.classDao.findById(Long.valueOf(teacherDto.getClass_id()));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        List<ClassEntity> classList = new ArrayList<>();

        classList.add(classEntity.get());

        teacherEntity.setClassEntityList(classList);

        classEntity.get().setTeacherEntity(teacherEntity);
        this.teacherDao.save(teacherEntity);
    }


    public ClassDto AuthSchool(ClassDto classDto){

        ClassDto New_classdto = new ClassDto();

        Optional<ClassEntity> classEntity = this.classDao.findByCode(classDto.getCode());
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        int id = classEntity.get().getId().intValue();

        New_classdto.setId(id);
        New_classdto.setCode(classEntity.get().getCode());
        New_classdto.setSchool_name(classEntity.get().getSchool_name());
        New_classdto.setGrade(classEntity.get().getGrade());
        New_classdto.setClass_num(classEntity.get().getClass_num());


        return New_classdto;
    }
}
