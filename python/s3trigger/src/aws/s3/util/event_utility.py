'''
Created on Jul 10, 2025

@author: dchen
'''

import urllib

EVENT_RECORDS = 'Records'
EVENT_S3 = 's3'
EVENT_BUCKET = 'bucket'
EVENT_NAME = 'name'
EVENT_OBJECT = 'object'
EVENT_KEY = 'key'

class EventUtility:
    def __init__(self, event):
        self.event = event
    
    def get_bucket(self):
        """
        Extracts the bucket name from the S3 event.
        """
        return self.event[EVENT_RECORDS][0][EVENT_S3][EVENT_BUCKET][EVENT_NAME]

    def get_key(self):
        return urllib.parse.unquote_plus(self.event[EVENT_RECORDS][0][EVENT_S3][EVENT_OBJECT][EVENT_KEY])
