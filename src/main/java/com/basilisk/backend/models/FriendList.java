package com.basilisk.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class FriendList {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int numberOfFriends;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "USER")
    private User user;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "FRIENDS")
    private List<User> friendList;

    public FriendList() {
    }

    public FriendList(int numberOfFriends, User user, List<User> friendList) {
        this.numberOfFriends = numberOfFriends;
        this.user = user;
        this.friendList = friendList;
    }

    public long getId() {
        return id;
    }

    public int getNumberOfFriends() {
        return numberOfFriends;
    }

    public User getUser() {
        return user;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setNumberOfFriends(int numberOfFriends) {
        this.numberOfFriends = numberOfFriends;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }
}