package hello;

public class DepotPost {
    private String title;
    private String url;
    private String image;

    public DepotPost() {}

    public DepotPost(String title, String url, String image) {
        this.title = title + " News";
        this.url = "http://outline.com/" + url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
