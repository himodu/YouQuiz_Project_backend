package LikeLion11th.YouQuiz_Project.domain.study.service;

import LikeLion11th.YouQuiz_Project.domain.study.entity.AnswerEntity;
import LikeLion11th.YouQuiz_Project.domain.study.entity.ChapterEntity;
import LikeLion11th.YouQuiz_Project.domain.study.entity.CommentEntity;
import LikeLion11th.YouQuiz_Project.global.Login.entity.StudentEntity;
import LikeLion11th.YouQuiz_Project.domain.study.dto.AnswerDto;
import LikeLion11th.YouQuiz_Project.domain.study.dto.ChapterDto;
import LikeLion11th.YouQuiz_Project.domain.study.repository.AnswerRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.ChapterRepository;
import LikeLion11th.YouQuiz_Project.domain.study.repository.CommentRepository;
import LikeLion11th.YouQuiz_Project.global.Login.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudyService {
    private final AnswerRepository answerRepository;
    private final StudentRepository studentRepository;
    private final ChapterRepository chapterRepository;
    private final CommentRepository commentRepository;


    public StudyService(@Autowired AnswerRepository answerRepository, @Autowired StudentRepository studentRepository, @Autowired ChapterRepository chapterRepository, @Autowired CommentRepository commentRepository) {
        this.answerRepository = answerRepository;
        this.studentRepository = studentRepository;
        this.chapterRepository = chapterRepository;
        this.commentRepository = commentRepository;}

    public void createAnswer(AnswerDto answerDto, int student_id, int chapter_id){
        AnswerEntity answerEntity = new AnswerEntity();

        Optional<ChapterEntity> chapterEntity = chapterRepository.findById(Long.valueOf(chapter_id));
        if(chapterEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<StudentEntity> studentEntity = studentRepository.findById(Long.valueOf(student_id));
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
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment("");
        answerEntity.setCommentEntity(commentEntity);
        commentRepository.save(commentEntity);
        answerRepository.save(answerEntity);
    }

    public ChapterDto readChapter(long chap_id){

        ChapterDto chapterDto = new ChapterDto();

        Optional<ChapterEntity> chapterEntity  = this.chapterRepository.findById(Long.valueOf(chap_id));
        if(chapterEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        chapterDto.setTitle(chapterEntity.get().getTitle());
        chapterDto.setYoutube_link(chapterEntity.get().getYoutube_link());
        chapterDto.setCorrect_answerList(chapterEntity.get().getCorrect_answerList());
        chapterDto.setQuizEntityList(chapterEntity.get().getQuizEntityList());


        return chapterDto;

    }



}
