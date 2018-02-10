package hello;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private OkHttpClient client = new OkHttpClient();
    Response response;

    public List<SlashPost> getSlash() {

        List<SlashPost> slashPosts = new ArrayList<SlashPost>();
        String html = "https://slashdot.org/";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements links = doc.select("span.story-title > a");
            System.out.println("headers");
            for (int i = 0; i < links.size(); i++) {
                SlashPost slashPost = new SlashPost(links.get(i).text(), links.get(i).attr("href"));
                slashPosts.add(slashPost);
            }
            System.out.println();

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
            for (int i = 0; i < links.size(); i++) {
                StackPost stackPost = new StackPost(links.get(i).text(), links.get(i).attr("href"));
                stackPosts.add(stackPost);
            }
            System.out.println();

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

            for (int i = 0; i < children.length(); i++) {
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
