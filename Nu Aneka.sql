-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2021 at 12:10 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalprojectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` int(11) UNSIGNED NOT NULL,
  `category` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `category`) VALUES
(1, 'Ballpoint'),
(2, 'Eraser'),
(3, 'Pencil Case');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `cityId` int(11) UNSIGNED NOT NULL,
  `city` varchar(20) NOT NULL,
  `provinceId` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`cityId`, `city`, `provinceId`) VALUES
(1, 'Bekasi', 1),
(2, 'Jakarta Utara', 3),
(3, 'Tangerang', 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) UNSIGNED NOT NULL,
  `customerName` varchar(30) NOT NULL,
  `customerAddress` text NOT NULL,
  `cityId` int(11) UNSIGNED NOT NULL,
  `customerContact` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `customerName`, `customerAddress`, `cityId`, `customerContact`) VALUES
(3, 'Carolina', 'Jl. Mutia Indah 2', 1, 'carolina@gmail.com'),
(5, 'Zefanya', 'Jl. Permata Indah', 3, '8808074'),
(7, 'Jensen H', 'Perumahan Villa Indah Blok C1 No.3', 1, 'jensenh@gmail.com'),
(8, 'Pak Kunarto', 'Jl. Setia Budi', 2, 'kunarto@yahoo.com'),
(11, 'Davin', 'Jl. Suka mulya', 3, 'davin@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `username` varchar(20) NOT NULL,
  `employeeName` varchar(30) NOT NULL,
  `employeeAddress` text NOT NULL,
  `cityId` int(11) UNSIGNED NOT NULL,
  `employeeContact` varchar(35) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`username`, `employeeName`, `employeeAddress`, `cityId`, `employeeContact`, `password`) VALUES
('alvwjy', 'Alvian Wijaya', 'Jl. Indah Kencana', 1, 'alvwjy@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `productId` int(11) UNSIGNED NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productQty` int(11) UNSIGNED DEFAULT NULL,
  `categoryId` int(11) UNSIGNED NOT NULL,
  `productPrice` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`productId`, `productName`, `productQty`, `categoryId`, `productPrice`) VALUES
(1, 'Zebra 0.5mm', 4, 1, 5000),
(4, 'Joyko 0.25 Super Thin', 96, 1, 2000),
(7, 'Big Pencil Case', 0, 3, 25000);

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE `province` (
  `provinceId` int(11) UNSIGNED NOT NULL,
  `province` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`provinceId`, `province`) VALUES
(1, 'Jawa Barat'),
(2, 'Banten'),
(3, 'DKI Jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `restock`
--

CREATE TABLE `restock` (
  `restockId` int(11) UNSIGNED NOT NULL,
  `supplierId` int(11) UNSIGNED NOT NULL,
  `productId` int(11) UNSIGNED NOT NULL,
  `restockDate` date NOT NULL,
  `restockPrice` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restock`
--

INSERT INTO `restock` (`restockId`, `supplierId`, `productId`, `restockDate`, `restockPrice`) VALUES
(1, 1, 1, '2020-12-21', 10000),
(8, 1, 4, '2020-12-27', 50000),
(11, 1, 4, '2021-01-12', 1200000),
(12, 3, 4, '2021-01-12', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `salesId` int(11) UNSIGNED NOT NULL,
  `customerId` int(11) UNSIGNED NOT NULL,
  `salesDate` date NOT NULL,
  `subTotal` int(11) UNSIGNED DEFAULT NULL,
  `paid` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`salesId`, `customerId`, `salesDate`, `subTotal`, `paid`) VALUES
(12, 7, '2020-12-27', 9000, 9000),
(13, 5, '2020-12-27', 20000, 20000),
(15, 5, '2020-12-28', 4000, 4000),
(16, 7, '2020-12-28', 4000, 4000),
(17, 7, '2020-12-29', 15000, 15000),
(18, 7, '2020-12-29', 4000, 4000),
(19, 7, '2021-01-11', 10000, 10000),
(20, 3, '2021-01-11', 5000, 5000),
(21, 3, '2021-01-12', 45000, 45000),
(22, 5, '2021-01-12', 10000, 10000),
(23, 3, '2021-01-12', 20000, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `salesdetails`
--

CREATE TABLE `salesdetails` (
  `detailId` int(11) UNSIGNED NOT NULL,
  `salesId` int(11) UNSIGNED NOT NULL,
  `productId` int(11) UNSIGNED NOT NULL,
  `qty` int(11) UNSIGNED NOT NULL,
  `total` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salesdetails`
--

INSERT INTO `salesdetails` (`detailId`, `salesId`, `productId`, `qty`, `total`) VALUES
(8, 12, 4, 2, 4000),
(10, 13, 4, 5, 10000),
(13, 15, 4, 2, 4000),
(14, 16, 4, 2, 4000),
(16, 18, 4, 2, 4000),
(18, 20, 1, 1, 5000),
(19, 21, 1, 5, 25000),
(20, 21, 4, 10, 20000),
(21, 22, 4, 5, 10000),
(22, 23, 4, 10, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplierId` int(11) UNSIGNED NOT NULL,
  `supplierName` varchar(30) NOT NULL,
  `supplierAddress` text NOT NULL,
  `cityId` int(11) UNSIGNED NOT NULL,
  `supplierContact` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplierId`, `supplierName`, `supplierAddress`, `cityId`, `supplierContact`) VALUES
(1, 'Aneka', 'Jl. Duta Kencana II', 1, 'aneka@gmail.com'),
(2, 'Joyko', 'Jl. Pinangsia 3', 3, 'joyko@gmail.com'),
(3, 'Toko Pak Hadi', 'Jl. Kencana Indah IV', 2, '087891495930'),
(6, 'PT. Kencana Indah', 'Jl. Medan Merdeka 3', 2, '880839203');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`cityId`),
  ADD KEY `provinceId` (`provinceId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`),
  ADD KEY `cityId` (`cityId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`username`),
  ADD KEY `cityId` (`cityId`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `categoryId` (`categoryId`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`provinceId`);

--
-- Indexes for table `restock`
--
ALTER TABLE `restock`
  ADD PRIMARY KEY (`restockId`),
  ADD KEY `supplierId` (`supplierId`),
  ADD KEY `productId` (`productId`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`salesId`),
  ADD KEY `customerId` (`customerId`);

--
-- Indexes for table `salesdetails`
--
ALTER TABLE `salesdetails`
  ADD PRIMARY KEY (`detailId`),
  ADD KEY `salesId` (`salesId`),
  ADD KEY `productId` (`productId`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierId`),
  ADD KEY `cityId` (`cityId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `cityId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `productId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `province`
--
ALTER TABLE `province`
  MODIFY `provinceId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `restock`
--
ALTER TABLE `restock`
  MODIFY `restockId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `salesId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `salesdetails`
--
ALTER TABLE `salesdetails`
  MODIFY `detailId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplierId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `city_ibfk_1` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `restock`
--
ALTER TABLE `restock`
  ADD CONSTRAINT `restock_ibfk_1` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`supplierId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `restock_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `inventory` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salesdetails`
--
ALTER TABLE `salesdetails`
  ADD CONSTRAINT `salesdetails_ibfk_1` FOREIGN KEY (`salesId`) REFERENCES `sales` (`salesId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salesdetails_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `inventory` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `supplier`
--
ALTER TABLE `supplier`
  ADD CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
