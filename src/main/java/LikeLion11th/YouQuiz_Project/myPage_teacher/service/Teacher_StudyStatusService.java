package LikeLion11th.YouQuiz_Project.myPage_teacher.service;

import LikeLion11th.YouQuiz_Project.repository.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacher_StudyStatusService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final Class_ChapterRepository classChapterRepository;
    private final Class_StudentRepository classStudentRepository;
    private final ChapterRepository chapterRepository;
    private final AnswerRepository answerRepository;
    public Teacher_StudyStatusService(ClassRepository classRepository, StudentRepository studentRepository, Class_ChapterRepository classChapterRepository,
                                      Class_StudentRepository classStudentRepository, ChapterRepository chapterRepository, AnswerRepository answerRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.classChapterRepository = classChapterRepository;
        this.classStudentRepository = classStudentRepository;
        this.chapterRepository = chapterRepository;
        this.answerRepository = answerRepository;
    }

    public List<Long> findClassIdByTeacherId(Long teacherId) { // Find ClassID using TeacherID
        List<Long> classId = classRepository.findClassIdByTeacherId(teacherId);
        return classId;
    }

    public List<Long> findChapIdByClassId(Long classId) { // Find ChapterID using ClassID
        List<Long> chapId = classChapterRepository.findChapIdByClassId(classId);
        return chapId;
    }

    public List<Long> findStuIdByClassId(Long classId) { // Find StudentID using ClassID
        List<Long> studentId = classStudentRepository.findStuIdByClassId(classId);
        return studentId;
    }

    public String findSchoolNameByClassId(Long classId) { // Find SchoolName using ClassID
        List<String> schoolName = classRepository.findSchoolNameByClassId(classId);
        return schoolName.get(0);
    }

    public Integer findGradeByClassId(Long classId) { // Find Grade using ClassID
        List<Integer> grade = classRepository.findGradeByClassId(classId);
        return grade.get(0);
    }

    public Integer findClassByClassId(Long classId) { // Find Class_Num using ClassID
        List<Integer> class_num = classRepository.findClassByClassId(classId);
        return class_num.get(0);
    }

    public String findNameByStuId(Long studentId) { // Find Username using StudentID
        List<String> name = studentRepository.findUserNameByStuId(studentId);
        return name.get(0);
    }

    public String checkStudyStatus(Long studentId, Long chapId) { // Check Learning Status using studentID & ChapterID
        List<Long> data = answerRepository.checkStudyStatus(studentId, chapId);
        return (data.isEmpty() == true) ? "X" : "O";
    }

    public JSONObject SearchStudyStatus (Long teacherId) { // Find Student's Study Status using TeacherID
        JSONObject returnJSON = new JSONObject();
        JSONArray classInfo = new JSONArray();
        List<Long> classData = findClassIdByTeacherId(teacherId);
        for (Long classId : classData) { //  Repeat as the times of the number of extracted ClassID
            List<Long> studentList = findStuIdByClassId(classId);
            JSONArray studentInfo = new JSONArray();
            for (Long studentId : studentList) { //  Repeat as the times of the number of extracted StudentID
                JSONObject studentData = new JSONObject();
                JSONArray chapterInfo = new JSONArray();
                List<Long> chapData = findChapIdByClassId(classId);
                for (Long chapId : chapData) { //  Repeat as the times of the number of extracted ChapterID
                    JSONObject chapterData = new JSONObject();
                    chapterData.put("chap_id", String.valueOf(chapId));
                    chapterData.put("status", checkStudyStatus(studentId, chapId));
                    chapterInfo.add(chapterData);
                }
                studentData.put("school_name", findSchoolNameByClassId(classId));
                studentData.put("grade", String.valueOf(findGradeByClassId(classId)));
                studentData.put("class_num", String.valueOf(findClassByClassId(classId)));
                studentData.put("student_id", String.valueOf(studentId));
                studentData.put("student_name", String.valueOf(findNameByStuId(studentId)));
                studentData.put("chapter_data", chapterInfo);
                studentInfo.add(studentData);
            }
            classInfo.add(studentInfo);
        }
        returnJSON.put("student_list", classInfo);
        return returnJSON;
    }
}