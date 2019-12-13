-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: aaw6ro0jzeyzzl.c4bb58vdvgk0.us-east-1.rds.amazonaws.com:3306
-- Generation Time: Dec 13, 2019 at 04:43 AM
-- Server version: 5.7.22-log
-- PHP Version: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebdb`
--
CREATE DATABASE IF NOT EXISTS `ebdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ebdb`;
--
-- Database: `innodb`
--
CREATE DATABASE IF NOT EXISTS `innodb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `innodb`;
--
-- Database: `lawncare`
--
CREATE DATABASE IF NOT EXISTS `lawncare` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `lawncare`;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `userType` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`email`, `password`, `phone`, `firstName`, `lastName`, `userType`) VALUES
('a@a.com', '0cc175b9c0f1b6a831c399e269772661', '0001112222', 'A', 'A', 'owner'),
('admin@lawncare.com', '21232f297a57a5a743894a0e4a801fc3', '0000000000', 'Admin', 'Admin', 'admin'),
('deleteme@gmail.com', '099af53f601532dbd31e0ea99ffdeb64', '1231231234', 'Delete', 'Me', 'worker'),
('g@g.com', 'b2f5ff47436671b6e533d8dc3614845d', '2489990000', 'Gregory', 'Magnus', 'worker'),
('jhooper@yahoo.com', 'a1889c919e2d08799e6507f5c0b3db17', '3130301441', 'Julius', 'Hooper', 'worker'),
('jlong1995@hotmail.com', 'ef80b3fc4aa642139eee38138f2f5020', '2480001111', 'Long', 'John', 'worker'),
('john@gmail.com', '202cb962ac59075b964b07152d234b70', '1231232310', 'john', 'smioth', 'worker'),
('johnsmith@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', '2481234567', 'John', 'Smith', 'owner'),
('jr@gmail.com', 'd66caed435d9bb1665e1cb3289e0e617', '2485556464', 'Jorge', 'Rose', 'owner'),
('m@m.com', 'b2f5ff47436671b6e533d8dc3614845d', '1236547899', 'ghjhj', 'dsfdsfs', 'worker'),
('marco@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', '0001112222', 'Marco', 'Manlius', 'worker'),
('michaeljyousif@lawncare.com', 'c8000fb484e96e50fcb9e5dbcfe488f4', '2488820323', 'Michael', 'Yousif', 'admin'),
('mo@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', '0001112222', 'mom', 'dad', 'worker'),
('patel.xyz@gmail.com', '202cb962ac59075b964b07152d234b70', '1234567890', 'xyz', 'patel', 'worker'),
('q@q.com', '7694f4a66316e53c8cdd9d9954bd611d', '1236784562', 'q', 'q', 'worker'),
('qfo@aol.com', '7694f4a66316e53c8cdd9d9954bd611d', '2488976538', 'Quenten', 'Foster', 'owner'),
('willr@aol.com', 'e19920042b8356072d172ff0fc7688cc', '2692014913', 'William', 'Regens', 'owner'),
('y@y.com', '415290769594460e2e485922904f345d', '3456782989', 'y', 'y', 'worker');

-- --------------------------------------------------------

--
-- Table structure for table `propertyInfo`
--

