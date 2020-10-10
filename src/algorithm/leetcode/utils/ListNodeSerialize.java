package algorithm.leetcode.utils;

public class ListNodeSerialize {

    public static String serialize(ListNode head) {
        if (head == null) return "[]";

        ListNode foo = head;
        StringBuffer buf = new StringBuffer("[");
        while (foo != null) {
            buf.append(foo.val).append(",");
            foo = foo.next;
        }

        return buf.deleteCharAt(buf.length() - 1).append("]").toString();
    }

}
