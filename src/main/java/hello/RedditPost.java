package hello;

public class RedditPost {
    private String title;
    private String url;
    private String icon;

    public RedditPost(String title, String url) {
        this.title = title;
        this.url = url;
        this.icon = setIcon(url);
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

    private String setIcon(String url) {

        if(url.contains("imgur") || url.contains("redd.it")) {
            return "image";
        }

        if(url.contains("reddit")) {
            return "comment";
        }

        if(url.contains("hooktube")) {
            return "videocam";
        }

        return "link";
    }

}
