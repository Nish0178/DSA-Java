import java.util.*;

class RandomizedCollection {

    private ArrayList<Integer> list;
    private HashMap<Integer, HashSet<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean isNew = !map.containsKey(val);

        list.add(val);

        map.putIfAbsent(val, new HashSet<>());
        map.get(val).add(list.size() - 1);

        return isNew;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        int removeIndex = map.get(val).iterator().next();

        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        // Remove the selected index
        map.get(val).remove(removeIndex);

        // Only move last element if we are NOT removing the last element
        if (removeIndex != lastIndex) {

            list.set(removeIndex, lastValue);

            // Last value was previously at lastIndex
            map.get(lastValue).remove(lastIndex);

            // Now it is at removeIndex
            map.get(lastValue).add(removeIndex);
        }

        // Remove last element
        list.remove(lastIndex);

        // Remove value completely if no occurrence remains
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}