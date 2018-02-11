package hello;

public class CodropsPost {
    private String url;
    private String image;

    public CodropsPost() {};

    public CodropsPost(String url, String image) {
        this.url = url;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
