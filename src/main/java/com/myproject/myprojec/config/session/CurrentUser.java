package com.myproject.myprojec.config.session;


import com.myproject.myprojec.persistence.entity.UserEntity;

public class CurrentUser {

    private static final long serialVersionUID = 1L;
    private SessionUser user;

    public CurrentUser(UserEntity user) {
        this.user = SessionUser.mapUserToSessionUser(user);
    }

    public SessionUser getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "CurrentUser{" + "user=" + user + "} " + super.toString();
    }
}
