-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Sep 2021 um 09:47
-- Server-Version: 10.4.19-MariaDB
-- PHP-Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `db_huibuh`
--
Drop DATABASE if EXISTS db_huibuh;
CREATE DATABASE db_huibuh;
-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag`
--

CREATE TABLE `auftrag` (
  `Auftragsnr` int(11) NOT NULL,
  `KID` int(11) NOT NULL,
  `Stunden` int(10) NOT NULL,
  `Datum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `auftrag`
--

INSERT INTO `auftrag` (`Auftragsnr`, `KID`, `Stunden`, `Datum`) VALUES
(1, 1, 123, NULL),
(5, 2, 80, NULL),
(6, 2, 54, NULL),
(7, 2, 96, NULL),
(8, 1, 453, NULL),
(9, 1, 122, NULL),
(10, 3, 6, NULL),
(11, 3, 23, NULL),
(12, 4, 77, NULL),
(13, 4, 11, NULL),
(14, 3, 0, NULL),
(15, 2, 0, '2021-08-31'),
(16, 2, 0, '2021-08-31'),
(17, 5, 0, '2021-08-31'),
(18, 5, 0, '2021-08-31');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kundendaten`
--

CREATE TABLE `kundendaten` (
  `KID` int(11) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `PLZ` varchar(10) NOT NULL,
  `Ort` varchar(20) NOT NULL,
  `Land` varchar(20) NOT NULL,
  `Ansprechpartner` varchar(40) NOT NULL,
  `Strasse` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `kundendaten`
--

INSERT INTO `kundendaten` (`KID`, `Name`, `PLZ`, `Ort`, `Land`, `Ansprechpartner`, `Strasse`) VALUES
(1, 'kunde1', '12345', 'asdfg', 'land1', 'ansprech1', 'strasse 1'),
(2, 'kunde2', 'land 2', 'ort2', 'ansprech2', 'starsse 2', '12345'),
(3, 'kunde3', '12345', 'yxcv12', 'asdfg', 'ansprech3', 'strasse3'),
(4, 'kunde4', '12345', 'wert', 'tret', 'ansprech4', 'strasse4'),
(5, 'Kunde 5', '31245', 'Ort5', 'Land5', 'Ansprech5', 'Strasse 5');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechnung`
--

CREATE TABLE `rechnung` (
  `Re-Nr` int(11) NOT NULL,
  `KID` int(11) NOT NULL,
  `GegID` int(11) NOT NULL,
  `Auftragsnr` int(11) NOT NULL,
  `SID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `regeg`
--

CREATE TABLE `regeg` (
  `GegID` int(11) NOT NULL,
  `Gegenstand` varchar(100) NOT NULL,
  `Honorar` float NOT NULL,
  `UST` float NOT NULL,
  `Stundensatz` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `regeg`
--

INSERT INTO `regeg` (`GegID`, `Gegenstand`, `Honorar`, `UST`, `Stundensatz`) VALUES
(1, 'regeg 1', 0, 0.19, 40),
(2, 'regeg 2', 1200, 0, 0),
(3, 'regeg 3', 0, 0.19, 121.01),
(5, 'regeg4', 0, 0.19, 1222.33),
(6, 'Regeg5', 0, 0.19, 42.58),
(7, 'Regeg5', 1800, 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rekorrekt`
--

CREATE TABLE `rekorrekt` (
  `korrektRe-Nr` int(11) NOT NULL,
  `Re-Nr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `renrregeg`
--

CREATE TABLE `renrregeg` (
  `ID` int(11) NOT NULL,
  `Re-Nr` int(11) NOT NULL,
  `GegID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stammdaten`
--

CREATE TABLE `stammdaten` (
  `SID` int(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Strasse` varchar(20) NOT NULL,
  `Hausnr` varchar(10) NOT NULL,
  `PLZ` varchar(5) NOT NULL,
  `Ort` varchar(20) NOT NULL,
  `Steuernummer` varchar(40) NOT NULL,
  `Benutzer` varchar(40) NOT NULL,
  `Passwort` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `stammdaten`
--

INSERT INTO `stammdaten` (`SID`, `Name`, `Strasse`, `Hausnr`, `PLZ`, `Ort`, `Steuernummer`, `Benutzer`, `Passwort`) VALUES
(10, 'stammName', 'stammStrasse', '122', '12345', 'stammOrt', 'steuernummer', 'test', 'test');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  ADD PRIMARY KEY (`Auftragsnr`),
  ADD KEY `KID` (`KID`) USING BTREE;

--
-- Indizes für die Tabelle `kundendaten`
--
ALTER TABLE `kundendaten`
  ADD PRIMARY KEY (`KID`);

--
-- Indizes für die Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  ADD PRIMARY KEY (`Re-Nr`),
  ADD KEY `KID` (`KID`) USING BTREE,
  ADD KEY `GegID` (`GegID`) USING BTREE,
  ADD KEY `Auftragsnr` (`Auftragsnr`) USING BTREE,
  ADD KEY `SID` (`SID`) USING BTREE;

--
-- Indizes für die Tabelle `regeg`
--
ALTER TABLE `regeg`
  ADD PRIMARY KEY (`GegID`);

--
-- Indizes für die Tabelle `rekorrekt`
--
ALTER TABLE `rekorrekt`
  ADD PRIMARY KEY (`korrektRe-Nr`),
  ADD KEY `Re-nr` (`Re-Nr`) USING BTREE;

--
-- Indizes für die Tabelle `renrregeg`
--
ALTER TABLE `renrregeg`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GegID` (`GegID`) USING BTREE,
  ADD KEY `Re-nr` (`Re-Nr`);

--
-- Indizes für die Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  ADD PRIMARY KEY (`SID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  MODIFY `Auftragsnr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT für Tabelle `kundendaten`
--
ALTER TABLE `kundendaten`
  MODIFY `KID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  MODIFY `Re-Nr` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `regeg`
--
ALTER TABLE `regeg`
  MODIFY `GegID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `rekorrekt`
--
ALTER TABLE `rekorrekt`
  MODIFY `korrektRe-Nr` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `renrregeg`
--
ALTER TABLE `renrregeg`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  MODIFY `SID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  ADD CONSTRAINT `auftrag_ibfk_1` FOREIGN KEY (`KID`) REFERENCES `kundendaten` (`KID`);

--
-- Constraints der Tabelle `rechnung`
--
ALTER TABLE `rechnung`
  ADD CONSTRAINT `AuftragsnrCon` FOREIGN KEY (`Auftragsnr`) REFERENCES `auftrag` (`Auftragsnr`),
  ADD CONSTRAINT `KIDCon` FOREIGN KEY (`KID`) REFERENCES `auftrag` (`KID`),
  ADD CONSTRAINT `SIDCon` FOREIGN KEY (`SID`) REFERENCES `stammdaten` (`SID`),
  ADD CONSTRAINT `rechnung_ibfk_1` FOREIGN KEY (`Re-Nr`) REFERENCES `rekorrekt` (`Re-Nr`);

--
-- Constraints der Tabelle `renrregeg`
--
ALTER TABLE `renrregeg`
  ADD CONSTRAINT `GegIDCon1` FOREIGN KEY (`GegID`) REFERENCES `regeg` (`GegID`),
  ADD CONSTRAINT `ReNRCon` FOREIGN KEY (`Re-Nr`) REFERENCES `rechnung` (`Re-Nr`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
