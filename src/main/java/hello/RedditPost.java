package hello;

public class RedditPost {
    private String title;
    private String url;
    private String icon;

    public RedditPost(String title, String url) {
        this.title = title;
        this.url = setUrl(url);
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

    private String setUrl(String url) {
        if(url.contains("imgur") || url.contains("reddit") || url.contains("redd.it")) {
            return  url;
        }
        if(url.contains("youtube")) {
            return url.replace("youtube", "hooktube");
        }
        if(url.contains("youtu.be/")) {
            return url.replace("youtu.be/", "hooktube.com/watch?v=");
        }
        return "http://outline.com/" + url;
    }

}
