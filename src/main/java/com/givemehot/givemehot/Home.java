package com.givemehot.givemehot;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "")
public class Home extends UI {

    @Autowired
    private Service service;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        System.out.println("init");
        JSONObject postsJson = service.getHot();

        System.out.println(postsJson.get("data"));

        JSONObject data = postsJson.getJSONObject("data");
        JSONArray children = data.getJSONArray("children");
        System.out.println(children.get(1));

        for (int i = 0; i < children.length(); i++) {
            String title = children.getJSONObject(i).getJSONObject("data").getString("title");
            String url = children.getJSONObject(i).getJSONObject("data").getString("url");
            System.out.println(title);
            Link link = new Link(title, new ExternalResource(getUrl(url)));
            link.setTargetName("_blank");
            link.setIcon(getIcon(getUrl(url)));
            layout.addComponent(link);
        }

        Label label = new Label();
        label.setValue("Hello World");
        layout.addComponent(label);
        setContent(layout);

    }

    String getUrl(String url) {
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

    VaadinIcons getIcon(String url) {

        if(url.contains("imgur") || url.contains("redd.it")) {
            return VaadinIcons.PICTURE;
        }
        if(url.contains("reddit")) {
            return VaadinIcons.COMMENT;
        }
        if(url.contains("hooktube")) {
            return VaadinIcons.FILM;
        }
        return VaadinIcons.LINK;
    }
}