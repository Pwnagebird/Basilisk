package com.basilisk.backend.presenters;

import com.basilisk.backend.models.User;
import com.basilisk.backend.services.FriendListService;
import com.basilisk.backend.services.TweetService;
import com.basilisk.backend.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfilePresenter {

    private UserService userService;
    private TweetService tweetService;
    private FriendListService friendListService;
    private static Logger LOGGER = Logger.getLogger(ProfilePresenter.class);

    @Autowired
    public ProfilePresenter(UserService userService, TweetService tweetService, FriendListService friendLisService) {
        this.userService = userService;
        this.tweetService = tweetService;
        this.friendListService = friendLisService;
    }

    public void updateUser(User user) {
        LOGGER.info("Creating user: " + user.getUsername());
        userService.updateUser(user);
        LOGGER.info("User created: " + user.getUsername());
    }
}