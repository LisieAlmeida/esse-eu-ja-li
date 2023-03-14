package com.capgemini;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.Entity;


public class Ranking {
    private List<User> users;
    private List<User> topUsers;

    public Ranking() {
        this.users = new ArrayList<>();
        this.topUsers = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        updateRanking();
    }

    private void updateRanking() {
        users.sort(Comparator.comparingInt(User::getPoints).reversed());
        if (users.size() > 10) {
            topUsers = users.subList(0, 10);
        }
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRankingPosition(i + 1);
        }
    }

    public List<User> getTopUsers() {
        return topUsers;
        
    }
    
    public List<User> getUsers() {
    	return users;
    	
    }
    
   
}
