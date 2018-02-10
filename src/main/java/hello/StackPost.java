package hello;

public class StackPost {
    private String title;
    private String url;
    private String icon;

    public StackPost(String title, String url) {
        this.title = title;
        this.url = url;
        this.icon = "code";
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
