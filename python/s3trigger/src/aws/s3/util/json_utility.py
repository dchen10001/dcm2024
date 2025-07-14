'''
Created on Jul 11, 2025

@author: dchen
'''

import json

def load_json_file(file_path):
    """
    Loads a JSON file.

    Args:
        file_path (str): The path to the JSON file containing the sample event.

    Returns:
        dict: A dictionary representing the loaded event.
    """
    try:
        with open(file_path, 'r') as f:
            event_data = json.load(f)
        return event_data
    except Exception as e:
        print(e.response)
        raise e 
    
def dumps(event):
    """
    Converts an event to a JSON string.

    Args:
        event (dict): The event to convert.

    Returns:
        str: A JSON string representation of the event.
    """
    return json.dumps(event, indent=2)