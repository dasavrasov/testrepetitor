package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.User;

import java.util.List;

public interface UserDao extends BasicDao<User> {

    /**
     * for finding users by name
     *
     * @param name user's name for search
     * @return list of users according this name
     */
    List<User> getUserByName(String name);

    /**
     * for finding user by login
     *
     * @param login user's unic login for search
     * @return unic user according this login
     */
    User getUserByLogin(String login);
}