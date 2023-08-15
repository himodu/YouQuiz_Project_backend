package LikeLion11th.YouQuiz_Project.Register.service;

import LikeLion11th.YouQuiz_Project.entity.ClassEntity;
import LikeLion11th.YouQuiz_Project.entity.Class_StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.model.ClassDto;
import LikeLion11th.YouQuiz_Project.model.StudentDto;
import LikeLion11th.YouQuiz_Project.model.TeacherDto;
import LikeLion11th.YouQuiz_Project.repository.ClassRepository1;
import LikeLion11th.YouQuiz_Project.repository.Class_StudentRepository1;
import LikeLion11th.YouQuiz_Project.repository.StudentRepository1;
import LikeLion11th.YouQuiz_Project.repository.TeacherRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private final StudentRepository1 studentRepository1;
    private final ClassRepository1 classRepository;
    private final Class_StudentRepository1 class_studentRepository1;
    private final TeacherRepository1 teacherRepository1;


    public RegisterService(
            @Autowired StudentRepository1 studentRepository1,
            @Autowired ClassRepository1 classRepository,
            @Autowired Class_StudentRepository1 class_studentRepository1,
            @Autowired TeacherRepository1 teacherRepository1) {
        this.studentRepository1 = studentRepository1;
        this.classRepository = classRepository;
        this.class_studentRepository1 = class_studentRepository1;
        this.teacherRepository1 = teacherRepository1;
    }

    public void createStudent(StudentDto studentDto){
        StudentEntity studentEntity = new StudentEntity();

        if(studentRepository1.findByUserId(studentDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
        studentEntity.setUserId(studentDto.getUserId());
        studentEntity.setPassword(studentDto.getPassword());
        studentEntity.setUsername(studentDto.getUsername());
        studentEntity.setBirth(studentDto.getBirth());
        studentEntity.setSex(studentDto.getSex());
        studentEntity.setPhoneNumber(studentDto.getPhoneNumber());

        this.studentRepository1.save(studentEntity);

        Optional<ClassEntity> classEntity = this.classRepository.findById(Long.valueOf(studentDto.getClass_id()));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Class_StudentEntity class_studentEntity = new Class_StudentEntity();
        class_studentEntity.setClassEntity(classEntity.get());
        class_studentEntity.setStudentEntity(studentEntity);
        this.class_studentRepository1.save(class_studentEntity);

    }

    public void createTeacher(TeacherDto teacherDto){
        TeacherEntity teacherEntity = new TeacherEntity();

        if(teacherRepository1.findByUserId(teacherDto.getUserId()).isPresent()){
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
        this.teacherRepository1.save(teacherEntity);
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
