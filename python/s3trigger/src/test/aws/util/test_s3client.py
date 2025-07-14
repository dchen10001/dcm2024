'''
Created on Jul 11, 2025

@author: dchen
'''

import unittest
from aws.s3.util.s3client import S3Client
import aws.s3.util.json_utility as json_utility

class TestS3Client(unittest.TestCase):
    def setUp(self):
        # Set up any required resources or configurations for the tests
        pass

    def test_s3_client_initialization(self):
        # Test if the S3 client initializes correctly
        client = S3Client()
        self.assertIsNotNone(client)

    def test_list_buckets(self):
        # Test if a specific S3 bucket exist
        client = S3Client()
        names = client.list_buckets()
        self.assertTrue(names)  # Adjust based on your test setup
        size = len(names)
        self.assertGreater(size, 1, 'no bucket')  # Adjust based on your test setup

    def test_get_list_objects(self):
        # Test if a specific S3 bucket exist
        client = S3Client()
        bucket_name = 'dev-wfm-dcm-aws-batch-output-trigger'
        names = client.get_list_objects(bucket_name, '')
        self.assertTrue(names)  # Adjust based on your test setup
        size = len(names)
        self.assertGreater(size, 1, 'no bucket')  # Adjust based on your test setup
        
    def test_list_s3_folders(self):
        # Test if a specific S3 bucket exist
        client = S3Client()
        bucket_name = 'dev-wfm-dcm-aws-batch-output-trigger'
        folders = client.list_s3_folders(bucket_name)
        self.assertTrue(folders)  # Adjust based on your test setup
        size = len(folders)
        self.assertEqual(size, 1, 'no bucket')  # Adjust based on your test setup
        folders = client.list_s3_folders(bucket_name, folders[0])  # Test with a specific folder])
        
    def test_get_object(self):
        client = S3Client()
        bucket_name = 'dev-wfm-dcm-aws-batch-input-trigger'
        key = 'input/CXONE_IntegrationTestTenant/saas-dcm-batch/Problem_20240101-01-01-01d55ea49e-76ff-44a7-badc-74c44dda2c5d'
        head_object = client.get_head_object(bucket_name, key)
        self.assertIsNotNone(head_object)
        print(head_object)
        
    def test_download_file(self):
        client = S3Client()
        bucket_name = 'dev-wfm-dcm-aws-batch-input-trigger'
        key = 'input/CXONE_IntegrationTestTenant/saas-dcm-batch/Problem_20240101-01-01-01d55ea49e-76ff-44a7-badc-74c44dda2c5d'
        file_path = '../../../../target/Problem_20240101-01-01-01d55ea49e-76ff-44a7-badc-74c44dda2c5d.json'
        client.download_file(bucket_name, key, file_path)
        problem = json_utility.load_json_file(file_path)
        print(problem)