package LikeLion11th.YouQuiz_Project.domain.study.entity;

import LikeLion11th.YouQuiz_Project.global.Login.entity.TeacherEntity;
import LikeLion11th.YouQuiz_Project.global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class")
public class ClassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String school_name;
    private int grade;
    private int class_num;
    private String code;

    @JsonIgnore
    @ManyToOne(
            targetEntity = TeacherEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name= "TEACHER_ID")
    private TeacherEntity teacherEntity;

    @JsonIgnore
    @OneToMany(
            targetEntity = Class_ChapterEntity.class,
            mappedBy = "classEntity",
            fetch = FetchType.LAZY
    )
    private List<Class_ChapterEntity> class_chapterEntityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            targetEntity = Class_StudentEntity.class,
            mappedBy = "classEntity",
            fetch = FetchType.LAZY
    )
    private List<Class_StudentEntity> class_StudentEntityList = new ArrayList<>();

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClass_num() {
        return class_num;
    }

    public void setClass_num(int class_num) {
        this.class_num = class_num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String class_code) {
        this.code = class_code;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Class_StudentEntity> getClass_StudentEntityList() {
        return class_StudentEntityList;
    }

    public void setClass_StudentEntityList(List<Class_StudentEntity> class_StudentEntityList) {
        this.class_StudentEntityList = class_StudentEntityList;
    }

    public List<Class_ChapterEntity> getClass_chapterEntityList() {
        return class_chapterEntityList;
    }

    public void setClass_chapterEntityList(List<Class_ChapterEntity> class_chapterEntityList) {
        this.class_chapterEntityList = class_chapterEntityList;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", school_name='" + school_name + '\'' +
                ", grade=" + grade +
                ", class_num=" + class_num +
                ", class_code='" + code + '\'' +
                ", teacherEntity=" + teacherEntity +
                ", class_chapterEntityList=" + class_chapterEntityList +
                ", class_StudentEntityList=" + class_StudentEntityList +
                '}';
    }
}
