

package com.basilisk.backend.presenters;

import com.basilisk.Constants;
import com.basilisk.backend.models.Comment;
import com.basilisk.backend.models.Retweet;
import com.basilisk.backend.models.Tweet;
import com.basilisk.backend.models.User;
import com.basilisk.backend.services.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TweetPresenter {

    private UserService userService;
    private TweetService tweetService;
    private RetweetService retweetService;
    private FollowService followService;
    private CommentService commentService;

    private static Logger LOGGER = Logger.getLogger(ProfilePresenter.class);

    public TweetPresenter(UserService userService, TweetService tweetService, FollowService followService,
                          RetweetService retweetService, CommentService commentService) {
        this.followService = followService;
        this.userService = userService;
        this.tweetService = tweetService;
        this.retweetService = retweetService;
        this.commentService = commentService;
    }

    public boolean createAndSaveTweet(String tweetText, User currentUser) {
        if (!tweetText.isEmpty()) {
            tweetText = Constants.cleanString(tweetText);
            Tweet tweet = new Tweet(tweetText, currentUser);
            LOGGER.info("Tweet creation success: " + tweetText);
            tweetService.writeTweet(tweet);
            return true;
        } else {
            LOGGER.info("Tweet creation failure " + tweetText);
            return false;
        }
    }

    public boolean createAndSaveComment(String commentText, Tweet tweet, User currentUser) {
        if (!commentText.isEmpty()) {
            commentText = Constants.cleanString(commentText);
            Comment comment = new Comment(commentText, currentUser, tweet);
            LOGGER.info("Comment creation success: " + commentText);
            LOGGER.info("Comment: " + comment.getText() + comment.getUser().toString() + comment.getTweet().toString());
            commentService.writeComment(comment);
            return true;
        } else {
            LOGGER.info("Comment creation failure " + commentText);
            return false;
        }
    }

    public long createAndSaveRetweet(Tweet tweet, User currentUser) {
        Retweet retweet = new Retweet(currentUser, tweet);
        retweetService.postRetweet(retweet);
        LOGGER.info("Retweet creation success: " + tweet.getText() + " retweeted by @" + currentUser.getUsername());
        return retweet.getId();
    }

    public boolean deleteTweet(Tweet tweet) {
        List<Retweet> retweetList = retweetService.getAllRetweetsByTweet(tweet);
        List<Comment> commentList = commentService.getTweetComments(tweet);

        for (Retweet retweet : retweetList) {
            retweetService.removeRetweet(retweet);
        }
        for (Comment comment : commentList) {
            commentService.removeComment(comment);
        }
        tweetService.removeTweet(tweet);
        LOGGER.info("Tweet deletion success: " + tweet.getText() + " by @" + tweet.getUser().getUsername());
        return true;
    }

    public boolean deleteRetweet(Retweet retweet) {
        retweetService.removeRetweet(retweet);
        LOGGER.info("Retweet deletion success: " + retweet.getTweet().getText() + " retweeted by @" + retweet.getUser().getUsername());
        return true;
    }

    public List<Comment> getTweetComments(Tweet tweet) {
        return commentService.getTweetComments(tweet);
    }

    public Tweet likesTweet(User user, Tweet tweet) {
        LOGGER.info("User " + user.getUsername() + " likes tweet " + tweet.getId());
        tweet.getLikesList().add(user);
        tweetService.editTweet(tweet);

        return tweetService.getTweetById(tweet.getId());
    }

    public Tweet dislikesTweet(User user, Tweet tweet) {
        LOGGER.info("User " + user.getUsername() + " dislikes tweet " + tweet.getId());
        tweet.getDislikesList().add(user);
        tweetService.editTweet(tweet);

        return tweetService.getTweetById(tweet.getId());
    }

    public Tweet unlikesTweet(User user, Tweet tweet) {
        LOGGER.info("User " + user.getUsername() + " unlikes tweet " + tweet.getId());
        tweet.getLikesList().remove(user);
        tweetService.editTweet(tweet);

        return tweetService.getTweetById(tweet.getId());
    }

    public Tweet undislikesTweet(User user, Tweet tweet) {
        LOGGER.info("User " + user.getUsername() + " undislikes tweet " + tweet.getId());
        tweet.getDislikesList().remove(user);
        tweetService.editTweet(tweet);

        return tweetService.getTweetById(tweet.getId());
    }
}