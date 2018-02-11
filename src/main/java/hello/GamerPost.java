package hello;

public class GamerPost {
    private String title;
    private String url;
    private String icon;

    public GamerPost(String title, String url) {
        this.title = title;
        this.url = url;
        this.icon = "videogame_asset";
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
