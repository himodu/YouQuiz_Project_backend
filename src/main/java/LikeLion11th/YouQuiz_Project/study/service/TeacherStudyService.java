package LikeLion11th.YouQuiz_Project.study.service;

import LikeLion11th.YouQuiz_Project.entity.*;
import LikeLion11th.YouQuiz_Project.model.*;
import LikeLion11th.YouQuiz_Project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherStudyService {
    private final ClassRepository1 classRepository1;
    private final ChapterRepository1 chapterRepository1;
    private final Class_ChapterRepository1 classChapterRepository1;
    private final TeacherRepository1 teacherRepository1;

    private final CommentRepository1 commentRepository1;
    private final AnswerRepository1 answerRepository1;

    public TeacherStudyService(
            @Autowired ClassRepository1 classRepository1,
            @Autowired ChapterRepository1 chapterRepository1,
            @Autowired Class_ChapterRepository1 classChapterRepository1,
            @Autowired TeacherRepository1 teacherRepository1,

            @Autowired CommentRepository1 commentRepository1,
            @Autowired AnswerRepository1 answerRepository1
    ){
        this.classRepository1 = classRepository1;
        this.chapterRepository1 = chapterRepository1;
        this.classChapterRepository1 = classChapterRepository1;
        this.teacherRepository1 = teacherRepository1;

        this.commentRepository1 = commentRepository1;
        this.answerRepository1 = answerRepository1;
    }

    public TeacherChapterListDto readTeacherChapter(Long teacher_id){
        TeacherChapterListDto teacherChapterListDto = new TeacherChapterListDto();
        List<TeacherChapterDto> teacherChapterList = new ArrayList<>();

        Optional<ClassEntity> classEntity = classRepository1.findByTeacherEntity(teacher_id);

        List<Class_ChapterEntity> classChapterEntityList = classEntity.get().getClass_chapterEntityList();

        for(Class_ChapterEntity class_chapterEntity :classChapterEntityList) {
            // 그 클래스 안의 chapter
            ChapterEntity chapter = class_chapterEntity.getChapterEntity();
            TeacherChapterDto teacherChapter = new TeacherChapterDto();
            teacherChapter.setChapter_id(chapter.getId());
            teacherChapter.setTitle(chapter.getTitle());
            teacherChapter.setYoutube_link(chapter.getYoutube_link());

            teacherChapterList.add(teacherChapter);
        }

        teacherChapterListDto.setTeacherChapterList(teacherChapterList);

        return teacherChapterListDto;
    }

    public InfoDto readAllAboutChapter(Long class_id, Long chapter_id, Long teacher_id) {
        InfoDto infoDto = new InfoDto();
        List<Class_StudentEntity> classStudentEntityList = new ArrayList<>();
        List<AnswerSentenceDto> answer_sentence_list = new ArrayList<>();
        List<CommentEntity> commentEntityList = new ArrayList<>();

        Optional<ClassEntity> classEntity = classRepository1.findById(Long.valueOf(class_id));
        if(classEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<ChapterEntity> chapterEntity = chapterRepository1.findById(Long.valueOf(chapter_id));
        if(chapterEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<TeacherEntity> teacherEntity = teacherRepository1.findById(Long.valueOf(teacher_id));
        if(teacherEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // chapter의 문제, 선택지, 정답
        // Chapter
        infoDto.setTitle(chapterEntity.get().getTitle());
        infoDto.setCorrect_answerList(chapterEntity.get().getCorrect_answerList());
        infoDto.setYoutube_link(chapterEntity.get().getYoutube_link());
        infoDto.setQuizEntityList(chapterEntity.get().getQuizEntityList());

        // Teacher
        infoDto.setTeacher_id(teacherEntity.get().getId());

        classStudentEntityList = classEntity.get().getClass_StudentEntityList();

        // 클래스 안 전체 학생 돌기
        for(Class_StudentEntity classStudentEntity :classStudentEntityList){
            StudentEntity student = classStudentEntity.getStudentEntity();
            Long student_id = student.getId(); // class 안의 각 학생 id
            String username = student.getUsername();
            List<AnswerEntity> answerEntityList = student.getAnswerList();

            for(AnswerEntity answer : answerEntityList){
                ChapterEntity chapter = answer.getChapterEntity();

                // 학생이 작성한 answer 중 현재 chapter에 작성한 answer
                if(chapter.getId()==chapter_id)
                {
                    // 주관식 답변 list에 학생 답변 추가
                    AnswerSentenceDto answerDto = new AnswerSentenceDto(student_id, username, answer.getAnswer_sentence());
                    answer_sentence_list.add(answerDto);

                    // 교육자 comment list에 추가
                    CommentEntity commentEntity = answer.getCommentEntity();
                    commentEntityList.add(commentEntity);
                }
            }
        }

        // 클래스 안의 전체 학생들 돌아야 될 것 같음
        // 학생들이 작성한 주관식 답변 list
        // Answer
        infoDto.setAnswer_sentence_list(answer_sentence_list);

        // teacher_id로 선생님 찾고
        // 해당 선생님이 class의 학생들에게 작성한 comment list
        // 각 학생들이 작성한 answer_id 따라가면 CommentEntity가 있음
        // Comment
        infoDto.setCommentEntityList(commentEntityList);

        return infoDto;

    }

    public void createComment(CommentDto commentDto, Long teacher_id, Long chapter_id, Long student_id){
        CommentEntity commentEntity = new CommentEntity();

        Optional<TeacherEntity> teacherEntity = teacherRepository1.findById(Long.valueOf(teacher_id));
        if(teacherEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<AnswerEntity> answerEntity = answerRepository1.findByChapterEntityAndStudentEntity1(Long.valueOf(chapter_id), Long.valueOf(student_id));
        if(answerEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        commentEntity.setId(commentDto.getId());
        commentEntity.setComment(commentDto.getComment());
        commentEntity.setAnswerEntity(answerEntity.get());
        commentEntity.setTeacherEntity(teacherEntity.get());

        answerEntity.get().setCommentEntity(commentEntity);

        this.commentRepository1.save(commentEntity);
    }
}
