-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2026 at 10:01 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `device_inventory_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignments`
--

CREATE TABLE `assignments` (
  `assignment_id` int(11) NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `assigned_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assignments`
--

INSERT INTO `assignments` (`assignment_id`, `device_id`, `employee_id`, `assigned_date`, `return_date`, `status`) VALUES
(2, 3, 2, '2026-04-24', NULL, 'ACTIVE'),
(3, 4, 2, '2026-04-25', NULL, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `audit_history`
--

CREATE TABLE `audit_history` (
  `audit_id` int(11) NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `action_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `audit_history`
--

INSERT INTO `audit_history` (`audit_id`, `device_id`, `employee_id`, `action`, `action_date`) VALUES
(1, 3, 2, 'ASSIGNED to barinda', '2026-04-24 16:22:53'),
(2, 4, 2, 'ASSIGNED to barinda system', '2026-04-24 23:39:47');

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `device_id` int(11) NOT NULL,
  `device_name` varchar(100) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `serial_number` varchar(100) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `condition_status` varchar(50) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`device_id`, `device_name`, `type`, `serial_number`, `brand`, `model`, `status`, `condition_status`, `purchase_date`, `created_at`) VALUES
(3, 'Dell XPS 13', 'Laptop', 'SN12345', 'Dell', 'XPS 13', 'Assigned', 'Good', '2026-04-24', '2026-04-24 16:22:41'),
(4, 'mackbook pro', 'Tablet', 'sn430-b34', 'apple', '17 pro', 'Assigned', 'Good', '2026-04-07', '2026-04-24 20:44:54');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `email`, `department`, `created_at`) VALUES
(2, 'barinda', 'system', 'iradukunda@gmail.com', 'ET', '2026-04-24 15:36:49'),
(4, 'Oscar', 'NIYOKWIZERWA', 'oscar@gmail.com', 'HR', '2026-04-24 21:04:55');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `email`, `department`, `role`, `created_at`, `password`) VALUES
(1, 'Jean Mutabazi', 'jean@airtel.rw', 'IT', 'Admin', '2026-04-24 12:40:46', 'password123'),
(2, 'barinda system sylvere', 'systembarinda@gmail.com', 'IT', 'Staff', '2026-04-24 13:08:22', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignments`
--
ALTER TABLE `assignments`
  ADD PRIMARY KEY (`assignment_id`),
  ADD KEY `device_id` (`device_id`),
  ADD KEY `FKr69edfcloedr3t1ylmiesklif` (`employee_id`);

--
-- Indexes for table `audit_history`
--
ALTER TABLE `audit_history`
  ADD PRIMARY KEY (`audit_id`),
  ADD KEY `device_id` (`device_id`),
  ADD KEY `FKeswbkfeexa83q31l50s1jhpnf` (`employee_id`);

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`device_id`),
  ADD UNIQUE KEY `serial_number` (`serial_number`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignments`
--
ALTER TABLE `assignments`
  MODIFY `assignment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `audit_history`
--
ALTER TABLE `audit_history`
  MODIFY `audit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
  MODIFY `device_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignments`
--
ALTER TABLE `assignments`
  ADD CONSTRAINT `FKr69edfcloedr3t1ylmiesklif` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `assignments_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`),
  ADD CONSTRAINT `assignments_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `audit_history`
--
ALTER TABLE `audit_history`
  ADD CONSTRAINT `FKeswbkfeexa83q31l50s1jhpnf` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `audit_history_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`),
  ADD CONSTRAINT `audit_history_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
