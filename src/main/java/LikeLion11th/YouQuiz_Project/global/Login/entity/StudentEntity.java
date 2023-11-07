package LikeLion11th.YouQuiz_Project.global.Login.entity;

import LikeLion11th.YouQuiz_Project.domain.study.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.global.BaseEntity;
import LikeLion11th.YouQuiz_Project.domain.study.entity.Class_StudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "student")
public class StudentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String username;
    private String birth;
    private String sex;
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(
            targetEntity = Class_StudentEntity.class,
            mappedBy = "studentEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Class_StudentEntity> class_studentList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            targetEntity = AnswerEntity.class,
            mappedBy = "studentEntity",
            fetch = FetchType.LAZY
    )
    private List<AnswerEntity> answerList = new ArrayList<>();

    public List<Class_StudentEntity> getClass_studentList() {
        return class_studentList;
    }

    public void setClass_studentList(List<Class_StudentEntity> class_studentList) {
        this.class_studentList = class_studentList;
    }

    public List<AnswerEntity> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerEntity> answerList) {
        this.answerList = answerList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public List<Class_StudentEntity> getClass_student() {
//        return class_studentList;
//    }
//
//    public void setClass_student(List<Class_StudentEntity> class_student) {
//        this.class_studentList = class_student;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "StudentEntity{" +
//                "id=" + id +
//                ", userId='" + userId + '\'' +
//                ", password='" + password + '\'' +
//                ", username='" + username + '\'' +
//                ", birth='" + birth + '\'' +
//                ", sex='" + sex + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", class_student=" + class_studentList +
//                '}';
//    }
}
