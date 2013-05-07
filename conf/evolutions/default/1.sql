# Tasks schema
 
# --- !Ups

CREATE TABLE `Project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,                                                                                                                                                      
  `name` varchar(100) DEFAULT NULL,                                                                                                                                                            
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=latin1;
 
# --- !Downs
 
DROP TABLE Project;
