"""
ENSF 607 Assignment 3 Exercise 2

Author: Shad Sajid

Development Date: October 17, 2023

Description: This program randomly generates a certain number of tickets from a period of time as defined by the user.
This data is inserted into a local MySQL database.
"""

import mysql.connector
import os
import random

from dotenv import load_dotenv
from datetime import date, datetime, timedelta

class TicketGenerator:
    def __init__(self):
        # Initialize the database name and table names
        self.db_name = "SERVICE_TICKETS"
        self.tableNames = ['eventactivity', 'eventorigin', 'eventstatus', 'eventclass']

    def get_user_inputs(self):
        while True:
            try:
                # Prompt the user for the number of tickets to generate
                self.number_tickets = int(input("Enter the number of tickets to generate: "))
                break  # If number of tickets is successfully parsed, exit the loop
            except ValueError:
                # Re-prompts the user for the correct input format
                print("Invalid input for the number of tickets. Please enter a valid integer.")

        while True:
            start_date_str = input("Enter the start date (YYYY-MM-DD): ")
            end_date_str = input("Enter the end date (YYYY-MM-DD): ")

            try:
                # Attempt to parse start_date and end_date
                self.start_date = date.fromisoformat(start_date_str)
                self.end_date = date.fromisoformat(end_date_str)
                self.delta = self.end_date - self.start_date
                break  # If parsing is successful, exit the loop
            except ValueError:
                # Re-prompts the user for the correct input format
                print("Invalid date format. Please enter dates in the format YYYY-MM-DD.")


    def db_connector(self):
        try:
            # Connects to the MySQL database using environment variables
            load_dotenv()

            db = mysql.connector.connect(
            host=os.getenv("MYSQL_HOST"),
            user=os.getenv("MYSQL_USER"),
            password=os.getenv("MYSQL_PASSWORD")
            )

            print("Successful Connection to MySQL:", db)
            return db
        except mysql.connector.Error as e:
            # Displays the appropriate error if the connection is unsuccessful
            print("Connection Error: {}".format(e))
            return None
    
    def calculate_priority(self, urgency, impact):
        # A helper function to calculate the priority based on the input urgency and impact
        if urgency == 1 and impact == 1:
            return 1
        elif (urgency == 2 and impact == 1) or (urgency == 1 and impact == 2):
            return 2
        elif (urgency == 3 and impact == 1) or (urgency == 1 and impact == 3) or (urgency == 2 and impact == 2):
            return 3
        elif (urgency == 3 and impact == 2) or (urgency == 2 and impact == 3):
            return 4
        else:
            return 5

    def calculate_days(self):
        # Calculates a random start and end date
        start_date = self.start_date + timedelta(days=random.randint(0, self.delta.days))
        remaining_days = (self.end_date - start_date).days

        # This condition checks to see if start date occurs before end date.
        # If the number of remaining days is 0, set the end date to the start date
        if remaining_days <= 0:
            end_date = start_date
        else:
            end_date = start_date + timedelta(days=random.randint(0, remaining_days))

        return (start_date, end_date)

    def get_entries(self, db, tableName):
        # Used to lookup values from the MySQL tables inside the database
        
        if db is None:
            return
        
        cursor = db.cursor()
        query = f"SELECT * FROM {self.db_name}.{tableName}"
        cursor.execute(query)
        result = cursor.fetchall()
        cursor.close()

        return result

    def generate_tickets(self, db):
        if db is None:
            return
        
        cursor = db.cursor()
        
        # Generates tickets and adds them into the MySQL database
        for i in range(1, self.number_tickets + 1):
            caseID = "CS_" + str(i)
            activity = random.choice(self.get_entries(db, 'eventactivity'))[1] # Get a random activity
            urgency = str(random.randint(1, 3))
            impact = str(random.randint(1, 3))
            priority = self.calculate_priority(int(urgency), int(impact)) # Uses the helper function to determine priority
            start_date, end_date =  self.calculate_days()
            update_datetime = datetime.now().strftime('%Y-%m-%d %H:%M:%S') # Get system time
            ticket_status = random.choice(self.get_entries(db, 'eventstatus'))[1] # Get a random status

            origin = random.choice(self.get_entries(db, 'eventorigin'))[1] # Get a random origin
            ticket_class = random.choice(self.get_entries(db, 'eventclass'))[1] # Get a random class
            duration = (end_date - start_date).days
            
            query = f"""
            INSERT INTO {self.db_name}.EventLog (CaseId, Activity, Urgency, Impact, Priority, StartDate, EndDate, TicketStatus, UpdateDateTime, Origin, Class, Duration)
            VALUES ('{caseID}', '{activity}', '{urgency}', '{impact}', '{priority}', '{start_date}', '{end_date}', '{ticket_status}', '{update_datetime}', '{origin}', '{ticket_class}', {duration})
            """
            cursor.execute(query)

        db.commit()
        cursor.close()

    def main(self):
        self.get_user_inputs() # Get user inputs
        db = self.db_connector() # Set database connection

        if db:
            self.generate_tickets(db) # Generate tickets and add them into MySQL
            print(f"Successfully inserted {self.number_tickets} randomly generated tickets from {self.start_date} to {self.end_date} into the database.")

if __name__ == "__main__":
    TicketGenerator().main()