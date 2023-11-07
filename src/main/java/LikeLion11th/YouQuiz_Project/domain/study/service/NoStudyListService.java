package LikeLion11th.YouQuiz_Project.domain.study.service;

import LikeLion11th.YouQuiz_Project.domain.study.repository.Class_ChapterRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.Class_StudentRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.AnswerRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.ChapterRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoStudyListService {
    private final AnswerRepository answerRepository;
    private final ChapterRepository chapterRepository;
    private final Class_ChapterRepository classChapterRepository;
    private final Class_StudentRepository classStudentRepository;
    public NoStudyListService (AnswerRepository answerRepository, ChapterRepository chapterRepository, Class_ChapterRepository classChapterRepository, Class_StudentRepository classStudentRepository) {
        this.answerRepository = answerRepository;
        this.chapterRepository = chapterRepository;
        this.classChapterRepository = classChapterRepository;
        this.classStudentRepository = classStudentRepository;
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

    public String findYoutubeTitleByChapID(Long chapId) { // Find YoutubeTitle using ChapterID
        List<String> title = chapterRepository.findYoutubeTitleByChapID(chapId);
        return title.get(0);
    }

    public JSONObject findYoutubeURLByStuID(Long studentId) { // Find YoutubeURL using StudentID
        JSONObject returnJSON = new JSONObject();
        JSONArray chapterArray = new JSONArray(); // Create JSON Array using JSON DATA
        List<Long> ClassData = findClassIdByStuId(studentId); // Find ClassID using StudentID
        for (Long classId : ClassData) { //  Repeat as the times of the number of extracted ClassID
            List<Long> ChapData = findChapIdByClassId(classId); // Find ChapterID using ClassID
            for (Long chapId : ChapData) { // Repeat as the times of the number of extracted ChapterID
                JSONObject chapterObject = new JSONObject(); // Create JSON Data using extracted data (YoutubeLink & ChapterID) from DB
                if (checkStudyStatus(studentId, chapId) == "U") { // Add ChapterID & YoutubeURL to JSON if ONLY NOT Studied
                    chapterObject.put("chap_id", String.valueOf(chapId));
                    chapterObject.put("youtube_link", findURLByChapID(chapId));
                    chapterObject.put("title", findYoutubeTitleByChapID(chapId));
                    chapterArray.add(chapterObject);
                } else {
                    continue;
                }
            }
        }
        returnJSON.put("no_study_list", chapterArray);
        return returnJSON;
    }
}