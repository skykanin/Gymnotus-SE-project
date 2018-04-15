CREATE TABLE User (
  userID int NOT NULL PRIMARY KEY IDENTITY,
  username VARCHAR(32),
  password VARCHAR(64),
  name VARCHAR(64),
  email VARCHAR(64),
  phoneumber VARCHAR(16),
  gender INT,
  age INT,
  isAnonymous BOOLEAN,
  shareHealthData BOOLEAN,
  shareExerciseData BOOLEAN,
  isTrainer BOOLEAN,
  CHECK (age >= 16)
);

CREATE TABLE Role (
  roleID INT NOT NULL PRIMARY KEY,
  title VARCHAR(64),
  description VARCHAR(256)
);

CREATE TABLE UserHasRole (
  userID int NOT NULL ,
  roleID int NOT NULL,
  PRIMARY KEY (userID, roleID),
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE,
  FOREIGN KEY (roleID) REFERENCES Role(roleID) ON DELETE CASCADE
);

CREATE TABLE Feedback (
  feedbackID int NOT NULL PRIMARY KEY IDENTITY,
  trainerID int NOT NULL,
  userID int NOT NULL,
  content VARCHAR(1024),
  date DATE,
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE,
  FOREIGN KEY (trainerID) REFERENCES User(userID) ON DELETE CASCADE
);

CREATE TABLE HealthDataReport (
  reportID INT NOT NULL PRIMARY KEY IDENTITY,
  userID INT NOT NULL,
  date DATE,
  bloodPressure INT,
  dailySteps INT,
  restingHeartRate INT,
  height INT,
  weight INT,
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE
);

CREATE TABLE ExerciseProgram (
  programID INT NOT NULL PRIMARY KEY IDENTITY,
  name VARCHAR(64),
  description VARCHAR(1024)
);

CREATE TABLE Exercise (
  exerciseID INT NOT NULL PRIMARY KEY IDENTITY,
  programID INT,
  description VARCHAR(1024),
  sets INT,
  repsPerSet INT,
  pauseBetweenSets INT,
  parameterDescription VARCHAR(512),
  CHECK (sets > 0 and repsPerSet > 0),
  FOREIGN KEY (programID) REFERENCES ExerciseProgram(programID));

CREATE TABLE Result (
  resultID INT NOT NULL PRIMARY KEY IDENTITY,
  userID INT NOT NULL,
  exerciseID INT NOT NULL,
  resultParameter INT,
  date DATE,
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE,
  FOREIGN KEY (exerciseID) REFERENCES Exercise(exerciseID) ON DELETE CASCADE
);

CREATE TABLE UserRegisteredForExercise (
  userID int NOT NULL,
  programID int NOT NULL,
  registrationDate Date,
  PRIMARY KEY (userID, programID),
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE,
  FOREIGN KEY (programID) REFERENCES ExerciseProgram(programID) ON DELETE CASCADE
);

CREATE TABLE UserCommentOnExerciseprogram (
  commentID int NOT NULL PRIMARY KEY IDENTITY,
  userID int NOT NULL,
  programID int NOT NULL,
  content VARCHAR(1024),
  date DATE,
  FOREIGN KEY (userID) REFERENCES User(userID),
  FOREIGN KEY (programID) REFERENCES ExerciseProgram(programID) ON DELETE CASCADE
);

CREATE TABLE UserTrainerExerciseprogram (
  userID int NOT NULL,
  programID int NOT NULL,
  PRIMARY KEY (userID, programID),
  FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE,
  FOREIGN KEY (programID) REFERENCES ExerciseProgram(programID) ON DELETE CASCADE
);
