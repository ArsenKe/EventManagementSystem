 
The system will be designed as a web application using a three-layer architecture
that includes a presentation layer, an application tier, and a data layer. The
presentation layer will be responsible for handling user interactions, such as
displaying content and processing user input. The application tier will implement the
business logic of the system. The data layer will store and retrieve data from a
database. The communication between the different layer will be via HTTP and
RESTful APIs.

● System bounded context: The corresponding subdomains are all related to
system management, security, and user communication. The login subdomain
handles user authentication and authorization, the Maintenance subdomain 
manages system maintenance, and the Notification subdomain sends updates
to attendees about changes in events they are attending or bookmarked.

● Event management bounded context: The corresponding subdomains are all
related to event management, discovery, and user engagement, and they
allow attendees to find and interact with events based on their interests. The
most common thing between all those subdomains is that they are really
correlated to the event class(see class diagram).

● Event performance and data analysis bounded context: The corresponding
subdomains are related to event performance and data analysis. The
Feedback Service allows attendees to leave feedback and rate events they
participated in, and the Analytics and Reporting Service generates reports for
organizers about event attendance, feedback, and other metrics

 1.2.1. Development Stack
As a package manager and overall build manager, we will use Maven. Maven was
selected because of its wide adoption and de-facto standard in Java.
Docker in version 20 or above will be used to package our application into containers
and make them easily runnable and scalable. Also since our team is using different
operating systems, this will make the process more easily repeatable.
GitLab CI/CD will be used to manage the pipelines of CI and CD.
ReactJS with npm will be used for setting up the front-end. We chose ReactJS
because it has documentation and most team members have some experience
working with ReactJS.

1.2.2. Technology Stack
Spring boot will be used on the back-end to build the microservices and mostly build
the endpoints for the web application.

React.js will be the front-end to represent the data in User Interface.
We chose MySQL as our database management system because most team
members had experience working with it
