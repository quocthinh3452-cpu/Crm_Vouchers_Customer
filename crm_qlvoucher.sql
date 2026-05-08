-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 08, 2026 at 01:31 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crm_qlvoucher`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `email`, `phone`, `created_at`) VALUES
(1, 'Hồ Quốc Thịnh', 'quocthinh3452@gmail.com', '0333461614', '2026-05-08 18:56:31'),
(2, 'Nguyễn Văn A', 'thinhsuy2304@gmail.com', '0799703452', '2026-05-08 19:25:27');

-- --------------------------------------------------------

--
-- Table structure for table `vouchers`
--

DROP TABLE IF EXISTS `vouchers`;
CREATE TABLE IF NOT EXISTS `vouchers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `discount_percent` int NOT NULL,
  `quantity` int NOT NULL,
  `expired_date` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ;

--
-- Dumping data for table `vouchers`
--

INSERT INTO `vouchers` (`id`, `code`, `discount_percent`, `quantity`, `expired_date`, `status`, `created_at`) VALUES
(1, 'SUMMER2026', 25, 47, '2026-12-31', 'ACTIVE', '2026-05-08 16:03:16'),
(2, 'HQT2304', 16, 0, '2026-05-09', 'ACTIVE', '2026-05-08 18:13:10');

-- --------------------------------------------------------

--
-- Table structure for table `voucher_usages`
--

DROP TABLE IF EXISTS `voucher_usages`;
CREATE TABLE IF NOT EXISTS `voucher_usages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `voucher_id` bigint NOT NULL,
  `used_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_vouchers` (`voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `voucher_usages`
--

INSERT INTO `voucher_usages` (`id`, `user_id`, `voucher_id`, `used_at`) VALUES
(1, 1, 1, '2026-05-08 19:17:04'),
(2, 1, 1, '2026-05-08 19:17:23'),
(3, 1, 1, '2026-05-08 19:25:46'),
(4, 1, 2, '2026-05-08 19:26:09');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `voucher_usages`
--
ALTER TABLE `voucher_usages`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_vouchers` FOREIGN KEY (`voucher_id`) REFERENCES `vouchers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
