package com.basilisk.backend.presenters;

import com.basilisk.backend.models.Tweet;
import com.basilisk.backend.models.User;
import com.basilisk.backend.services.FriendListService;
import com.basilisk.backend.services.TweetService;
import com.basilisk.backend.services.UserService;
import com.basilisk.frontend.components.TweetDisplayComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProfilePresenter {

    private UserService userService;
    private TweetService tweetService;
    private FriendListService friendListService;
    private TweetDisplayPresenter tweetDisplayPresenter;
    private static Logger LOGGER = Logger.getLogger(ProfilePresenter.class);

    @Autowired
    public ProfilePresenter(UserService userService, TweetService tweetService, FriendListService friendLisService, TweetDisplayPresenter tweetDisplayPresenter) {
        this.userService = userService;
        this.tweetService = tweetService;
        this.friendListService = friendLisService;
        this.tweetDisplayPresenter = tweetDisplayPresenter;
    }

    public List<TweetDisplayComponent> getAllUserTweets(User user) {
        List<Tweet> tweetList = tweetService.retrieveAllTweets(user);
        List<TweetDisplayComponent> tweetComponentDisplayList = new LinkedList<>();

        for (Tweet tweet : tweetList) {
            TweetDisplayComponent tweetComponentDisplay = new TweetDisplayComponent(tweetDisplayPresenter);
            tweetComponentDisplay.setTweet(tweet);
            tweetComponentDisplayList.add(tweetComponentDisplay);
        }
        return tweetComponentDisplayList;
    }
}