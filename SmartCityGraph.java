import java.util.*;

public class SmartCityGraph {

    // To represent the graph  Agjancy List is used
    private Map<String, List<String>> adjList;
    public SmartCityGraph(){
        this.adjList = new HashMap<>();
    }

    //add locations
    public void addLocation(String location){
        if(!adjList.containsKey(location)){
            adjList.put(location, new ArrayList<>());
            System.out.println("Location" + location + "Successfully added");}
        else{System.out.println("location already exists..");}
    }

    //remove location
    public void removeLocation(String location){
        adjList.remove(location);
        System.out.println("Location" + location + "removed");
    
        for(List<String> connections : adjList.values()){
            connections.remove(location);
        }
    }

    //add roads(edges)

    public void addRoad(String source, String destination){
        if (adjList.containsKey(source) && adjList.containsKey(destination)){
             adjList.get(source).add(destination);
             adjList.get(destination).add(source);
             System.out.println("Road added between" + source + "and"+ destination + "successfullu");
        }else{
            System.out.println("Road does not exit");
        }   
    }
    public void removeRoad(String source, String destination){
        if(adjList.containsKey(source) && adjList.containsKey(destination)){
            adjList.get(source).remove(destination);
            adjList.get(destination).remove(source);
            System.out.println("Road remoed between" + source + "and" + destination + "..");
        }else{
            System.out.println("No road exists between these locations.");
        }
        
    }
    public void displayConnection(){
        System.out.println("\n--- Smart City Map Connections ----");
        for(Map.Entry<String, List<String>> entry : adjList.entrySet()){
            System.out.println(entry.getKey()+ "connects to -->"+ entry.getValue());

        }
    }

    //traversal using queue

    public void traverseBFS(String startLocation) {
        if (!adjList.containsKey(startLocation)) {
            System.out.println("Error: Start location not found.");
            return;
        }

        System.out.print("BFS Traversal Route (Queue-based): ");
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startLocation);
        queue.add(startLocation);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

}
