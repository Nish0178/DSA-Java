import java.util.*;

class RandomizedSet {

    private ArrayList<Integer> list;
    private HashMap<Integer, Integer> map;
    private Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        // Store value at the end of the list
        list.add(val);

        // Store value -> index
        map.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);

        // Get the last element
        int lastValue = list.get(list.size() - 1);

        // Move last element to the position of val
        list.set(index, lastValue);

        // Update its index in the map
        map.put(lastValue, index);

        // Remove last element
        list.remove(list.size() - 1);

        // Remove val from map
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());

        return list.get(index);
    }
}