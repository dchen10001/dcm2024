'''
Created on Jul 14, 2025

@author: dchen
'''

from add_two_numbers.list_node import ListNode

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        result = []
        carry = 0
        f1 = True
        f2 = True
        loop = 0
        while f1 is True or f2 is True :
            f1 = l1 is not None
            if f1:
                digit1 = l1.val
                l1 = l1.next
            else :
                digit1 = 0
            
            f2 = l2 is not None
            digit2 = 0
            if f2:
                digit2 = l2.val
                l2 = l2.next
            else :
                digit2 = 0

            if f1 or f2 or loop == 0:
                total = digit1 + digit2 + carry
                carry = total // 10            
                result.append(total % 10)
            loop += 1
        if carry > 0:
            result.append(carry)
                
        # Reverse the result linked list
        l = None
        for item in reversed(result):
            l = ListNode(item, l)
    
        return l