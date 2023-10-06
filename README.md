 
The system will be designed as a web application using a three-layer architecture
that includes a presentation layer, an application tier, and a data layer. The
presentation layer will be responsible for handling user interactions, such as
displaying content and processing user input. The application tier will implement the
business logic of the system. The data layer will store and retrieve data from a
database. The communication between the different layer will be via HTTP and
RESTful APIs

 
● The system will be accessible via a web interface and will be compatible with
standard web browsers
● The Maintainer can check the quality and health of each microservice and
update them if bugs are found.
● Databases are not services, hence they do not need to be checked by the
Maintenance Service.
● The users can login/logout and system handles that using access tokens.
● We did not add a user management service(add user, update user info, delete
user) because it was not specified in the assignment sheet, but we could add it
if the supervisor judged it is necessary.
● The organizer can create,update and delete events.
● Every bounded context has its own domain classes (e.g. event management
context has a class Event with attributes description, capacity, id, ...; System
context has another class Event but with the same attributes).
● The recommender service gets activated only when the attendee has at least
1 bookmark or when a Free event is added.
● When organizers and attendees will search for events by different criteria they
will get the search results depending on the search query. Here also users will
have the option to filter their search results by tags.
● Attendees can mark/unmark events they are interested in. The attendees can
tag/untag different events. The tags will be predefined in the system.
● Calendar service should be able to retrieve event data from a database, JSON
and XML formats are machine-readable and standard calendar format is
compatible with popular calendar applications like Google Calendar.
● Analytic and reporting services should be able to generate reports based on
different parameters like event types or date ranges.
● Attendees can attend events only if there are vacancies
● Setting the capacity of the event will be handled with ManageInventoryService
(instead of AttendanceService)
● Event organizers will have the necessary permissions and access to create,
manage, and update events using the system.
