package LikeLion11th.YouQuiz_Project.myPage_teacher.service;

import LikeLion11th.YouQuiz_Project.entity.CommentEntity;
import LikeLion11th.YouQuiz_Project.repository.*;
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

    public List<CommentEntity> CountComment(Long studentId, Long chapId) { // Count the number of Teacher's Comment using StudentID AND ChapterID
        List<CommentEntity> comment = answerRepository.CountComment(studentId, chapId);
        return comment;
    }

    public Long CountChapter(Long classId) { // Count the countumber of Chapter in Class using ClassID
        List<Long> chapterNumber = classChapterRepository.CountChapter(classId);
        return chapterNumber.get(0);
    }

    public String findURLByChapID(Long chapId) { // Find YoutubeURL using ChapterID
        List<String> URL = chapterRepository.findURLByChapID(chapId);
        return URL.get(0);
    }

    public String findYoutubeTitleByChapID(Long chapId) { // Find YoutubeTitle using ChapterID
        List<String> title = chapterRepository.findYoutubeTitleByChapID(chapId);
        return title.get(0);
    }

    public JSONObject SearchEvaluationStatus (Long teacherId) { // Find Student's Evaluation Status using TeacherID
        JSONObject resultJSON = new JSONObject();
        JSONArray evaluationInfo = new JSONArray();
        List<Long> classData = findClassIdByTeacherId(teacherId);
        for (Long classId : classData) { //  Repeat as the times of the number of extracted ClassID
            List<Long> studentData = findStuIdByClassId(classId);
            List<Long> chapterData = findChapIdByClassId(classId);
            JSONArray stuData = new JSONArray();
            for (Long chapId : chapterData) { // Repeat as the times of the number of extracted ChapterID
                JSONObject studentInfo = new JSONObject();
                Integer CompleteStudent = 0;
                Long userCommentNumber = 0L;

                for (Long studentId : studentData) { //  Repeat as the times of the number of extracted studentID
                    List<CommentEntity> comments = answerRepository.CountComment(studentId, chapId);
                    for(CommentEntity comment : comments){
                        if(!comment.getComment().isBlank()){
                            userCommentNumber += 1;
                        }
                    }
                }
                studentInfo.put("class_id", classId);
                studentInfo.put("youtube_link", findURLByChapID(chapId));
                studentInfo.put("complete_student", String.valueOf(userCommentNumber));
                studentInfo.put("total_student", String.valueOf(CountStudent(classId)));
                studentInfo.put("chap_id", String.valueOf(chapId));
                studentInfo.put("youtube_title", findYoutubeTitleByChapID(chapId));
                stuData.add(studentInfo);
            }
            evaluationInfo.add(stuData);
        }
        resultJSON.put("evaluation_status", evaluationInfo);
        return resultJSON;
    }
}
