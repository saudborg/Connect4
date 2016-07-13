# Gluck Game - Connect4
This project was developed for Gluck Games. It represents a connect4 game
- [Connect 4](https://en.wikipedia.org/wiki/Connect_Four)

### Prerequisities
- [Apache Maven](https://maven.apache.org/index.html)


## Built with
- Java 1.8
- Maven 3.2.1
- Spring Boot (JPA, TEST, MVC, WEBSOCKET, TOMCAT, THYMELEAF)
- Bootstrap
- HQSQL (Memory database. Every time you shutdown the system, will be necessary create everthing again)


## Installation
- Clone the project

- Go to folder where you can see the pom.xml
- run `mvn spring-boot:run`

OR

- Import the project in an IDE
- RUN the class Application.java


### How to use 
When you see on your terminal `INFO: Server startup in 8367 ms` you are ready to use it

- There is two options to play a game: via REST or via Real Time. But both need a creation before

- Create 2 players: 

(POST) http://localhost:8080/newPlayer
name 	:		namePlayer
color	:		color (Blue or Red)

- Create a game:
(POST) http://localhost:8080/createGame
player1	:	id (this ID you can get in the response when you create a user)
player2	:	id


$ Play via REST
Here you are gonna play using a rest system. Through http://localhost:8080/game/{game_id}/play you can select a game that you are playing and which player are you

(POST) http://localhost:8080/game/{game_id}/play (The game id you get in the response when you create a game)
col 		:	integer	(Reference on the column you would like to play. By default 0 to 5)
playerId	:	ID (this ID you can get in the response when you create a user)

Your result will be like this:
`
"id": 1,
    "game": [
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","SPACE","SPACE","SPACE","SPACE","SPACE", "SPACE"
        ],
        [
            "SPACE","BLUE","SPACE","SPACE","SPACE","SPACE","SPACE"
        ]
    ],
    "result": []
`

If there is a winner, also will show a message like this:

    "winner": {
        "id": 1,
        "name": "saulo",
        "coin": "BLUE"
    },
        "result": [
        {
            "row": 2, "col": 1
        },
        {
            "row": 3,"col": 1
        },
        {
            "row": 4,"col": 1
        },
        {
            "row": 5,"col": 1
        }
    ]

If there is an error, like not your turn or invalid column, a message like this will show up

`
    {
        "timestamp": 1468411182421,
        "status": 500,
        "error": "Internal Server Error",
        "exception": "com.saulo.borges.exception.AppException",
        "message": "It is not your turn to play",
        "path": "/game/1/play"
    }
`

- You also can check the current status of the game using:
(GET) http://localhost:8080/game/{game_id}/status


$ Play online
- After use a rest service to create a game and the players, you can acess the url to play on real time online
http://localhost:8080/game/{game_id}/online?player={player_id}

In this screen you just need to choose which column would you like to play and a message will return.
The same message your opponent will receive.




## NOTES
- The interface is not completed. I believe that it is possible to improving, and also create some screen to create a game and a player.
- Also to improve the game, would be nice create a password for the player, then just the players can see the game.
- Would be nice create more endpoint to do things like delete a user or leave the game
- In attachment on GitHub you can see a file named RestUrls.json. It is possible to import in the Postman, and all POSTs and GETs available are there



# ABOUT THE DEVELOPING
- The game rules and the tests was developed in 2 days, without big problems. After this, in 2 days I created the rest service. After this my big problem was create a way to play in two player online using an interface. I found on internet one way and the project was based using it






