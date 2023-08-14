package LikeLion11th.YouQuiz_Project.study.service;

import LikeLion11th.YouQuiz_Project.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.entity.ChapterEntity;
import LikeLion11th.YouQuiz_Project.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.model.AnswerDto;
import LikeLion11th.YouQuiz_Project.model.ChapterDto;
import LikeLion11th.YouQuiz_Project.repository.AnswerDao;
import LikeLion11th.YouQuiz_Project.repository.ChapterDao;
import LikeLion11th.YouQuiz_Project.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudyService {
    private final AnswerDao answerDao;
    private final StudentDao studentDao;
    private final ChapterDao chapterDao;


    public StudyService(@Autowired AnswerDao answerDao, @Autowired StudentDao studentDao, @Autowired ChapterDao chapterDao) {
        this.answerDao = answerDao;
        this.studentDao = studentDao;
        this.chapterDao = chapterDao;
    }

    public void createAnswer(AnswerDto answerDto, int student_id, int chapter_id){
        AnswerEntity answerEntity = new AnswerEntity();


        Optional<ChapterEntity> chapterEntity = chapterDao.findById(Long.valueOf(chapter_id));
        if(chapterEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<StudentEntity> studentEntity = studentDao.findById(Long.valueOf(student_id));
        if(studentEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Integer> c_answersList = chapterEntity.get().getCorrect_answerList();
        List<Integer> answersList = answerDto.getAnswer_list();

        int score = 0;

        for(int i=0; i< c_answersList.size(); i++){
            if(c_answersList.get(i) == answersList.get(i)){
                score += 20;
            }
        }

        answerEntity.setScore(score);
        answerEntity.setAnswersList(answersList);
        answerEntity.setAnswer_sentence(answerDto.getAnswer_sentence());
        answerEntity.setChapterEntity(chapterEntity.get());
        answerEntity.setStudentEntity(studentEntity.get());
        answerEntity.setCommentEntity(null);

        answerDao.save(answerEntity);

    }

    public ChapterDto readChapter(int chap_id){

        ChapterDto chapterDto = new ChapterDto();

        Optional<ChapterEntity> chapterEntity  = this.chapterDao.findById(Long.valueOf(chap_id));
        if(chapterEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        chapterDto.setYoutube_link(chapterEntity.get().getYoutube_link());
        chapterDto.setCorrect_answerList(chapterEntity.get().getCorrect_answerList());
        chapterDto.setQuizEntityList(chapterEntity.get().getQuizEntityList());


        return chapterDto;

    }



}
