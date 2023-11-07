package LikeLion11th.YouQuiz_Project.domain.study.dto;

public class TeacherChapterDto {
    private Long chapter_id;
    private String title;
    private String youtube_link;

    public TeacherChapterDto() {
    }

    public TeacherChapterDto(Long chapter_id, String title, String youtube_link) {
        this.chapter_id = chapter_id;
        this.title = title;
        this.youtube_link = youtube_link;
    }

    public Long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }
}
