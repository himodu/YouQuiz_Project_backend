package LikeLion11th.YouQuiz_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="class_student")
public class Class_StudentEntity extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(
            targetEntity = StudentEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity studentEntity;

    @JsonIgnore
    @ManyToOne(
            targetEntity = ClassEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="CLASS_ID")
    private ClassEntity classEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "Class_StudentEntity{" +
                "id=" + id +
                ", studentEntity=" + studentEntity +
                ", classEntity=" + classEntity +
                '}';
    }
}
