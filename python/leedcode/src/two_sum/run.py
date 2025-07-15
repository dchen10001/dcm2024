'''
Created on Jul 13, 2025

@author: dchen
'''
import two_sum.solution as solution

def display(result):
    if not result:
        print("No result found")
    else:
        print("Found: {}, {}".format(result[0], result[1]))
 
 
       
s = solution.Solution()

nums = [2, 7, 11, 15]
target = 9

for idx in range(5):
    print("Iteration: {}".format(nums[idx]))
    
result = s.twoSum(nums, target)

display(result)

display(result)