CREATE TABLE `propertyInfo` (
  `propertyNumber` int(11) NOT NULL,
  `ownerEmail` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip` varchar(5) NOT NULL,
  `lawnSize` int(11) NOT NULL,
  `equipmentAvailable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `propertyInfo`
--

INSERT INTO `propertyInfo` (`propertyNumber`, `ownerEmail`, `street`, `city`, `state`, `zip`, `lawnSize`, `equipmentAvailable`) VALUES
(86, 'willr@aol.com', '41 Lima Avenue', 'Kalamazoo', 'MI', '49001', 6700, 1),
(90, 'johnsmith@gmail.com', '43359 Stonignton ct', 'Canton', 'MI', '48188', 102, 0),
(120, 'a@a.com', '97 Stop ct', 'Can', 'MI', '48189', 480, 1),
(129, 'a@a.com', '2 Stop ct', 'Can', 'MI', '48189', 480, 1),
(130, 'a@a.com', '123567', 'Det', 'MI', '12967', 120, 1);

-- --------------------------------------------------------

--
-- Table structure for table `propertyWork`
--

CREATE TABLE `propertyWork` (
  `propertyNumber` int(11) NOT NULL,
  `workNeeded` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `propertyWork`
--

INSERT INTO `propertyWork` (`propertyNumber`, `workNeeded`) VALUES
(86, ''),
(86, 'Clippings'),
(86, 'Mowing'),
(86, 'Trimming'),
(90, ''),
(90, 'Fertilization'),
(120, ''),
(120, 'Clippings'),
(129, ''),
(129, 'Clippings'),
(130, ''),
(130, 'Clippings');

-- --------------------------------------------------------

--
-- Table structure for table `workerProfile`
--

CREATE TABLE `workerProfile` (
  `workerEmail` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `daysAvailable` varchar(7) DEFAULT NULL,
  `startTime` varchar(20) DEFAULT NULL,
  `endTime` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workerProfile`
--

INSERT INTO `workerProfile` (`workerEmail`, `description`, `website`, `daysAvailable`, `startTime`, `endTime`) VALUES
('deleteme@gmail.com', NULL, NULL, NULL, NULL, NULL),
('g@g.com', 'I have been a self-employed lawn Care specialist forI have been a self-employed lawI hI have been a self-employed lawn Care specialist for the past 20 years. I have to of the line lawn mowing equipmen', 'null', '', 'null', 'null'),
('jhooper@yahoo.com', 'I am a hard worker with 8 years of experience in the lawn care industry. Visit my website to view my credentials and read testimonials', 'new.com', 'MTWR', '10 : 00', '04 : 00'),
('marco@gmail.com', 'HI I\'m marco', 'Google.com', 'UMT', '00:00:16', '00:00:05'),
('q@q.com', NULL, NULL, NULL, NULL, NULL),
('y@y.com', 'null', 'null', 'UM', '04 : 42', '13 : 42');

-- --------------------------------------------------------

--
-- Table structure for table `workerWork`
--

CREATE TABLE `workerWork` (
  `workerEmail` varchar(50) NOT NULL,
  `workOffered` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workerWork`
--

INSERT INTO `workerWork` (`workerEmail`, `workOffered`) VALUES
('G@G.COM', 'Mowing'),
('jhooper@yahoo.com', 'Clippings'),
('jhooper@yahoo.com', 'Fertilization'),
('marco@gmail.com', 'Clippings'),
('marco@gmail.com', 'Fertilization'),
('marco@gmail.com', 'Pest_Control');

-- --------------------------------------------------------

--
-- Table structure for table `workerrating`
--

CREATE TABLE `workerrating` (
  `idworkerrating` int(11) NOT NULL,
  `rating_star` float DEFAULT NULL,
  `rating_description` varchar(300) DEFAULT NULL,
  `worker_email` varchar(50) DEFAULT NULL,
  `owner_email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workerrating`
--

INSERT INTO `workerrating` (`idworkerrating`, `rating_star`, `rating_description`, `worker_email`, `owner_email`) VALUES
(1, 2, 'dghfghghfg', 'g@g.com', 'qfo@aol.com'),
(2, 4, 'dhfj gift dhhch', 'g@g.com', 'a@a.com'),
(4, 5, 'Nice fella', 'jhooper@yahoo.com', 'johnsmith@gmail.com'),
(5, 3, 'what a weirdo name', 'q@q.com', 'a@a.com'),
(6, 1, 'lol', 'marco@gmail.com', 'johnsmith@gmail.com'),
(8, 4, 'he was very nice, but also odd', 'q@q.com', 'johnsmith@gmail.com'),
(9, 1, 'They stole my dog', 'dell@gmail.com', 'johnsmith@gmail.com'),
(10, 3, 'djfndndbdjdndbf', 'g@g.com', 'a@a.com'),
(11, 0, '', 'g@g.com', 'a@a.com'),
(12, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(13, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(14, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(15, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(16, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(17, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(18, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(19, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(20, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(21, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(22, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(23, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(24, 3, 'asdfcacfgyuqa', 'jhooper@yahoo.com', 'a@a.com'),
(25, 2, '', 'g@g.com', 'a@a.com'),
(26, 2, '', 'g@g.com', 'a@a.com'),
(27, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(28, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(29, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(30, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(31, 2, 'Great Job', 'g@g.com', 'a@a.com'),
(32, 2, 'Great Job', 'g@g.com', 'a@a.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `propertyInfo`
--
ALTER TABLE `propertyInfo`
  ADD PRIMARY KEY (`propertyNumber`),
  ADD UNIQUE KEY `addressUnique` (`street`,`city`,`state`),
  ADD KEY `FK_ownerEmail_email` (`ownerEmail`);

--
-- Indexes for table `propertyWork`
--
ALTER TABLE `propertyWork`
  ADD PRIMARY KEY (`propertyNumber`,`workNeeded`);

--
-- Indexes for table `workerProfile`
--
ALTER TABLE `workerProfile`
  ADD PRIMARY KEY (`workerEmail`);

--
-- Indexes for table `workerWork`
--
ALTER TABLE `workerWork`
  ADD PRIMARY KEY (`workerEmail`,`workOffered`);

--
-- Indexes for table `workerrating`
--
ALTER TABLE `workerrating`
  ADD PRIMARY KEY (`idworkerrating`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `propertyInfo`
--
ALTER TABLE `propertyInfo`
  MODIFY `propertyNumber` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;
--
-- AUTO_INCREMENT for table `workerrating`
--
ALTER TABLE `workerrating`
  MODIFY `idworkerrating` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `propertyInfo`
--
ALTER TABLE `propertyInfo`
  ADD CONSTRAINT `FK_ownerEmail_email` FOREIGN KEY (`ownerEmail`) REFERENCES `accounts` (`email`) ON UPDATE CASCADE;

--
-- Constraints for table `propertyWork`
--
ALTER TABLE `propertyWork`
  ADD CONSTRAINT `FK_propertyID` FOREIGN KEY (`propertyNumber`) REFERENCES `propertyInfo` (`propertyNumber`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `workerProfile`
--
ALTER TABLE `workerProfile`
  ADD CONSTRAINT `FK_workerEmail_email` FOREIGN KEY (`workerEmail`) REFERENCES `accounts` (`email`);

--
-- Constraints for table `workerWork`
--
ALTER TABLE `workerWork`
  ADD CONSTRAINT `FK_workerWork_email` FOREIGN KEY (`workerEmail`) REFERENCES `workerProfile` (`workerEmail`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Database: `tmp`
--
CREATE DATABASE IF NOT EXISTS `tmp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tmp`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
