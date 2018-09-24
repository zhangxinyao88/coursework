package tests;

// (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

// This class contains a helper method used in the public tests, and example
// social networks that the tests call methods on.

import socialNetwork.Twitface;
import java.util.Collection;

public class TestData {

  // In various tests we have to check the contents of a Collection returned
  // by a method, but we can't create a Collection that has the expected
  // values and use the equals() method to compare against the Collection,
  // because we don't even know what kind of Collection the methods will
  // return.  This method takes a Collection to check and another Collection
  // with the expected values (this Collection will actually be some type of
  // List).  Then it uses the Collection containsAll() method to compare the
  // two parameter Collections.  If we have two collections A and B, and A
  // contains all of the elements of B, and B contains all of the elements
  // of A, then it must be the case that they must have all the same
  // elements, and only the same elements.  Of course this is not
  // particularly efficient, but our goal is just to make it easy to check
  // the results of tests.
  public static <T> boolean checkCollection(Collection<T> collection,
                                            Collection<T> expectedResults) {
    return collection.containsAll(expectedResults) &&
           expectedResults.containsAll(collection);
  }

  // example social networks ////////////////////////////////////////////

  // creates a new Twitface object with several users, but no friendships
  public static Twitface exampleSocialNetwork1() {
    Twitface socialNetwork= new Twitface();
    String[] users= new String[]{"Ethel", "Franz", "Gertrude", "Wallace",
                                 "Elmer", "Doris"};

    for (String user : users)
      socialNetwork.addUser(user);

    return socialNetwork;
  }

  // creates a new Twitface object with several users and several
  // friendships; note that adding the friendships will add the users also
  public static Twitface exampleSocialNetwork2() {
    Twitface socialNetwork= new Twitface();

    socialNetwork.friend("Koala", "Lion");
    socialNetwork.friend("Koala", "Meerkat");
    socialNetwork.friend("Koala", "Numbat");
    socialNetwork.friend("Lion", "Numbat");
    socialNetwork.friend("Meerkat", "Walrus");
    socialNetwork.friend("Numbat", "Penguin");
    socialNetwork.friend("Numbat", "Quokka");
    socialNetwork.friend("Penguin", "Otter");
    socialNetwork.friend("Quokka", "Walrus");

    return socialNetwork;
  }

}
