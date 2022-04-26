-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 07:40 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `consumers`
--

CREATE TABLE `consumers` (
  `id` int(11) NOT NULL,
  `regNo` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(75) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `regDate` date NOT NULL,
  `power_usage` int(11) NOT NULL,
  `consumer_type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumers`
--

INSERT INTO `consumers` (`id`, `regNo`, `name`, `address`, `phone`, `regDate`, `power_usage`, `consumer_type`) VALUES
(1, '123564', 'Perera', 'Kurunegala', '0710627950', '2000-01-08', 10, 'Domestic'),
(2, '200019004568', 'Charles ', 'Colombo 11', '0378899653', '2007-03-30', 20, 'Domestic'),
(3, '4689523', 'Agrotech', 'Kandy', '0374423659', '2011-05-23', 100, 'Factory'),
(11, '5498788', 'saman', 'kkk', '4569871230', '1001-01-01', 100, 'Factory');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consumers`
--
ALTER TABLE `consumers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consumers`
--
ALTER TABLE `consumers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
