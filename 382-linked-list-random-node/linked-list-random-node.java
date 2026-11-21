import java.util.*;

class Solution {

    private ArrayList<Integer> list;
    private Random random;

    public Solution(ListNode head) {
        list = new ArrayList<>();
        random = new Random();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}