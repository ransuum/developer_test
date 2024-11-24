package second;

import java.util.HashMap;
import java.util.Map;

public class City {
    protected String name;
    protected Map<Integer, Integer> neighbors;

    public City(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
    }
}
