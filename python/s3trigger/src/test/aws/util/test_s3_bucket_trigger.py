'''
Created on Jul 10, 2025

@author: dchen
'''
import unittest
import aws.s3.util.event_utility as event_util
import aws.s3.util.json_utility as json_utility
import aws.s3.util.file_utility as file_util

def handler(line):
    """
    A simple handler function that prints the input line.
    
    Args:
        line (str): The input line to be printed.
    """
    print(line)
    if 'Status : SUCCESS' in line:
        return True
    return False
    
class TestHandler(unittest.TestCase):
    def setUp(self):
        self.event = json_utility.load_json_file("../../resources/event.json")
        self.util = event_util.EventUtility(self.event)
        
    def test_get_bucket(self):
        print("test_get_bucket")
        bucket = self.util.get_bucket()
        self.assertEqual(bucket, 'dev-wfm-dcm-aws-batch-input-trigger')
        
    def test_get_key(self):
        print("get_key")
        key = self.util.get_key()
        self.assertEqual(key, 'input/CXONE_PerformanceTestTenant/saas-dcm-simulation-batch/Problem_20240827-00-07-44simulation-DCMLT_2072-500Agents-10skills-15SKG-bullseye-constantVolume')
        direction, tenant, job_definition, file_name = key.split("/")
        self.assertEqual(direction, 'input')
        self.assertEqual(tenant, 'CXONE_PerformanceTestTenant')
        self.assertEqual(job_definition, 'saas-dcm-simulation-batch')
        self.assertEqual(file_name, 'Problem_20240827-00-07-44simulation-DCMLT_2072-500Agents-10skills-15SKG-bullseye-constantVolume')
    
    def test_read_file(self):
        print("test_read_file")
        file_path = "../../resources/ProcessReport.txt"
        result = file_util.FileUtility.read_file(file_path, handler)
        self.assertTrue(result)