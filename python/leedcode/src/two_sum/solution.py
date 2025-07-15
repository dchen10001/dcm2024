'''
Created on Jul 13, 2025

@author: dchen
'''

class Solution:

    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        size = len(nums)
        valueIndexMap = {}
        for idx in range(size):
            cur = nums[idx]
            x = target - cur
            if x in valueIndexMap:
                return [valueIndexMap[x], idx]
            valueIndexMap[cur] = idx
            
        return []