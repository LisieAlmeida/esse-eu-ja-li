package com.capgemini;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RankingTests {
	
	@Test
	public void testUpdateRankingWith11Users() {
	    Ranking ranking = new Ranking();
	    User user1 = new User("Alice Borba", "alice@gmail.com", "1234566");
	    user1.setPoints(10);
	    ranking.addUser(user1);

	    User user2 = new User("Roberto Braga", "roberto@gmail.com", "154878");
	    user2.setPoints(20);
	    ranking.addUser(user2);

	    User user3 = new User("Maria Clara", "maria@gmail.com", "548484");
	    user3.setPoints(30);
	    ranking.addUser(user3);

	    User user4 = new User("Davi Silva", "davi@gmail.com", "5648746");
	    user4.setPoints(40);
	    ranking.addUser(user4);

	    User user5 = new User("Evandro Cardoso", "evandro@gmail.com", "5544841");
	    user5.setPoints(50);
	    ranking.addUser(user5);

	    User user6 = new User("Francisco Santos", "francisco@gmail.com", "6549894");
	    user6.setPoints(60);
	    ranking.addUser(user6);

	    User user7 = new User("Graciela Matos", "graciela@gmail.com", "54878");
	    user7.setPoints(70);
	    ranking.addUser(user7);

	    User user8 = new User("Rafael Cordeiro", "rafael@gmail.com", "6966488");
	    user8.setPoints(80);
	    ranking.addUser(user8);

	    User user9 = new User("Isabel Rocha", "isabel@gmail.com", "6458977");
	    user9.setPoints(90);
	    ranking.addUser(user9);

	    User user10 = new User("Jaqueline Maria", "jack@gmail.com", "56857897");
	    user10.setPoints(100);
	    ranking.addUser(user10);

	    User user11 = new User("Kelly Carvalho", "kelly@gmail.com", "2659889");
	    user11.setPoints(110);
	    ranking.addUser(user11);

	    List<User> topUsers = ranking.getTopUsers();
	    assertEquals(10, topUsers.size());

	    // check the position of a specific user
	    List<User> users = ranking.getUsers();
	    assertEquals(10, users.indexOf(user1));

	    // check the order of users in the ranking
	    assertEquals(user11, topUsers.get(0));
	    assertEquals(user10, topUsers.get(1));
	    assertEquals(user9, topUsers.get(2));
	    assertEquals(user8, topUsers.get(3));
	    assertEquals(user7, topUsers.get(4));
	    assertEquals(user6, topUsers.get(5));
	    assertEquals(user5, topUsers.get(6));
	    assertEquals(user4, topUsers.get(7));
	    assertEquals(user3, topUsers.get(8));
	    assertEquals(user2, topUsers.get(9));
	}

}
