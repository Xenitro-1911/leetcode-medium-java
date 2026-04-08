package problems.p002_add_two_numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Helper methods ---

    private ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) { size++; curr = curr.next; }
        int[] arr = new int[size];
        curr = head;
        for (int i = 0; i < size; i++) { arr[i] = curr.val; curr = curr.next; }
        return arr;
    }

    // --- Valid cases ---

    @Test
    void testExample_342plus465() {
        // 342 + 465 = 807 → [7,0,8]
        ListNode l1 = buildList(2, 4, 3);
        ListNode l2 = buildList(5, 6, 4);
        assertArrayEquals(new int[]{7, 0, 8}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testSingleDigit_noCarry() {
        // 3 + 4 = 7
        ListNode l1 = buildList(3);
        ListNode l2 = buildList(4);
        assertArrayEquals(new int[]{7}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testSingleDigit_withCarry() {
        // 5 + 5 = 10 → [0,1]
        ListNode l1 = buildList(5);
        ListNode l2 = buildList(5);
        assertArrayEquals(new int[]{0, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testCarryPropagates() {
        // 999 + 1 = 1000 → [0,0,0,1]
        ListNode l1 = buildList(9, 9, 9);
        ListNode l2 = buildList(1);
        assertArrayEquals(new int[]{0, 0, 0, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testUnequalLengths_l1Longer() {
        // 9901 + 9 = 9910 → [0,1,9,9] wait: [9,0,9,9] = 9909?
        // l1=[9,0,9,9]=9909, l2=[9]=9 → 9918 → [8,1,9,9]
        ListNode l1 = buildList(9, 0, 9, 9);
        ListNode l2 = buildList(9);
        assertArrayEquals(new int[]{8, 1, 9, 9}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testUnequalLengths_l2Longer() {
        // l1=[1], l2=[9,9,9] → 1 + 999 = 1000 → [0,0,0,1]
        ListNode l1 = buildList(1);
        ListNode l2 = buildList(9, 9, 9);
        assertArrayEquals(new int[]{0, 0, 0, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testLargeInput_noOverflow() {
        // [1,9,9,9,9,9,9,9,9,9] + [9] → [0,0,0,0,0,0,0,0,0,2]
        ListNode l1 = buildList(1, 9, 9, 9, 9, 9, 9, 9, 9, 9);
        ListNode l2 = buildList(9);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testMultiDigit_carryInMiddle() {
        // [9,9] + [1] = 100 → [0,0,1]
        ListNode l1 = buildList(9, 9);
        ListNode l2 = buildList(1);
        assertArrayEquals(new int[]{0, 0, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    // --- Edge cases ---

    @Test
    void testZeroPlusZero() {
        // 0 + 0 = 0 → [0]
        ListNode l1 = buildList(0);
        ListNode l2 = buildList(0);
        assertArrayEquals(new int[]{0}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testZeroPlusNonZero() {
        // 0 + 123 → [3,2,1]
        ListNode l1 = buildList(0);
        ListNode l2 = buildList(3, 2, 1);
        assertArrayEquals(new int[]{3, 2, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testResultLongerThanBothInputs() {
        // [9] + [9] = 18 → [8,1]
        ListNode l1 = buildList(9);
        ListNode l2 = buildList(9);
        assertArrayEquals(new int[]{8, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testAllNines() {
        // 999 + 999 = 1998 → [8,9,9,1]
        ListNode l1 = buildList(9, 9, 9);
        ListNode l2 = buildList(9, 9, 9);
        assertArrayEquals(new int[]{8, 9, 9, 1}, toArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    void testOutputIsNewList() {
        // Result should be a separate list, not l1 or l2
        ListNode l1 = buildList(1, 2);
        ListNode l2 = buildList(3, 4);
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertNotSame(l1, result);
        assertNotSame(l2, result);
    }
}