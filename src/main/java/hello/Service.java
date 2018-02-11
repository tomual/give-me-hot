package hello;

import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.TwitchClientBuilder;
import me.philippheuer.twitch4j.endpoints.StreamEndpoint;
import me.philippheuer.twitch4j.model.Game;
import me.philippheuer.twitch4j.model.Stream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private OkHttpClient client = new OkHttpClient();
    Response response;

    public List<TwitchStream> getTwitch() {
        List<TwitchStream> twitchStreams = new ArrayList<TwitchStream>();
        TwitchClient twitchClient = TwitchClientBuilder.init()
                .withClientId("")
                .withClientSecret("")
                .withAutoSaveConfiguration(true)
                .withConfigurationDirectory(new File("config"))
                .withCredential("") // Get your token at: https://twitchapps.com/tmi/
                .connect();

        StreamEndpoint streamEndpoint = twitchClient.getStreamEndpoint();
        Optional<Long> limit = Optional.ofNullable(null);
        Optional<Long> offset = Optional.ofNullable(null);
        Optional<String> language = Optional.ofNullable(null);
        Optional<String> channelIds = Optional.ofNullable(null);
        Optional<String> stream_type = Optional.ofNullable(null);

        List<Stream> streams = streamEndpoint.getAll(limit, offset, language, Optional.ofNullable(new Game()), channelIds, stream_type);
        for (int i = 0; i < streams.size() && i < 3; i++) {
            String game = streams.get(i).getChannel().getGame();
            int views = streams.get(i).getViewers();
            String channel = streams.get(i).getChannel().getName();
            String title = streams.get(i).getChannel().getStatus();
            String icon = streams.get(i).getChannel().getLogo();
            TwitchStream twitchStream = new TwitchStream(title, channel, icon, game, views);
            twitchStreams.add(twitchStream);
        }
        return twitchStreams;
    }

    public List<GamerPost> getGamer() {
        List<GamerPost> gamerPosts = new ArrayList<GamerPost>();
        String html = "https://thegamerspost.com/";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select("a.article-title");
            for (int i = 0; i < links.size() && i < 5; i++) {
                GamerPost gamerPost = new GamerPost(links.get(i).text(), links.get(i).attr("href"));
                gamerPosts.add(gamerPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gamerPosts;
    }

    public DepotPost getDepot() {
        DepotPost depotPost = new DepotPost();
        String html = "https://www.webdesignerdepot.com/category/news/";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select(".article-hp-content");
            for (int i = 0; i < links.size(); i++) {

                String title = links.get(i).select("a.anim-link").text();
                if(title.contains("Popular Design News of the Week")) {
                    title = title.replace("Popular Design News of the Week: ", "");
                    title = title.replaceAll(", 2018", "");
                    title = title.replaceAll("^.* - ", "");
                    depotPost = new DepotPost(title, links.get(i).select("a.anim-link").attr("href"), links.get(i).select("img").attr("src"));
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return depotPost;
    }

    public CodropsPost getCodrops() {
        CodropsPost codropsPost = new CodropsPost();
        String html = "https://tympanus.net/codrops/collective/";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select(".ct-latest-thumb a");
            codropsPost = new CodropsPost(links.get(0).attr("href"), links.get(0).select("img").attr("src"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codropsPost;
    }

    public List<SlashPost> getSlash() {
        List<SlashPost> slashPosts = new ArrayList<SlashPost>();
        String html = "https://slashdot.org/";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select("span.story-title > a");
            for (int i = 0; i < links.size() && i < 5; i++) {
                SlashPost slashPost = new SlashPost(links.get(i).text(), links.get(i).attr("href"));
                slashPosts.add(slashPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return slashPosts;
    }

    public List<StackPost> getStack() {
        List<StackPost> stackPosts = new ArrayList<StackPost>();
        String html = "https://stackoverflow.com/?tab=week";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select("div#question-mini-list h3 > a");
            System.out.println("headers");
            for (int i = 0; i < links.size() && i < 13; i++) {
                StackPost stackPost = new StackPost(links.get(i).text(), links.get(i).attr("href"));
                stackPosts.add(stackPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stackPosts;
    }

    public List<RedditPost> getReddit() {
        List<RedditPost> redditPosts = new ArrayList<RedditPost>();
        Request request = new Request.Builder()
                .url("https://www.reddit.com/r/all/hot/.json?count=20")
                .build();

        try {
            response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            JSONObject postsJson = json;
            JSONObject data = postsJson.getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length() && i < 13; i++) {
                String title = children.getJSONObject(i).getJSONObject("data").getString("title");
                String url = children.getJSONObject(i).getJSONObject("data").getString("url");
                RedditPost redditPost = new RedditPost(title, url);
                redditPosts.add(redditPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redditPosts;
    }
}
