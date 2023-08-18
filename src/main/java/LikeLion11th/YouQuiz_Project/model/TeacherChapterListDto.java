package LikeLion11th.YouQuiz_Project.model;

import java.util.ArrayList;
import java.util.List;

public class TeacherChapterListDto {
    List<TeacherChapterDto> teacherChapterList = new ArrayList<>();

    public List<TeacherChapterDto> getTeacherChapterList() {
        return teacherChapterList;
    }

    public void setTeacherChapterList(List<TeacherChapterDto> teacherChapterList) {
        this.teacherChapterList = teacherChapterList;
    }
}
