package tests;

// (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import socialNetwork.Twitface;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // the last two tests have the same results, so to make things shorter we
  // just create a static map for use in checking both of them
  static Map<String, List<String>> expectedFriends= new HashMap<>();

  static {  // static initialization block

    // to make things easy we use a map of lists to store the friends of
    // each user, where the key is the person and the value is a list of
    // their friends
    expectedFriends.put("Adelina", Arrays.asList("Felipe", "Zella",
                                                 "Lindsay", "Melba"));
    expectedFriends.put("Clark", Arrays.asList("Pearl", "Felipe", "Evan"));
    expectedFriends.put("Deborah", Arrays.asList("Vernie", "Felipe"));
    expectedFriends.put("Edward", Arrays.asList("Schuyler", "Miriam"));
    expectedFriends.put("Elizabeth", Arrays.asList("Valerie", "Zella",
                                                   "Leila", "Melba"));
    expectedFriends.put("Emmie", Arrays.asList("Phoebe", "Zella", "Leila",
                                               "Melba"));
    expectedFriends.put("Evan", Arrays.asList("Phoebe", "Clark", "Zella",
                                              "Philomena"));
    expectedFriends.put("Exie", Arrays.asList("Felipe"));
    expectedFriends.put("Felipe", Arrays.asList("Roger", "Deborah", "Clark",
                                                "Adelina", "Exie"));
    expectedFriends.put("Grady", Arrays.asList("Schuyler"));
    expectedFriends.put("Hilma", Arrays.asList("Valerie"));
    expectedFriends.put("Katie", Arrays.asList("Susan"));
    expectedFriends.put("Leila", Arrays.asList("Elizabeth", "Emmie"));
    expectedFriends.put("Lindsay", Arrays.asList("Susan", "Adelina"));
    expectedFriends.put("Madaline", Arrays.asList("Susan"));
    expectedFriends.put("Marvel", Arrays.asList("Valerie", "Schuyler"));
    expectedFriends.put("Maude", Arrays.asList("Roger"));
    expectedFriends.put("Melba", Arrays.asList("Roger", "Vernie", "Elizabeth",
                                               "Adelina", "Emmie"));
    expectedFriends.put("Miriam", Arrays.asList("Vernie", "Edward"));
    expectedFriends.put("Pearl", Arrays.asList("Clark"));
    expectedFriends.put("Philomena", Arrays.asList("Schuyler", "Evan"));
    expectedFriends.put("Phoebe", Arrays.asList("Emmie", "Evan"));
    expectedFriends.put("Roger", Arrays.asList("Felipe", "Maude", "Melba"));
    expectedFriends.put("Schuyler", Arrays.asList("Grady", "Philomena",
                                                  "Edward", "Marvel"));
    expectedFriends.put("Shelley", Arrays.asList("Susan"));
    expectedFriends.put("Susan", Arrays.asList("Madaline", "Katie", "Zella",
                                               "Lindsay", "Shelley"));
    expectedFriends.put("Valerie", Arrays.asList("Elizabeth", "Hilma",
                                                 "Marvel"));
    expectedFriends.put("Vernie", Arrays.asList("Deborah", "Miriam", "Melba"));
    expectedFriends.put("Zella", Arrays.asList("Elizabeth", "Susan", "Adelina",
                                               "Emmie", "Evan"));

  }

  // Tests that the getAllUsers() method returns the names of all of the
  // users of a social network that has users but no friend relationships.
  // Note that this test does not create or use any threads.
  @Test public void testPublic1() {
    Twitface socialNetwork= TestData.exampleSocialNetwork1();

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Doris", "Elmer",
                                                      "Ethel", "Franz",
                                                      "Gertrude", "Wallace")));
  }

  // Tests that the getFriends() method returns the names of all of the
  // friends of a user.  Note that this test does not create or use any
  // threads.
  @Test public void testPublic2() {
    Twitface socialNetwork= TestData.exampleSocialNetwork2();

    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Numbat"),
                                        Arrays.asList("Koala", "Lion",
                                                      "Penguin", "Quokka")));
  }

  // Tests the peopleYouMayKnow() method.  Note that this test does not
  // create or use any threads.
  @Test public void testPublic3() {
    Twitface socialNetwork= TestData.exampleSocialNetwork2();

    assertTrue(TestData.checkCollection(
      socialNetwork.peopleYouMayKnow("Walrus"),
      Arrays.asList("Koala", "Numbat")));
  }

  // Creates one thread, which reads one list of user data, which only
  // contains one user addition, just to ensure that one thread can be
  // created and manipulated correctly.
  @Test public void testPublic4() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends4.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Koala")));
  }

  // Creates one thread, which reads one list of user data, which contains
  // several user additions.  Theresults are checked by calling
  // getAllUsers().
  @Test public void testPublic5() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends5.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Doris", "Elmer",
                                                      "Ethel", "Franz",
                                                      "Gertrude", "Wallace")));
  }

  // Creates one thread, which reads one list of user data, which only
  // contains one friend relationship.  Note that creating the friend
  // relationship will add or create the two users in the process.  The
  // results are checked by calling getAllUsers() and getFriends().  This
  // also checks that creating a friendship works both ways (both users
  // become friends of each other).
  @Test public void testPublic6() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends6.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Aardvark",
                                                      "Platypus")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Aardvark"),
                                        Arrays.asList("Platypus")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Platypus"),
                                        Arrays.asList("Aardvark")));
  }

  // Creates one thread, which reads one list of user data, which contains
  // several user additions and several friend relationships.  The results
  // are checked by calling getAllUsers(), and getFriends() on some of the
  // users.
  @Test public void testPublic7() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends7-8.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Koala", "Lion",
                                                      "Meerkat", "Numbat",
                                                      "Otter", "Penguin",
                                                      "Quokka", "Walrus")));

    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Otter"),
                                        Arrays.asList("Penguin")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Koala"),
                                        Arrays.asList("Lion", "Numbat",
                                                      "Meerkat")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Numbat"),
                                        Arrays.asList("Koala", "Lion",
                                                      "Penguin", "Quokka")));
  }

  // Creates one thread, which reads one list of user data, which contains
  // several user additions and several friend relationships, and calls the
  // peopleYouMayKnow() method.
  @Test public void testPublic8() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends7-8.html"
    ));

    assertTrue(TestData.checkCollection(
      socialNetwork.peopleYouMayKnow("Walrus"),
      Arrays.asList("Koala", "Numbat")));
  }

  // Creates two threads, which each independently read the same list of
  // user data, which contains two user additions for the same person; the
  // duplicate addition, even when performed concurrently by two threads,
  // should have no effect.
  @Test public void testPublic9() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends9.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends9.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Koala")));
  }

  // Creates two threads, which each independently read the same list of
  // user data, which contains two friend relationships for the same two
  // people; the duplicate friend relationship, even when performed
  // concurrently by two threads, should have no effect.
  @Test public void testPublic10() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends10.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends10.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        Arrays.asList("Aardvark",
                                                      "Platypus")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Aardvark"),
                                        Arrays.asList("Platypus")));
    assertTrue(TestData.checkCollection(socialNetwork.getFriends("Platypus"),
                                        Arrays.asList("Aardvark")));
  }

  // Creates two threads, which each read a list of user data, which contain
  // many user additions for different people, and calls getAllUsers().
  @Test public void testPublic11() {
    Twitface socialNetwork= new Twitface();
    List<String> expected= Arrays.asList("Ada", "Addie", "Agnes", "Alice",
                                         "Alma", "Amanda", "Amelia", "Anna",
                                         "Annie", "Belle", "Bertha",
                                         "Bessie", "Blanche", "Caroline",
                                         "Carrie", "Catherine", "Charlotte",
                                         "Clara", "Cora", "Daisy", "Della",
                                         "Dora", "Edith", "Edna", "Effie",
                                         "Eliza", "Elizabeth", "Ella",
                                         "Ellen", "Elsie", "Emily", "Emma",
                                         "Ethel", "Etta", "Eva", "Fannie",
                                         "Flora", "Florence", "Frances",
                                         "Georgia", "Gertrude", "Grace",
                                         "Hannah", "Harriet", "Hattie",
                                         "Helen", "Ida", "Jane", "Jennie",
                                         "Jessie", "Josephine", "Julia",
                                         "Kate", "Katherine", "Katie",
                                         "Laura", "Lena", "Lillian",
                                         "Lillie", "Lizzie", "Lottie",
                                         "Louise", "Lucy", "Lula", "Lulu",
                                         "Lydia", "Mabel", "Mae", "Maggie",
                                         "Mamie", "Margaret", "Marie",
                                         "Martha", "Mary", "Mattie", "Maud",
                                         "Maude", "May", "Minnie", "Mollie",
                                         "Myrtle", "Nancy", "Nannie",
                                         "Nellie", "Nettie", "Nora",
                                         "Olive", "Pearl", "Rebecca",
                                         "Rosa", "Rose", "Ruth", "Sadie",
                                         "Sallie", "Sarah", "Stella",
                                         "Susan", "Susie", "Viola",
                                         "Virginia");

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends11a.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends11b.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        expected));
  }

  // Creates two threads, which each read a list of user data, which contain
  // many friend relationships for different people.
  @Test public void testPublic12() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends12a.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends12b.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        expectedFriends.keySet()));

    for (String user : expectedFriends.keySet())
      assertTrue(TestData.checkCollection(socialNetwork.getFriends(user),
                                          expectedFriends.get(user)));
  }

  // Creates ten threads, which each read a list of user data, which contain
  // several friend relationships for different people.
  @Test public void testPublic13() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.readFriendData(Arrays.asList(
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13a.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13b.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13c.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13d.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13e.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13f.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13g.html",
      "http://www.cs.umd.edu/class/spring2018/cmsc132-010XH/friends13h.html"
    ));

    assertTrue(TestData.checkCollection(socialNetwork.getAllUsers(),
                                        expectedFriends.keySet()));

    for (String user : expectedFriends.keySet())
      assertTrue(TestData.checkCollection(socialNetwork.getFriends(user),
                                          expectedFriends.get(user)));
  }

}
