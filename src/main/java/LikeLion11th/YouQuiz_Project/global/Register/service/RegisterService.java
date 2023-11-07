package LikeLion11th.YouQuiz_Project.global.Register.service;


import LikeLion11th.YouQuiz_Project.global.Login.dto.StudentDto;
import LikeLion11th.YouQuiz_Project.global.Login.repository.StudentRepository;
import LikeLion11th.YouQuiz_Project.global.Login.repository.TeacherRepository;
import LikeLion11th.YouQuiz_Project.domain.study.entity.ClassEntity;
import LikeLion11th.YouQuiz_Project.domain.study.entity.Class_StudentEntity;
import LikeLion11th.YouQuiz_Project.global.Login.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.global.Login.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.global.Register.dto.ClassDto;
import LikeLion11th.YouQuiz_Project.global.Login.dto.TeacherDto;
import LikeLion11th.YouQuiz_Project.domain.study.repository.ClassRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.Class_StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final Class_StudentRepository class_studentRepository;
    private final TeacherRepository teacherRepository;


    public RegisterService(
            @Autowired StudentRepository studentRepository,
            @Autowired ClassRepository classRepository,
            @Autowired Class_StudentRepository class_studentRepository,
            @Autowired TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.class_studentRepository = class_studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void createStudent(StudentDto studentDto){
        StudentEntity studentEntity = new StudentEntity();

        if(studentRepository.findByUserId(studentDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
        studentEntity.setUserId(studentDto.getUserId());
        studentEntity.setPassword(studentDto.getPassword());
        studentEntity.setUsername(studentDto.getUsername());
        studentEntity.setBirth(studentDto.getBirth());
        studentEntity.setSex(studentDto.getSex());
        studentEntity.setPhoneNumber(studentDto.getPhoneNumber());

        this.studentRepository.save(studentEntity);

        Optional<ClassEntity> classEntity = this.classRepository.findById(Long.valueOf(studentDto.getClass_id()));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Class_StudentEntity class_studentEntity = new Class_StudentEntity();
        class_studentEntity.setClassEntity(classEntity.get());
        class_studentEntity.setStudentEntity(studentEntity);
        this.class_studentRepository.save(class_studentEntity);

    }

    public void createTeacher(TeacherDto teacherDto){
        TeacherEntity teacherEntity = new TeacherEntity();

        if(teacherRepository.findByUserId(teacherDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
        teacherEntity.setUserId(teacherDto.getUserId());
        teacherEntity.setPassword(teacherDto.getPassword());
        teacherEntity.setUsername(teacherDto.getUsername());
        teacherEntity.setBirth(teacherDto.getBirth());
        teacherEntity.setSex(teacherDto.getSex());
        teacherEntity.setPhoneNumber(teacherDto.getPhoneNumber());

        Optional<ClassEntity> classEntity = this.classRepository.findById(Long.valueOf(teacherDto.getClass_id()));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        List<ClassEntity> classList = new ArrayList<>();

        classList.add(classEntity.get());

        teacherEntity.setClassEntityList(classList);

        classEntity.get().setTeacherEntity(teacherEntity);
        this.teacherRepository.save(teacherEntity);
    }


    public ClassDto AuthSchool(ClassDto classDto){

        ClassDto New_classdto = new ClassDto();

        Optional<ClassEntity> classEntity = this.classRepository.findByCode(classDto.getCode());
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
