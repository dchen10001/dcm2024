'''
Created on Jul 13, 2025

@author: dchen
'''
from pickle import TRUE

class FileUtility :
    """
    Utility class for file operations.
    """
    @staticmethod
    def read_file(file_path, func):
        """
        Reads a file and processes each line.

        Args:
            file_path (str): The path to the file to read.
        """
        try:
            with open(file_path, 'r') as file:
                for line in file:
                    if func(line.strip()):
                        return TRUE
                    
            return False  # Return False if no lines were processed
        except FileNotFoundError:
            print(f"Error: The file '{file_path}' was not found.")
        except Exception as e:
            print(f"An error occurred: {e}")
            
    @staticmethod
    def get_file_name(file_path):
        """
        Extracts the file name from a given file path.

        Args:
            file_path (str): The full path of the file.

        Returns:
            str: The name of the file without the directory path.
        """
        return file_path.split('/')[-1] if '/' in file_path else file_path.split('\\')[-1]