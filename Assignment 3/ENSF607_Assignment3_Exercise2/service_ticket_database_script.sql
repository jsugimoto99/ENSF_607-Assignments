CREATE DATABASE service_tickets;
USE service_tickets;
CREATE TABLE EventActivity
(
ID	INT unsigned NOT NULL AUTO_INCREMENT, # Primary Key of activity. Should auto increment.
ActivityName	VARCHAR(20) NOT NULL UNIQUE, # Activity Name
primary key	(ID)
);

CREATE TABLE EventOrigin
(
ID	INT unsigned NOT NULL AUTO_INCREMENT, # Primary Key of activity. Should auto increment.
Origin	VARCHAR(20) NOT NULL UNIQUE, # Name of the person who created ticket.
primary key	(ID)
);

CREATE TABLE EventStatus
(
ID	INT unsigned NOT NULL AUTO_INCREMENT, # Primary Key of activity. Should auto increment.
Status	VARCHAR(20) NOT NULL UNIQUE, # Status Description
primary key	(ID)
);

CREATE TABLE EventClass
(
ID	INT unsigned NOT NULL AUTO_INCREMENT, # Primary Key of activity. Should auto increment.
Class	VARCHAR(20) NOT NULL UNIQUE, # Class Description
primary key	(ID)
);

CREATE TABLE EventLog
(
ID	INT unsigned NOT NULL AUTO_INCREMENT, # Primary Key of activity. Should auto increment.
CaseId	VARCHAR(20) NOT NULL UNIQUE, # Unique Case Id. Prefixed with CS<number>.
Activity	VARCHAR(20) NOT NULL, # Activity from EventActivity Table.
Urgency	VARCHAR(1), # Urgency Value From Table.
Impact	VARCHAR(1), # Impact From Table.
Priority	VARCHAR(1), # Calculated priority from urgency and impact.
StartDate	DATE, # Date Ticket was created.
EndDate	Date, # Date Ticket was closed.
TicketStatus	VARCHAR(20) NOT NULL, # Ticket Status
UpdateDateTime	DATETIME, # Date/Timestamp of ticket record
Duration	INT, # Length of ticket time. Calculated between start date and end date.
Origin	VARCHAR(20) NOT NULL, # Person/Owner of ticket
Class	VARCHAR(20) NOT NULL, # Ticket Class from class table
PRIMARY KEY	(ID),
FOREIGN KEY (Activity) REFERENCES EventActivity(ActivityName),
FOREIGN KEY (TicketStatus) REFERENCES EventStatus(Status),
FOREIGN KEY (Origin) REFERENCES EventOrigin(Origin),
FOREIGN KEY (Class) REFERENCES EventClass(Class)
);