-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 12, 2017 at 03:50 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kamusjawa`
--

-- --------------------------------------------------------

--
-- Table structure for table `kosakata`
--

CREATE TABLE `kosakata` (
  `id` int(10) NOT NULL,
  `indonesia` varchar(60) NOT NULL,
  `jawa` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kosakata`
--

INSERT INTO `kosakata` (`id`, `indonesia`, `jawa`) VALUES
(1, 'mata', 'mripat'),
(2, 'burung', 'manuk'),
(3, 'kambing', 'wedus'),
(4, 'anjing', 'asu'),
(5, 'pandai', 'pinter'),
(6, 'depan', 'ngarep'),
(7, 'makan', 'madhang'),
(8, 'diam', 'meneng'),
(9, 'lari', 'mlayu'),
(10, 'baju', 'klambi'),
(11, 'celana', 'katok'),
(12, 'ular', 'ulo'),
(13, 'terbang', 'mabur'),
(14, 'anak', 'lare'),
(15, 'katak', 'kodok'),
(16, 'pulang', 'bali'),
(17, 'lapar', 'ngeleh'),
(19, 'lelah', 'kesel'),
(20, 'aku', 'kulo'),
(21, 'tidur', 'turu'),
(22, 'ulat', 'uler'),
(23, 'capung', 'kinjeng'),
(24, 'babi', 'celeng'),
(25, 'kecoa', 'coro'),
(26, 'belalang', 'walang'),
(27, 'ikan', 'iwak'),
(28, 'kera', 'munyuk'),
(29, 'belajar', 'sinau'),
(30, 'takut', 'wedi'),
(31, 'jalan', 'mlaku'),
(33, 'tupai', 'bajing'),
(34, 'kelelawar', 'lowo'),
(35, 'duduk', 'lingguh'),
(36, 'lihat', 'delok'),
(37, 'ayam', 'pitek'),
(38, 'berangkat', 'mangkat'),
(39, 'kura-kura', 'bulus'),
(40, 'kepiting', 'yuyu'),
(41, 'udang', 'urang'),
(42, 'kuda', 'jaran'),
(43, 'lebah', 'tawon'),
(44, 'nyamuk', 'lemut'),
(45, 'kumbang', 'kwawong'),
(46, 'kerbau', 'kebo'),
(47, 'rusa', 'kidang'),
(48, 'kutu', 'tumo'),
(49, 'pepaya', 'gandul'),
(50, 'mangga', 'pelem'),
(51, 'kelapa', 'kambil'),
(52, 'pisang', 'gedang'),
(53, 'teman', 'konco'),
(54, 'air', 'banyu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kosakata`
--
ALTER TABLE `kosakata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kosakata`
--
ALTER TABLE `kosakata`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
