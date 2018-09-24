package socialNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Twitface {
   Graph<String> G = new Graph<String>();

  public synchronized boolean addUser(String name) {
    return G.addVertexToGraph(name);
  }

  public Collection<String> getAllUsers() {
    return G.getVertices();
  }

  public synchronized boolean friend(String user1, String user2) {
    return G.addEdgeToEachOther(user1, user2);
  }

  public Collection<String> getFriends(String user) {
    return G.neighborsOfVertex(user);
  }

  public boolean unfriend(String user1, String user2) {
    return G.removeEdge(user1, user2);
  }

  public Collection<String> peopleYouMayKnow(String user) {
    return G.getNeighborsNeighbor(user);
  }

  public void readFriendData(List<String> urls) {
     ArrayList<Thread> UrlArrayList = new ArrayList<Thread>();
     for (String p : urls) {
        try {
        URL U = new URL(p);
        URLConnection Ur = U.openConnection();
        UrlArrayList.add(new Thread(new Runner(Ur)));
        }
        catch(Exception e)
        {
           
        }
     }
     for (Thread q : UrlArrayList) {
        q.start();
     }
     for (Thread r : UrlArrayList) {
        try {
        r.join();
        }
        catch(Exception e) {
           
        }
     }
  }

 public class Runner implements Runnable{
     URLConnection local;
     public Runner(URLConnection url) {
        local = url;
     }
     public void run() {
        try {
         BufferedReader br = new BufferedReader(new InputStreamReader
                 (local.getInputStream()));
         String i;
         while ((i = br.readLine()) != null && !i.equals("<body>")) 
         {
            
         }
         while ((i = br.readLine()) != null && !i.equals("</body>")) {
            String[] words = i.split(" ");
            if (words[0].equals("addperson")) {
               addUser(words[1]);
            }
            else if (words[0].equals("addfriend")) {
               friend(words[1],words[2]);
            }
         }
      } catch (Exception e) 
        {
         System.out.println(e);
     }
      
     }
  }
}
