package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class Graph<V> {
   class Edge{
      V value;
      public Edge(V v) {
         value = v;
      }
      public boolean equals(Edge e) { //override equals method
         return (e.value.equals(value));
      }

      public V getV() {
         return value;
      }
   }

   Map<V, ArrayList<Edge>> G;
   public Graph() {
      G = new HashMap<V, ArrayList<Edge>>();
   }

   //add a vertex into the graph
  public boolean addVertexToGraph(V vertexToAdd)
  throws IllegalArgumentException {
     if (vertexToAdd == null)  //check if vertex is null  
        throw new IllegalArgumentException();
     if (G.containsKey(vertexToAdd)) //check if vertex already exists
        return false;
     else {
        G.put(vertexToAdd, new ArrayList<Edge>());
        return true;
     }
  }

  //check if vertex is contained in the graph
  public boolean isVertex(V vertexToCheck) throws IllegalArgumentException {
     if (vertexToCheck == null)  //check if vertex is null  
        throw new IllegalArgumentException();
     return G.containsKey(vertexToCheck);
  }

  //get all the vertices of the graph
  public Collection<V> getVertices() {
     return new HashSet<V>(G.keySet());
  }

  //to add an edge to the graph
  public boolean addEdgeToEachOther(V source, V dest) 
        throws IllegalArgumentException {
     if (source == null || dest == null)  //check if vertex is null  
        throw new IllegalArgumentException();     
     if (source.equals(dest)) //if it's the same person
        return false;
     addVertexToGraph(source);
     addVertexToGraph(dest); //make sure the two vertices are contained
     Edge SourceEdge = new Edge(dest);
     Edge DestEdge = new Edge(source);
     G.get(source).add(SourceEdge); //add the edge from source to dest
     G.get(dest).add(DestEdge); // add the edge from dest to source
     return true;
  }

//remove all the edges from one vertex to another with exact weight
  public boolean removeEdge(V source, V dest) 
        throws IllegalArgumentException {
     if (source == null || dest == null)  //check if vertex is null  
        throw new IllegalArgumentException();
     if (!G.containsKey(source) || !G.containsKey(dest))
        //see if source and dest are in the graph 
        return false;
    
     Edge SourceEdge = new Edge(dest);
     Edge DestEdge = new Edge(source);
     return G.get(dest).remove(DestEdge) && G.get(source).remove(SourceEdge);
  }

  //return a collection of all vertices with same source vertex
  public Collection<V> neighborsOfVertex(V sourceVertex)  
        throws IllegalArgumentException {
     if (sourceVertex == null)  //check if vertex is null  
        throw new IllegalArgumentException();
     Collection<V> Nb = new ArrayList<V>();
     if (!G.containsKey(sourceVertex))
        return Nb;
     for (Edge p : G.get(sourceVertex)) {
        Nb.add(p.getV());
     }
     return Nb;
  }
  
  public Collection<V> getNeighborsNeighbor(V Vertex){
     Collection<V> Nb = new ArrayList<V>();
     for (Edge V : G.get(Vertex)) {
        ArrayList<V> friends = (ArrayList<V>) neighborsOfVertex(V.getV());
        for (V v : friends) {
           if (!G.get(Vertex).contains(v) && !v.equals(Vertex))
              Nb.add(v);
        }
     }
     return Nb;
  }

}
