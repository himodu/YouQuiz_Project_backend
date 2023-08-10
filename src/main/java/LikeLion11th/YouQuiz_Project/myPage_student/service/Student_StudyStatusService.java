package LikeLion11th.YouQuiz_Project.myPage_student.service;

import LikeLion11th.YouQuiz_Project.repository.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Student_StudyStatusService {
    private final Class_StudentRepository classStudentRepository;
    private final Class_ChapterRepository classChapterRepository;
    private final ChapterRepository chapterRepository;
    private final AnswerRepository answerRepository;
    private final QuizRepository quizRepository;
    public Student_StudyStatusService(Class_StudentRepository classStudentRepository, Class_ChapterRepository classChapterRepository,
                                      ChapterRepository chapterRepository, AnswerRepository answerRepository, QuizRepository quizRepository) {
        this.classStudentRepository = classStudentRepository;
        this.classChapterRepository = classChapterRepository;
        this.chapterRepository = chapterRepository;
        this.answerRepository = answerRepository;
        this.quizRepository = quizRepository;
    }

    public List<Long> findClassIdByStuId(Long studentId) { // Find ClassID using StudentID
        List<Long> classId = classStudentRepository.findClassIdByStuId(studentId); // Extracting the data which meets the criterion (criterion: StudentID)
        return classId;
    }

    public List<Long> findChapIdByClassId(Long classId) { // Find ChapterID using ClassID
        List<Long> chapId = classChapterRepository.findChapIdByClassId(classId); // Extracting the data which meets the criterion (criterion: ClassID)
        return chapId;
    }

    public String findURLByChapID(Long chapId) { // Find YoutubeURL using ChapterID
        List<String> URL = chapterRepository.findURLByChapID(chapId); // Extracting the data which meets the criterion (criterion: ChapterID)
        return URL.get(0);
    }

    public String checkStudyStatus(Long studentId, Long chapId) { // Check Learning Status using studentID & ChapterID
        List<Long> data = answerRepository.checkStudyStatus(studentId, chapId);
        return (data.isEmpty() == true) ? "U" : "S";
    }

    public JSONObject findYoutubeURLByStuID(Long studentId) { // Find YoutubeURL using StudentID
        JSONObject returnJSON = new JSONObject();
        JSONArray chapterArray = new JSONArray(); // Create JSON Array using JSON DATA
        List<Long> ClassData = findClassIdByStuId(studentId); // Find ClassID using StudentID
        for (Long classId : ClassData) { //  Repeat as the times of the number of extracted ClassID
            List<Long> ChapData = findChapIdByClassId(classId); // Find ChapterID using ClassID
            for (Long chapId : ChapData) { // Repeat as the times of the number of extracted ChapterID
                JSONObject chapterObject = new JSONObject(); // Create JSON Data using extracted data (YoutubeLink & ChapterID) from DB
                chapterObject.put("chap_id", String.valueOf(chapId));
                chapterObject.put("youtube_url", findURLByChapID(chapId));
                chapterObject.put("status", checkStudyStatus(studentId, chapId));
                chapterArray.add(chapterObject);
            }
        }
        returnJSON.put("studyStatus", chapterArray);
        return returnJSON;
    }

    public String findAnswerSentence (Long studentId, Long chapId) { // Find Answer_Sentence using StudentID & ChapterID
        List<String> sentence = answerRepository.findAnswerSentence(studentId, chapId); // Extracting the Student's answer_sentence
        return sentence.get(0);
    }

    public List<Integer> findAnswerList (Long studentId, Long chapId) { // Find Answer_List using StudentID & ChapterID
        List<Integer> answerlist = answerRepository.findAnswerList(studentId, chapId); // Extracting the Student's Answer_List
        return answerlist;
    }

    public String findQuestion(Long chapId) { // FInd Question using ChapterID
        List<String> question = quizRepository.findQuestion(chapId); // Extracting the Question
        return question.get(0);
    }

    public Integer findScore(Long studentId, Long chapId) { // Find Score using StudentID & ChapterID
        List<Integer> score = answerRepository.findScore(studentId, chapId); // Extracting the Student's Score
        return score.get(0);
    }

    public JSONObject findStudyStatus(Long studentId, Long chapId) { // Find Learning Status of Each Chapter
        JSONObject returnJSON = new JSONObject();
        JSONArray chapterArray = new JSONArray(); // Create JSON Array using JSON DATA
        JSONObject chapterObject = new JSONObject(); // Create JSON Data using extracted data (YoutubeLink & ChapterID) from DB
        chapterObject.put("youtube_url", findURLByChapID(chapId));
        chapterObject.put("question", findQuestion(chapId));
        chapterObject.put("answer_sentence", findAnswerSentence(studentId, chapId));
        chapterObject.put("answer_list", findAnswerList(studentId, chapId));
        chapterObject.put("score", findScore(studentId, chapId));
        chapterArray.add(chapterObject);
        returnJSON.put("studyResult", chapterArray);
        return returnJSON;
    }
}