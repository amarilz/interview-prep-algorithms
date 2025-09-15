package com.amarildo.structure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    public static ListNode createLinkedListFromString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // Converte il carattere in un intero.
                int digit = Character.getNumericValue(c);
                current.next = new ListNode(digit);
                current = current.next;
            } else {
                // Puoi gestire qui il caso in cui un carattere non è una cifra
            }
        }
        return dummyHead.next;
    }
}
