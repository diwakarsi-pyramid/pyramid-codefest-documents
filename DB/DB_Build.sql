DROP TABLE IF EXISTS JD;
DROP TABLE IF EXISTS CandidateProfile;

CREATE TABLE JD (
    JDId INT AUTO_INCREMENT PRIMARY KEY,
    JDName VARCHAR(500),
    Skill_GoodToHave TEXT,
    Skill_Mandatory TEXT,
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100)
);

CREATE TABLE CandidateProfile (
    CandidateProfileId INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(500),
    EmailAddress VARCHAR(100),
    MobileNumber VARCHAR(15),
    OverallMatchScore DOUBLE,
    Skills TEXT,
    TP1Comment TEXT,
    TP2Comment VARCHAR(100),
    Status VARCHAR(100),
    IsJoined CHAR(1),
    DueDate DATETIME,
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100)
);
