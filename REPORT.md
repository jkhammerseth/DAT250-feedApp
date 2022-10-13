Front page title goes here (add hvl picture)





Names of group members








*Evaluation*


The report and project will be evaluated according to

    • readability and technical quality
    • § Readability (primarily report)
    • § Logical flow of the presentation
    • § Self-contained with respect to presenting the software technology presented
    • (assume DAT250 knowledge)
    • § Language and writing (e.g., not a lot of headings with little text - but
    • substantial paragraphs with your own writing)
    • § Presentation and layout (overall look and feel of the report)
    • § Balanced structure (e.g., 50% of the space is not spent in the introduction)


Report evaluation [2:2]

    • § Technical quality (report and prototype)
    • § Prototype completeness and complexity: are the key aspect of the developed
    • prototype explained and does it meet the requirements (see earlier slides)
    • § Overall technical quality of the prototype implementation and is the
    • developed prototype implementation operational
    • (e.g., does it work or does it have significant errors)
    • § Assessment and analysis of the chosen technology
    • (e.g., comparison with related technologies, results from experiments)
    • § The project report will have a weight of 40 % in the final
    • grade of the course
    • § Remember: the report needs to be submitted via WiseFlow
    • – deadline is strict









## Introduction [~1 page]:

    • A brief introduction to the prototype implementation and topic of the project

The prototype that was to be implemented in this projected was a simple voting system, where one has ability to create a user and then create polls and take part in polls created by other users. The polls can be either private, and only a logged in user has access to them, or they can be public and anyone with the correct link or access code is able to view and partake in the poll. In addition there is the option of having an IoT device connected to the system, and use this device with a persistent connection to the system, and thereby being able to vote by the push of a button. The system has a persistent creation of users and polls stored in a relational database. In addition there is a supervising feature storing an equivalent copy of the data in a non-relational database, so that the voting can be supervised by a (possible) third party to see that the voting system is consistent and the results of the polls presented can be trustworthy. The interface is accessed through the web and is implemented in an API.

    • § Discuss (briefly) the technology stack that has been selected, mention related
      technologies (if relevant), primary arguments for choice of technology stack

The technology stack we selected consists of PostgreSQL and MongoDB as relational and non-relational databases respectively, Django with python as the back-end, and React with JavaScript, HTML and CSS as the front-end. We worked with Spring Boot and Java initially as part of the course, but changed to Django as we agreed this would be easier to implement and work with given the programming experience and background of the group members. 
    • § A brief account of the results that have been obtained in the project


[This will be completed towards the end of working on the assignment]


    • § A one paragraph overview at the end, explaining how the rest of the report is/ has been organised

[The rest of the report is organised with … following … then … and then … and then finally …]












## The Software Technology Stack [~3 pages]
    • Introduce in (sufficient) depth the key concepts and architecture of the
    • chosen software technologies


[Spring Boot/PostgreSQL as back-end with a REST API…] 

[React with Typescript/JavaScript, HTML, CSS, or Vue as front-end]

[Python application with MongoDB that communicates using Websockets as the supervisor]




    • § May use a running example to introduce the technology

[Insert picture of running application]
    • § Emphasize the “new” software technologies that was selected by the group
    • and which has not been covered in the course

[Vue or other, explain technology]













## Design of the FeedApp Prototype [~4 pages]



    • An architectural overview of the application that has been implemented

[Pictures of architecture etc with text explaining the implementations]

    • § High-level design, domain model, … (App assignment A)

[flow diagram, mockups, relations, with text explaining]

    • § May involve selected models from Chaps. 5 of the IoT and cloud books

[IoT/cloud model, relevant pictures from the books, with modified version showing our specific implementation in relation to the examples]














## Implementation [~4 pages]


    • Details of how the prototype has been implemented which may involve presentation of suitable code snippets
















## Test-bed environment and experiments [~2 pages]



    • Explain how the prototype has been tested the test-bed environment

    • § Explains what experiments have been done and the results














## Conclusion [~1/2 page]


    • Concludes on the project, including the technology, its maturity, learning curve, and quality of the documentation
















## References [~1/2 page]
    • § Provide a well chosen set of references, suitable for someone interesting in
learning about the software technology used in the project
