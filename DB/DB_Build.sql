drop table if exists interviews;
drop table if exists candidateskillmap;
drop table if exists jdskillmap;
drop table if exists candidateprofile;
drop table if exists JDs;
drop table if exists skills;

CREATE TABLE Skills (
    SkillId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    TechnicalSoftSkillFlag CHAR(1),
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100)
);

CREATE TABLE JDs (
    JDId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Description VARCHAR(100),
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100)
);

CREATE TABLE CandidateProfile (
    CandidateProfileId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    EmailAddress VARCHAR(100),
    MobileNumber VARCHAR(100),
    OverallMatchScore DOUBLE,
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100)
);

CREATE TABLE JDSkillMap (
    JDSkillMapId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    JDId INT,
    SkillId INT,
    MustHaveFlag CHAR(1),
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100),
    FOREIGN KEY (JDId)
        REFERENCES JDs (JDId),
    FOREIGN KEY (SkillId)
        REFERENCES Skills (SkillId)
);


CREATE TABLE CandidateSkillMap (
    CandidateSkillMapId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CandidateProfileId INT,
    SkillId INT,
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100),
    FOREIGN KEY (SkillId)
        REFERENCES Skills (SkillId),
    FOREIGN KEY (CandidateProfileId)
        REFERENCES CandidateProfile (CandidateProfileId)
);


CREATE TABLE Interviews (
    InterviewId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CandidateProfileId INT,
    JDId INT,
    TP1DateTime DATETIME,
    TP1Interviewer VARCHAR(100),
    TP1Result VARCHAR(100),
    TP2DateTime DATETIME,
    TP2Interviewer VARCHAR(100),
    TP2Result VARCHAR(100),
    ManagerRoundDateTime DATETIME,
    ManagerRoundInterviewer VARCHAR(100),
    ManagerRoundResult VARCHAR(100),
    Status VARCHAR(100),
    IsJoined CHAR(1),
    DueDate DATE,
    InsTS DATETIME,
    InsBy VARCHAR(100),
    UpdTS DATETIME,
    UpdBy VARCHAR(100),
    FOREIGN KEY (JDId)
        REFERENCES JDs (JDId),
    FOREIGN KEY (CandidateProfileId)
        REFERENCES CandidateProfile (CandidateProfileId)
);