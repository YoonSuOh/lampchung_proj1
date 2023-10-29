use lamp;

CREATE TABLE `ccm` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `paper` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `guidename` varchar(20) DEFAULT NULL,
  `prayname` varchar(20) DEFAULT NULL,
  `respname` varchar(20) DEFAULT NULL,
  `offername` varchar(20) DEFAULT NULL,
  
  `ccm1` varchar(100) DEFAULT NULL,
  `ccm2` varchar(100) DEFAULT NULL,
  `ccm3` varchar(100) DEFAULT NULL,
  `ccm4` varchar(100) DEFAULT NULL,
  `resp` varchar(3000) DEFAULT NULL,
  `long_label` varchar(50) DEFAULT NULL,
  `chapter` int DEFAULT NULL,
  `paragraph` int DEFAULT NULL,
  `start` int DEFAULT NULL,
  `fin` int DEFAULT NULL,
  `notice` varchar(1000) DEFAULT NULL,
  `notice1` varchar(1000) DEFAULT NULL,
  `notice2` varchar(1000) DEFAULT NULL,
  `notice3` varchar(1000) DEFAULT NULL,
  `speachname` varchar(16) DEFAULT NULL,
  `nprayname` varchar(20) DEFAULT NULL,
  `nrespname` varchar(20) DEFAULT NULL,
  `noffername` varchar(20) DEFAULT NULL,
  `createdAt` date DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `perm` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

INSERT INTO `lamp`.`user`
(`name`, `password`)
VALUES('admin','lamp');
