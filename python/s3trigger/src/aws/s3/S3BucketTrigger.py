'''
Created on Jul 10, 2025

@author: dchen
'''

import datetime
import json
import os
import re
import urllib

import boto3

def handler(event, context):
    s = json.dumps(event, indent=2)
    print('s3 input trigger!!!!!!: {}'.format(s))
    bucket = event['Records'][0]['s3']['bucket']['name']
    key = urllib.parse.unquote_plus(event['Records'][0]['s3']['object']['key'])
 
    try:
        aws_s3 = boto3.client('s3')
        waiter = aws_s3.get_waiter('object_exists')
        waiter.wait(Bucket=bucket, Key=key)
        s3_head_object = aws_s3.head_object(Bucket=bucket, Key=key)
    except Exception as e:
        print('Error getting object {} from bucket {}. make sure they exist '
              'and your bucket is in the same region as this '
              'function.'.format(key, bucket))
        raise e
    
    return s3_head_object