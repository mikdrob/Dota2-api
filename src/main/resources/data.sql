DROP TABLE IF EXISTS players;

CREATE TABLE players (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  playerId INT NOT NULL,
  displayMatches BOOLEAN,
  response VARCHAR(250000) DEFAULT NULL
);

-- jdbc:h2:tcp://localhost:9092/default