Build a RESTful web service that provides a simple to-do list. The web service should allow the user to perform the following operations:

Add a new to-do item.
Retrieve a list of all to-do items.
Retrieve a single to-do item by ID.
Update an existing to-do item.
Delete a to-do item.

Each to-do item should have the following attributes:

ID (a unique identifier for the item).
Title (a short title for the item).
Description (a longer description of the item).
Due date (the date by which the item should be completed).
Status (one of "To Do", "In Progress", or "Done").

The web service should use a database (such as MySQL or PostgreSQL) to store the to-do items, and should use Spring Data JPA to access the database.
