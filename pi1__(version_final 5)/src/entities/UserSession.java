/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ghofr
 */
public class UserSession {
    private static UserSession instance;
    private int userId; // User's ID

    // Private constructor to prevent direct instantiation
    public UserSession() {
    }

    // Get the UserSession instance (singleton pattern)
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Set the user's ID
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Get the user's ID
    public int getUserId() {
        return userId;
    }
}

    

