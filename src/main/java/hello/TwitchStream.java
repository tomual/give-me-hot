package hello;

public class TwitchStream {
    private String title;
    private String url;
    private String channel;
    private String icon;
    private String game;
    private int views;

    public TwitchStream(String title, String channel, String icon, String game, int views) {
        this.title = title;
        this.url = "https://www.twitch.tv/" + channel;
        this.channel = channel;
        this.icon = icon;
        this.game = game;
        this.views = views;
    }


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getGame() {
        return game;
    }

    public String getChannel() {
        return channel;
    }

    public String getIcon() {
        return icon;
    }

    public int getViews() {
        return views;
    }


}
