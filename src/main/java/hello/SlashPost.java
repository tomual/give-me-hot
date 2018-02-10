package hello;

public class SlashPost {
    private String title;
    private String url;
    private String icon;

    public SlashPost(String title, String url) {
        this.title = title;
        this.url = url;
        this.icon = "question_answer";
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getIcon() {
        return icon;
    }
}
