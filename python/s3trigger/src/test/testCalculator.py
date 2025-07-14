'''
Created on Jul 10, 2025

@author: dchen
'''

import unittest

class TestCalculator(unittest.TestCase):
    def setUp(self):
        self.a = 10
        self.b = 5

    def test_add(self):
        print("it is a test")# This method will run before each test
        result = self.a + self.b
        self.assertEqual(result, 15)

    def test_subtract(self):
        result = self.a - self.b
        self.assertEqual(result, 5)

    def test_multiply(self):
        result = self.a * self.b
        self.assertEqual(result, 50)

    def test_divide(self):
        result = self.a / self.b
        self.assertEqual(result, 2)