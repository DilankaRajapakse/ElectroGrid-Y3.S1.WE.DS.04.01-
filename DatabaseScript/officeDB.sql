-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Apr 26, 2022 at 11:17 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newofficedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `newofficedb`
--

CREATE TABLE `newofficedb` (
  `OfficeID` varchar(10) NOT NULL,
  `OfficeName` varchar(50) NOT NULL,
  `OfficeType` varchar(30) NOT NULL,
  `OfficeAddress` varchar(50) NOT NULL,
  `OfficePhoneNumber` varchar(10) NOT NULL,
  `BranchManager` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `newofficedb`
--

INSERT INTO `newofficedb` (`OfficeID`, `OfficeName`, `OfficeType`, `OfficeAddress`, `OfficePhoneNumber`, `BranchManager`) VALUES
('EL01000', 'Colombo', 'Service', 'Colombo 7', '038011111', 'E1000'),
('EL01030', 'Panadura', 'Service', 'Panadura 05', '038000001', 'E1035');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
