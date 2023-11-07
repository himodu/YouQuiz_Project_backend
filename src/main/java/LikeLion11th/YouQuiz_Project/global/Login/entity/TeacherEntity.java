package LikeLion11th.YouQuiz_Project.global.Login.entity;

import LikeLion11th.YouQuiz_Project.global.BaseEntity;
import LikeLion11th.YouQuiz_Project.domain.study.entity.ClassEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class TeacherEntity extends BaseEntity {
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
            targetEntity = ClassEntity.class,
            mappedBy = "teacherEntity",
            fetch = FetchType.LAZY
    )
    private List<ClassEntity> classEntityList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ClassEntity> getClassEntityList() {
        return classEntityList;
    }

    public void setClassEntityList(List<ClassEntity> classEntityList) {
        this.classEntityList = classEntityList;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", birth='" + birth + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", classEntityList=" + classEntityList +
                '}';
    }
}
