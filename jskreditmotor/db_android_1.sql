-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table db_android_1.kredit
CREATE TABLE IF NOT EXISTS `kredit` (
  `invoice` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idkreditor` int(25) NOT NULL,
  `kdmotor` varchar(10) DEFAULT NULL,
  `hrgtunai` varchar(25) DEFAULT NULL,
  `dp` varchar(25) DEFAULT NULL,
  `hrgkredit` varchar(25) DEFAULT NULL,
  `bunga` varchar(25) DEFAULT NULL,
  `lama` varchar(25) DEFAULT NULL,
  `totalkredit` varchar(25) DEFAULT NULL,
  `angsuran` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`invoice`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table db_android_1.kreditor
CREATE TABLE IF NOT EXISTS `kreditor` (
  `idkreditor` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `pekerjaan` varchar(50) DEFAULT NULL,
  `telp` varchar(16) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idkreditor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table db_android_1.motor
CREATE TABLE IF NOT EXISTS `motor` (
  `idmotor` int(11) NOT NULL AUTO_INCREMENT,
  `kdmotor` varchar(10) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmotor`),
  UNIQUE KEY `kdmotor` (`kdmotor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table db_android_1.petugas
CREATE TABLE IF NOT EXISTS `petugas` (
  `idpetugas` int(11) NOT NULL AUTO_INCREMENT,
  `kdpetugas` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idpetugas`),
  UNIQUE KEY `kdpetugas` (`kdpetugas`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
