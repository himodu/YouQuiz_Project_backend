package LikeLion11th.YouQuiz_Project.domain.myPage_teacher.service;

import LikeLion11th.YouQuiz_Project.domain.study.repository.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Teacher_EvaluationStatusService {
    private final ClassRepository classRepository;
    private final AnswerRepository answerRepository;
    private final Class_StudentRepository classStudentRepository;
    private final Class_ChapterRepository classChapterRepository;
    private final ChapterRepository chapterRepository;

    private Teacher_EvaluationStatusService(ClassRepository classRepository, AnswerRepository answerRepository, Class_StudentRepository classStudentRepository,
                                            Class_ChapterRepository classChapterRepository, ChapterRepository chapterRepository) {
        this.classRepository = classRepository;
        this.answerRepository = answerRepository;
        this.classStudentRepository = classStudentRepository;
        this.classChapterRepository = classChapterRepository;
        this.chapterRepository = chapterRepository;
    }

    public List<Long> findClassIdByTeacherId(Long teacherId) { // Find ClassID using TeacherID
        List<Long> classId = classRepository.findClassIdByTeacherId(teacherId);
        return classId;
    }

    public List<Long> findStuIdByClassId(Long classId) { // Find StudentID using ClassID
        List<Long> studentId = classStudentRepository.findStuIdByClassId(classId);
        return studentId;
    }

    public List<Long> findChapIdByClassId(Long classId) { // Find ChapterID using ClassID
        List<Long> chapId = classChapterRepository.findChapIdByClassId(classId);
        return chapId;
    }

    public Long CountStudent(Long classId) { // Count the Number of Student in Class using ClassID
        List<Long> studentNumber = classStudentRepository.CountStudent(classId);
        return studentNumber.get(0);
    }

    public List<Long> CountComment(Long studentId, Long chapId) { // Count the number of Teacher's Comment using StudentID AND ChapterID
        List<Long> commendId = answerRepository.CountComment(studentId, chapId);
        return commendId;
    }

    public Long CountChapter(Long classId) { // Count the number of Chapter in Class using ClassID
        List<Long> chapterNumber = classChapterRepository.CountChapter(classId);
        return chapterNumber.get(0);
    }

    public String findURLByChapID(Long chapId) { // Find YoutubeURL using ChapterID
        List<String> URL = chapterRepository.findURLByChapID(chapId);
        return URL.get(0);
    }

    public JSONObject SearchEvaluationStatus (Long teacherId) { // Find Student's Evaluation Status using TeacherID
        JSONObject resultJSON = new JSONObject();
        JSONArray evaluationInfo = new JSONArray();
        List<Long> classData = findClassIdByTeacherId(teacherId);
        for (Long classId : classData) { //  Repeat as the times of the number of extracted ClassID
            List<Long> studentData = findStuIdByClassId(classId);
            List<Long> chapterData = findChapIdByClassId(classId);
            JSONObject studentInfo = new JSONObject();
            for (Long chapId : chapterData) { // Repeat as the times of the number of extracted ChapterID
                Integer CompleteStudent = 0;
                Long userCommentNumber = 0L;
                for (Long studentId : studentData) { //  Repeat as the times of the number of extracted studentID
                    userCommentNumber += (CountComment(studentId, chapId).isEmpty()) ? 0 : 1; // Add 1 to Student Comment Counter Variable (userCommentNumber) if Student's Answer exist.
                    CompleteStudent += (userCommentNumber == CountChapter(classId)) ? 1 : 0; // answer evaluation is finished if the number of Teacher's Comment equals the number of Chapter in Class
                }
                studentInfo.put("youtube_link", findURLByChapID(chapId));
                studentInfo.put("complete_student", String.valueOf(CompleteStudent));
                studentInfo.put("total_student", String.valueOf(CountStudent(classId)));
                studentInfo.put("chap_id", String.valueOf(chapId));
            }
            studentInfo.put("class_id", classId);
            evaluationInfo.add(studentInfo);
        }
        resultJSON.put("evaluation_status", evaluationInfo);
        return resultJSON;
    }
}
