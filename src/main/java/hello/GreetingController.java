package hello;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private Service service;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        DepotPost depotPost = service.getDepot();
        CodropsPost codropsPost = service.getCodrops();
        List<RedditPost> redditPosts = service.getReddit();
        List<StackPost> stackPosts = service.getStack();
        List<SlashPost> slashPosts = service.getSlash();
        List<GamerPost> gamerPosts = service.getGamer();
        List<TwitchStream> twitchStreams = service.getTwitch();

        model.addAttribute("name", name);
        model.addAttribute("codropsPost", codropsPost);
        model.addAttribute("depotPost", depotPost);
        model.addAttribute("redditPosts", redditPosts);
        model.addAttribute("stackPosts", stackPosts);
        model.addAttribute("slashPosts", slashPosts);
        model.addAttribute("gamerPosts", gamerPosts);
        model.addAttribute("twitchStreams", twitchStreams);
        return "greeting";


    }
}