CREATE DATABASE  IF NOT EXISTS `patient-management-db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `patient-management-db`;

-- DROP TABLE IF EXISTS `patient_doctor`;
-- DROP TABLE IF EXISTS `patient_address`;
-- DROP TABLE IF EXISTS `patient`;
-- DROP TABLE IF EXISTS `doctor`;


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;



CREATE TABLE `patient_address` (
  `address_id` int AUTO_INCREMENT primary key,
  `state` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `zipcode` int NOT NULL
  )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `patient` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `a_id` int,
  `full_name` varchar(45),
  `email` varchar(45),
  `age` int,
  `height` float(3),
  `weight` float(3),
  `occupation` varchar(45) DEFAULT NULL,
  `mob_no` varchar(45) NOT NULL UNIQUE,
  `password` varchar(45),
  PRIMARY KEY (`patient_id`),
  FOREIGN KEY(`a_id`) REFERENCES `patient_address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;



  CREATE TABLE `doctor` (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `s_time` Time,
  `e_time` Time,
  `is_available` bool,
  `doctor_specialization` varchar(45) NOT NULL,
  PRIMARY KEY (`doctor_id`) 
  )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `files` (
`file_id` int NOT NULL AUTO_INCREMENT,
`file_name` varchar(45),
`file_type` varchar(45),
`data` MEDIUMBLOB,
PRIMARY KEY (`file_id`) 
)ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

  
    CREATE TABLE `patient_doctor` (
    `appointment_no` int NOT NULL AUTO_INCREMENT,
	`doctor_id` int,
	`patient_id` int,
    `appointment_time` timestamp,
    `status` int default -1,
    `file_id` int,
    `symptoms` varchar(100),
    FOREIGN KEY(`patient_id`) REFERENCES `patient` (`patient_id`),
    FOREIGN KEY(`doctor_id`) REFERENCES `doctor` (`doctor_id`),
    FOREIGN KEY(`file_id`) REFERENCES `files` (`file_id`),
    PRIMARY KEY (`appointment_no`)
  )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
CREATE DATABASE  IF NOT EXISTS `patient-management-db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `patient-management-db`;






