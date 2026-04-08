package problems.p002_add_two_numbers;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode x = head;
        int sum=0;

        while (l1 != null && l2 != null) {
            sum = (l1.val+l2.val)+sum;
            head.next = new ListNode(sum%10);
            head = head.next;
            sum = sum/10;
            l1=l1.next;
            l2=l2.next;
        }
        while(l1 != null){
            sum = l1.val + sum;
            head.next = new ListNode(sum % 10);
            head = head.next;
            sum = sum / 10;
            l1 = l1.next;
        }
        while(l2 != null){
            sum = l2.val + sum;
            head.next = new ListNode(sum % 10);
            head = head.next;
            sum = sum / 10;
            l2 = l2.next;
        }
        if(sum==1){
            head.next=new ListNode(1);
            head = head.next;
        }
        return x.next;
    }
}