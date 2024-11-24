package second;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

            int s = Integer.parseInt(br.readLine());  // Number of test cases

            while (s-- > 0) {  // Process each test case
                int n = Integer.parseInt(br.readLine());  // Number of cities
                Map<String, Integer> mapCities = new HashMap<>();  // Map city names to indices
                List<City> cities = new LinkedList<>();  // List to store all cities

                // Read information for each city
                for (int i = 0; i < n; i++) {
                    String name = br.readLine();
                    mapCities.put(name, i);  // Map city name to its index
                    cities.add(new City(name));  // Add new city to the list

                    int p = Integer.parseInt(br.readLine());  // Number of neighbors
                    // Read information for each neighbor
                    for (int j = 0; j < p; j++) {
                        String[] connection = br.readLine().split(" ");
                        int neighborIndex = Integer.parseInt(connection[0]) - 1;  // Convert to 0-based index
                        int cost = Integer.parseInt(connection[1]);
                        cities.get(i).neighbors.put(neighborIndex, cost);  // Add neighbor and cost
                    }
                }

                int r = Integer.parseInt(br.readLine());  // Number of paths to find
                // Process each path query
                for (int i = 0; i < r; i++) {
                    String[] path = br.readLine().split(" ");
                    int start = mapCities.get(path[0]);  // Get index of start city
                    int end = mapCities.get(path[1]);  // Get index of end city
                    int cost = findMinCost(cities, start, end);  // Find minimum cost
                    pw.println(cost);  // Write result to output file
                }

                if (s > 0) br.readLine();  // Read empty line between test cases
            }

            br.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to find minimum cost between two cities using Dijkstra's algorithm
    static int findMinCost(List<City> cities, int start, int end) {
        int n = cities.size();
        int[] costs = new int[n];  // Array to store minimum cost to each city
        Arrays.fill(costs, Integer.MAX_VALUE);  // Initialize all costs to infinity
        costs[start] = 0;  // Cost to start city is 0

        // Priority queue to always process the city with lowest current cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});  // Add start city to queue

        while (!pq.isEmpty()) {
            int[] current = pq.poll();  // Get city with lowest cost
            int cityIndex = current[0];
            int currentCost = current[1];

            if (cityIndex == end) {  // If we've reached the destination
                return currentCost;  // Return the cost
            }

            if (currentCost > costs[cityIndex]) {  // If we've found a better path already
                continue;  // Skip this path
            }

            // Check all neighbors of the current city
            for (Map.Entry<Integer, Integer> neighbor : cities.get(cityIndex).neighbors.entrySet()) {
                int nextCity = neighbor.getKey();
                int newCost = currentCost + neighbor.getValue();

                if (newCost < costs[nextCity]) {  // If we've found a better path
                    costs[nextCity] = newCost;  // Update the cost
                    pq.offer(new int[]{nextCity, newCost});  // Add to queue for processing
                }
            }
        }

        return -1;  // If no path is found (shouldn't happen in this problem)
    }
}
