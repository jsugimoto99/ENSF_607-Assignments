USE SERVICE_TICKETS;

-- Insert data into the 'eventactivity' table
INSERT INTO SERVICE_TICKETS.eventactivity (ActivityName) VALUES ('Design');
INSERT INTO SERVICE_TICKETS.eventactivity (ActivityName) VALUES ('Construction');
INSERT INTO SERVICE_TICKETS.eventactivity (ActivityName) VALUES ('Test');
INSERT INTO SERVICE_TICKETS.eventactivity (ActivityName) VALUES ('Password Reset');

-- Insert data into the 'eventorigin' table
INSERT INTO SERVICE_TICKETS.eventorigin (Origin) VALUES ('Joe S.');
INSERT INTO SERVICE_TICKETS.eventorigin (Origin) VALUES ('Bill B.');
INSERT INTO SERVICE_TICKETS.eventorigin (Origin) VALUES ('George E.');
INSERT INTO SERVICE_TICKETS.eventorigin (Origin) VALUES ('Achmed M.');
INSERT INTO SERVICE_TICKETS.eventorigin (Origin) VALUES ('Rona E.');

-- Insert data into the 'eventstatus' table
INSERT INTO SERVICE_TICKETS.eventstatus (Status) VALUES ('Open');
INSERT INTO SERVICE_TICKETS.eventstatus (Status) VALUES ('On Hold');
INSERT INTO SERVICE_TICKETS.eventstatus (Status) VALUES ('In Process');
INSERT INTO SERVICE_TICKETS.eventstatus (Status) VALUES ('Deployed');
INSERT INTO SERVICE_TICKETS.eventstatus (Status) VALUES ('Deployed Failed');

-- Insert data into the 'eventclass' table
INSERT INTO SERVICE_TICKETS.eventclass (Class) VALUES ('Change');
INSERT INTO SERVICE_TICKETS.eventclass (Class) VALUES ('Incident');
INSERT INTO SERVICE_TICKETS.eventclass (Class) VALUES ('Problem');
INSERT INTO SERVICE_TICKETS.eventclass (Class) VALUES ('SR');