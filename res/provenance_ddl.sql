-- MySQL Script generated by MySQL Workbench
-- 05/25/15 23:01:11
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema provenance
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema provenance
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `provenance` DEFAULT CHARACTER SET utf8 ;
USE `provenance` ;

-- -----------------------------------------------------
-- Table `provenance`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`project` (
  `ProjectId` CHAR(36) NOT NULL,
  `ProjectName` VARCHAR(125) NOT NULL,
  `ProjectLeadName` VARCHAR(125) NULL DEFAULT NULL,
  `ProjectDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ProjectId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`workflow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`workflow` (
  `WorkflowId` CHAR(36) NOT NULL,
  `ProjectId` CHAR(36) NULL DEFAULT NULL,
  `WorkflowName` VARCHAR(125) NULL DEFAULT NULL,
  `WorkflowDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `InputURL` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`WorkflowId`),
  UNIQUE INDEX `XPKWorkflow` (`WorkflowId` ASC),
  INDEX `FK_Project_Workflow` (`ProjectId` ASC),
  CONSTRAINT `FK_Project_Workflow`
    FOREIGN KEY (`ProjectId`)
    REFERENCES `provenance`.`project` (`ProjectId`)
    ON DELETE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`executionlog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`executionlog` (
  `LogId` CHAR(36) NOT NULL,
  `WorkflowId` CHAR(36) NOT NULL,
  `InputURL` VARCHAR(255) NULL DEFAULT NULL,
  `ExecutionStartTime` TIMESTAMP NULL DEFAULT NULL,
  `ExecutionCompleteTime` TIMESTAMP NULL DEFAULT NULL,
  `ReturnValue` VARCHAR(30) NULL DEFAULT NULL,
  `ReturnMessage` VARCHAR(1020) NULL DEFAULT NULL,
  `ExecutionNotes` VARCHAR(1020) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LogId`),
  INDEX `FK_Workflow_Log` (`WorkflowId` ASC),
  CONSTRAINT `FK_Workflow_Log`
    FOREIGN KEY (`WorkflowId`)
    REFERENCES `provenance`.`workflow` (`WorkflowId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`logstep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`logstep` (
  `LogStepId` CHAR(36) NOT NULL,
  `LogId` CHAR(36) NOT NULL,
  `StepSequence` INT(11) NULL DEFAULT NULL,
  `OutputURL` VARCHAR(255) NULL DEFAULT NULL,
  `ExecutionStartTime` TIMESTAMP NULL DEFAULT NULL,
  `ExecutionEndTime` TIMESTAMP NULL DEFAULT NULL,
  `ReturnValue` VARCHAR(30) NULL DEFAULT NULL,
  `ReturnMessage` VARCHAR(1020) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LogStepId`),
  UNIQUE INDEX `UQLogStep` (`LogId` ASC, `StepSequence` ASC),
  CONSTRAINT `FK_Log_Step`
    FOREIGN KEY (`LogId`)
    REFERENCES `provenance`.`executionlog` (`LogId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`logparameter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`logparameter` (
  `LogParmId` CHAR(36) NOT NULL,
  `LogStepId` CHAR(36) NOT NULL,
  `ParameterSequence` INT(11) NULL DEFAULT NULL,
  `ParameterName` VARCHAR(125) NULL DEFAULT NULL,
  `ParameterValue` VARCHAR(30) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LogParmId`),
  UNIQUE INDEX `UQLogParameter` (`LogStepId` ASC, `ParameterSequence` ASC),
  CONSTRAINT `FK_LogStep_Parameter`
    FOREIGN KEY (`LogStepId`)
    REFERENCES `provenance`.`logstep` (`LogStepId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`tool`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`tool` (
  `ToolId` CHAR(36) NOT NULL,
  `ToolName` VARCHAR(125) NOT NULL,
  `ToolDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `ToolURL` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ToolId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`workflowstep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`workflowstep` (
  `StepId` CHAR(36) NOT NULL,
  `ToolId` CHAR(36) NOT NULL,
  `WorkflowId` CHAR(36) NOT NULL,
  `StepSequence` INT(11) NULL DEFAULT NULL,
  `StepName` VARCHAR(125) NULL DEFAULT NULL,
  `StepDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `ToolURL` VARCHAR(255) NULL DEFAULT NULL,
  `OutputURL` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`StepId`),
  UNIQUE INDEX `UQWorkflowStep` (`WorkflowId` ASC, `StepSequence` ASC),
  INDEX `FK_Tool_Step` (`ToolId` ASC),
  CONSTRAINT `FK_Tool_Step`
    FOREIGN KEY (`ToolId`)
    REFERENCES `provenance`.`tool` (`ToolId`),
  CONSTRAINT `FK_Workflow_Step`
    FOREIGN KEY (`WorkflowId`)
    REFERENCES `provenance`.`workflow` (`WorkflowId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`stepparameter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`stepparameter` (
  `StepParmId` CHAR(36) NOT NULL,
  `StepId` CHAR(36) NOT NULL,
  `ParameterName` VARCHAR(125) NULL DEFAULT NULL,
  `ParameterDataType` VARCHAR(30) NULL DEFAULT NULL,
  `ParameterValue` VARCHAR(30) NULL DEFAULT NULL,
  `ParameterSequence` INT(11) NULL DEFAULT NULL,
  `ParameterDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`StepParmId`),
  UNIQUE INDEX `UQStepParameter` (`StepId` ASC, `ParameterSequence` ASC),
  CONSTRAINT `FK_Step_Parameter`
    FOREIGN KEY (`StepId`)
    REFERENCES `provenance`.`workflowstep` (`StepId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `provenance`.`toolparameter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `provenance`.`toolparameter` (
  `ToolParmId` CHAR(36) NOT NULL,
  `ToolId` CHAR(36) NOT NULL,
  `ParameterName` VARCHAR(125) NOT NULL,
  `ParameterDataType` VARCHAR(125) NULL DEFAULT NULL,
  `ParameterDefaultValue` VARCHAR(30) NULL DEFAULT NULL,
  `ParameterSequence` INT(11) NOT NULL,
  `ParameterDescription` VARCHAR(1020) NULL DEFAULT NULL,
  `IsRequired` VARCHAR(30) NULL DEFAULT NULL,
  `CreatedBy` VARCHAR(30) NOT NULL,
  `CreationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` VARCHAR(30) NULL DEFAULT NULL,
  `ModificationTime` TIMESTAMP NULL DEFAULT NULL,
  `Version` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ToolParmId`),
  UNIQUE INDEX `UQToolParameter` (`ToolId` ASC, `ParameterSequence` ASC),
  CONSTRAINT `FK_Tool_Parameter`
    FOREIGN KEY (`ToolId`)
    REFERENCES `provenance`.`tool` (`ToolId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `provenance`;

DELIMITER $$
USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`project_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`project`
FOR EACH ROW
begin
    set new.`ProjectId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`project_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`project`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`workflow_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`workflow`
FOR EACH ROW
begin
    set new.`WorkflowId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`workflow_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`workflow`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`executionlog_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`executionlog`
FOR EACH ROW
begin
    set new.`LogId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`executionlog_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`executionlog`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`logstep_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`logstep`
FOR EACH ROW
begin
    set new.`LogStepId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`logstep_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`logstep`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`logparameter_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`logparameter`
FOR EACH ROW
begin
    set new.`LogParmId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`logparameter_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`logparameter`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`tool_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`tool`
FOR EACH ROW
begin
    set new.`ToolId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`tool_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`tool`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`workflowstep_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`workflowstep`
FOR EACH ROW
begin
    set new.`StepId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`workflowstep_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`workflowstep`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`stepparameter_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`stepparameter`
FOR EACH ROW
begin
    set new.`StepParmId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`stepparameter_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`stepparameter`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`toolparameter_BEFORE_INSERT`
BEFORE INSERT ON `provenance`.`toolparameter`
FOR EACH ROW
begin
    set new.`ToolParmId` = UUID();
    set new.`CreatedBy` = USER();
    END$$

USE `provenance`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `provenance`.`toolparameter_BEFORE_UPDATE`
BEFORE UPDATE ON `provenance`.`toolparameter`
FOR EACH ROW
begin
    set new.`ModificationTime` = CURRENT_TIMESTAMP;
    set new.`ModifiedBy` = USER();
    END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;