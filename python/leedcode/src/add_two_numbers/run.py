'''
Created on Jul 14, 2025

@author: dchen
'''
import add_two_numbers.solution as solution
import add_two_numbers.list_node as list_node

def display(result):
    while result is not None:
        print(result.val, end=' ')
        result = result.next

num1 = [2, 4, 3]
num2 = [5, 6, 4]

l1 = None
for i in range(3):
    l1 = list_node.ListNode(num1[i], l1)
    
l2 = None
for i in range(3):
    l2 = list_node.ListNode(num2[i], l2)

s = solution.Solution()

result = s.addTwoNumbers(l1, l2)

display(result)