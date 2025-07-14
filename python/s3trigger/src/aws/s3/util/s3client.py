'''
Created on Jul 11, 2025

@author: dchen
'''

import boto3
import botocore.exceptions

class S3Client:
    def __init__(self):
        try:
            self.s3_client = boto3.client('s3')
        except Exception as e:
            print(e)
            raise e       
        
    def list_buckets(self):
        try:
            response = self.s3_client.list_buckets()
        except botocore.exceptions.ClientError as ce:
            print(ce.response)
            raise ce 
        except Exception as e:
            print(e.response)
            raise e 
        
        # Output the bucket names
        print('Existing buckets:')
        names = []
        for bucket in response['Buckets']:
            name = bucket["Name"]
            names.append(name)
            print(f'  {name}')
        return names
    
    def get_list_objects(self, bucket, prefix=''):
        try:
            response = self.s3_client.list_objects_v2(Bucket = bucket, Prefix = prefix)
        except botocore.exceptions.ClientError as ce:
            print(ce.response)
            raise ce 
        except Exception as e:
            print(e.response)
            raise e 
        
        contents = response.get('Contents', [])
        names = []
        for content in contents:
            names.append(content['Key'])
            print(content['Key'])
        return names
    
    def get_head_object(self, bucket, key):
        try:
            waiter = self.s3_client.get_waiter('object_exists')
            waiter.wait(Bucket=bucket, Key=key)
            s3_head_object = self.s3_client.head_object(Bucket=bucket, Key=key)
        except botocore.exceptions.ClientError as ce:
            print(ce.response)
            raise ce 
        except Exception as e:
            print(e)
            print('Error getting object {} from bucket {}. make sure they exist '
                  'and your bucket is in the same region as this '
                  'function.'.format(key, bucket))
            raise e
        return s3_head_object;
    
    def download_file(self, bucket, key, file_path):
        try:
            with open(file_path, 'wb') as f:
                self.s3_client.download_fileobj(bucket, key, f)
        except botocore.exceptions.ClientError as ce:
            print(ce.response)
            raise ce 
        except Exception as e:
            print(e)
            print('Error download object {} from bucket {}. make sure they exist '
                  'and your bucket is in the same region as this '
                  'function.'.format(key, bucket))
            raise e
        
        
    def list_s3_folders(self, bucket, prefix=''):
        """
        Lists the top-level "folders" (common prefixes) within an S3 bucket 
        for a given prefix.
        """
        try:
            response = self.s3_client.list_objects_v2(Bucket=bucket, Prefix=prefix,
                                                  Delimiter='/'  # The key to identifying folders
                                                  )
        except botocore.exceptions.ClientError as ce:
            print(ce.response)
            raise ce 
        except Exception as e:
            print(e)
            print('Error to list object from bucket {}. make sure they exist.'.format(bucket))
            raise e                                                      
        
        folders = []
        if 'CommonPrefixes' in response:
            for common_prefix in response['CommonPrefixes']:
                folders.append(common_prefix['Prefix'])
        
        return folders
