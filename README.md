# CalendarRMI
distributed calendar tool for work groups


To compile the server, type 'javac *.java' in the Server directory.

To compile the client, type 'javac *.java' in the Client directory.

To run the Server and Client:

2) Start the server in the Server directory by typing:
rmiregistry &

3) Followed by:
java -Djava.security.policy=policy.txt serverDriver &

3) Run the client in the Client directory by typing:
java clientDriver ‘name of user’


** replace ‘name of user’ with a name you want to give to the user. 


