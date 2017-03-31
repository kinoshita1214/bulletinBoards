-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: bulletin_boards
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES (2,'支店A'),(3,'支店B'),(4,'支店C'),(1,'本社');
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `text` varchar(500) NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (5,1,2,'内勤','2017-03-02 02:17:02','2017-03-02 02:17:02'),(7,1,2,'退社','2017-03-02 02:53:49','2017-03-02 02:53:49'),(9,1,1,'雪','2017-03-02 04:49:51','2017-03-02 04:49:51'),(11,1,1,'晴れ','2017-03-03 06:02:25','2017-03-03 06:02:25'),(12,1,3,'曇り','2017-03-06 01:06:16','2017-03-06 01:06:16'),(13,1,3,'晴れ','2017-03-06 01:29:10','2017-03-06 01:29:10'),(18,2,5,'bbbbbbb','2017-03-08 08:15:48','2017-03-08 08:15:48'),(19,15,19,'曇りになりました。','2017-03-09 07:31:51','2017-03-09 07:31:51'),(20,16,19,'雨になりました。','2017-03-09 07:32:41','2017-03-09 07:32:41'),(22,18,21,'着きました。','2017-03-09 07:35:43','2017-03-09 07:35:43'),(27,15,33,'12:00～13:00 休憩\r\n出勤時間は1時間','2017-03-13 04:48:43','2017-03-13 04:48:43'),(28,15,33,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2017-03-14 08:14:14','2017-03-14 08:14:14'),(29,15,35,'aaaaaaaaaaaaaa','2017-03-14 08:28:15','2017-03-14 08:28:15'),(30,16,36,'両方参加します。','2017-03-21 00:04:41','2017-03-21 00:04:41'),(31,17,37,'承知しました。','2017-03-21 00:07:01','2017-03-21 00:07:01'),(32,17,36,'両方参加でお願いします。','2017-03-21 00:08:02','2017-03-21 00:08:02'),(33,18,37,'了解です。','2017-03-21 00:08:59','2017-03-21 00:08:59'),(34,18,36,'両方参加します。\r\nラケットがないので貸してもらってよろしいですか？','2017-03-21 00:11:20','2017-03-21 00:11:20'),(35,19,37,'承知しました。\r\n','2017-03-21 00:14:19','2017-03-21 00:14:19'),(36,20,37,'了解しました。','2017-03-21 00:15:15','2017-03-21 00:15:15'),(37,15,36,'本社情報さん、支店A店長さん了解です。','2017-03-21 00:34:27','2017-03-21 00:34:27'),(38,15,36,'支店A社員さん、ラケットお貸しするので大丈夫です。','2017-03-21 00:39:38','2017-03-21 00:39:38'),(39,15,41,'承知しました。','2017-03-23 08:14:22','2017-03-23 08:14:22'),(42,17,45,'支店A社員さんありがとうございます。','2017-03-23 08:54:34','2017-03-23 08:54:34');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (2,'情報管理'),(3,'支店長'),(4,'社員'),(1,'総務人事');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `managements`
--

DROP TABLE IF EXISTS `managements`;
/*!50001 DROP VIEW IF EXISTS `managements`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `managements` (
  `id` tinyint NOT NULL,
  `login_id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `branch_name` tinyint NOT NULL,
  `department_name` tinyint NOT NULL,
  `is_stoped` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `category` varchar(10) NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,'今日の天気','雨','天気','2017-03-02 01:49:35','2017-03-02 01:49:35'),(2,1,'今日の予定','仕事','予定','2017-03-02 01:50:19','2017-03-02 01:50:19'),(3,1,'今日の天気','雨','天気','2017-03-06 00:52:11','2017-03-06 00:52:11'),(4,1,'aaa','aaa','aaa','2017-03-06 05:49:32','2017-03-06 05:49:32'),(5,1,'bbb','bbb','bbb','2017-03-07 09:08:32','2017-03-07 09:08:32'),(11,1,'teat','test','test','2017-03-08 08:22:23','2017-03-08 08:22:23'),(12,10,'店長','店長','店長','2017-03-09 00:03:29','2017-03-09 00:03:29'),(13,6,'社員','社員','社員','2017-03-09 00:04:03','2017-03-09 00:04:03'),(15,1,'aaa','aaa','aaa','2017-03-09 01:16:32','2017-03-09 01:16:32'),(36,15,'【アワード対象】【3月】バドミントン部イベントのお知らせ','お疲れ様です、\r\nプラサイズ冨永です！\r\n\r\n徐々に気温も上がってきて、\r\n緑が増えてきますね。\r\n春の足音聞こえます♪\r\n\r\nただ季節の変わり目ですから、\r\n体調には気を付けましょう！\r\n油断大敵。\r\n\r\nリリースも近いですしね。\r\n（個人的な話 笑）\r\n油断大敵。\r\n\r\nそれでも気分転換は必要です！\r\n張り切ってバドミントンしましょ！\r\n\r\n経験者はもちろん！初心者も大歓迎♪\r\n適度な運動と親睦を深める事が\r\n目的なので楽しくやりましょ！\r\n\r\nまた、バドミントン後は親睦会を\r\n開催しますので、よければ参加してください。\r\n\r\n懇親会の人数を把握したいので、\r\n懇親会の参加表明もお願いします！\r\n\r\nラケット持っていないという方は\r\n貸し出しますのでご安心を。\r\n\r\n駅にて集合してから体育館へ移動します。\r\n徒歩10分くらいです。\r\n\r\n日　時：3月25日(土)\r\n　　　　14:00~17:00\r\n               (集合13:45)\r\n場　所：八雲小学校\r\n最寄駅：東急東横線　都立大学駅\r\n持ち物：運動できる服装、室内シューズ\r\n\r\n※開催日の5日前に参加人数が5名未満\r\nの時は中止になる場合もあります。',' バドミントン部より','2017-03-21 00:02:53','2017-03-21 00:02:53'),(37,16,'【確認依頼】転籍に関する書類について','to:ICCOM、アビリティ・ネットの皆様\r\n\r\nお疲れ様です。管理部です。\r\n\r\n3月6日（月）に、転籍に関する書類を特定記録郵便にて\r\n郵送させて頂きました。\r\n\r\nお送りさせて頂いた書類は、本記事に添付したファイルの\r\n通りとなります。\r\nご自宅に郵送されましたら各自ご確認をお願い致します。\r\n\r\n※プラサイズ、ジャック総研の皆様については、別途ご連絡\r\n　させて頂きますので、宜しくお願い致します。','管理本部より ','2017-03-21 00:05:52','2017-03-21 00:05:52'),(40,21,'【社内制度】蔵書一覧','お疲れ様です。\r\n\r\n最新の蔵書一覧をアップしました。\r\n\r\n添付の利用マニュアルも、\r\n併せてご確認ください。\r\n\r\n不明な点は下記のアドレスまで。\r\nbsg_rou@baristride.co.jp\r\n\r\n以上、よろしくお願いします。','社内制度関連 ','2017-03-21 00:49:32','2017-03-21 00:49:32'),(43,22,'【社内制度】ウィズイン5制度(住宅補助制度)','本制度に関する内容は添付資料をご確認下さい。\r\nまた、申請書につきましては添付資料をご利用下さい。\r\n本制度に関するお問い合わせはこちらまで。\r\nbsg_rou@baristride.co.jp\r\n【概要】ウィズイン5制度.pdf\r\n02_住所変更・通勤経路変更 兼 住宅手当申請書(プラサイズ).xlsx\r\n02_住所変更・通勤経路変更 兼 住宅手当申請書(ICCOM).xlsx\r\n02_住所変更・通勤経路変更 兼 住宅手当申請書(アビリティネット).xlsx','社内制度関連','2017-03-23 08:21:31','2017-03-23 08:21:31'),(45,17,'バスケサークル活動のお知らせ','お疲れ様です。\r\nプラサイズ冨永です！\r\n\r\nやるやる詐欺で、、\r\nしばらく活動できていなかった\r\nバスケサークル。\r\n\r\n再度メンバーを招集させて\r\n頂こうとおもいます！\r\n\r\n経験者はもちろん、\r\n未経験者でも構いません。\r\nバスケしてみたい！\r\nバスケ興味ある！\r\n運動したい！\r\n等々。\r\n気になったら、\r\n参加してみてください！\r\n\r\n参加表明はコメント欄へ！\r\n\r\nしばらくの活動内容は、\r\nシュート練習 ＋ ゲーム\r\nでやってみようと思います。\r\n\r\n駅にて集合してから体育館へ移動します。\r\n徒歩10分くらいです。\r\n\r\n日　時：4月8日(土)\r\n　　　　9:00~12:00\r\n               (集合8:45)\r\n場　所：八雲小学校\r\n最寄駅：東急東横線　都立大学駅\r\n持ち物：運動できる服装、室内シューズ\r\n※ボール持っている方はボール持参お願いします！\r\n\r\n※開催日の5日前に参加人数が6名未満\r\nの時は中止になる場合もあります。','バスケ部より','2017-03-23 08:52:53','2017-03-23 08:52:53'),(46,0,'ｈｔｒ','ぜｒｔｊｘｙｃ','ｚちゅ','2017-03-31 05:41:10','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `user_comments`
--

DROP TABLE IF EXISTS `user_comments`;
/*!50001 DROP VIEW IF EXISTS `user_comments`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `user_comments` (
  `login_id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `user_id` tinyint NOT NULL,
  `post_id` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `branch_id` tinyint NOT NULL,
  `department_id` tinyint NOT NULL,
  `insert_date` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `user_posts`
--

DROP TABLE IF EXISTS `user_posts`;
/*!50001 DROP VIEW IF EXISTS `user_posts`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `user_posts` (
  `login_id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `user_id` tinyint NOT NULL,
  `subject` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `category` tinyint NOT NULL,
  `branch_id` tinyint NOT NULL,
  `department_id` tinyint NOT NULL,
  `insert_date` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(20) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `is_stoped` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (15,'admin1',1,1,'本社総務人事','JfQ7FIatlaE5jj7rPYO8QBABX8yb7bNbQy4AKY1QIfc',0),(16,'admin2',1,2,'本社情報管理','HBQrLQGqNOmja95IBkWlf9aeFBVdrPq1o_kle3f9yNg',0),(17,'admin3',2,3,'支店A店長','T8K1ZzogGtmx_APcs0bhuq1ENR2qBQPVU0tN_cxDMuA',0),(18,'admin4',2,4,'支店A社員','EQGYgxpCaAe8zZ299Utty1KYvF0xrEkGnguj0hDZcK4',0),(19,'admin5',3,3,'支店B店長','abVAZTjFwJWAzswMe6-_P5YCZ_-nR46J2DUtqBRSnLc',0),(20,'admin6',3,4,'支店B社員','0y_VMBGbFJvcPx7Rl7rvrEGEKNNJ66L6mDr7P6X7YNI',0),(21,'admin7',4,3,'支店C店長','peNUg_F0XA9PPelQhh1qrtSIwl2n00Kogno_x7DnMYY',0),(22,'admin8',4,4,'支店C社員','1SCSZxtBQf1dkoHQsD02RBKLGFtCQ8NzP9BHBab_4cU',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `managements`
--

/*!50001 DROP TABLE IF EXISTS `managements`*/;
/*!50001 DROP VIEW IF EXISTS `managements`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `managements` AS select `users`.`id` AS `id`,`users`.`login_id` AS `login_id`,`users`.`name` AS `name`,`branches`.`name` AS `branch_name`,`departments`.`name` AS `department_name`,`users`.`is_stoped` AS `is_stoped` from ((`users` join `branches`) join `departments`) where ((`users`.`branch_id` = `branches`.`id`) and (`users`.`department_id` = `departments`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_comments`
--

/*!50001 DROP TABLE IF EXISTS `user_comments`*/;
/*!50001 DROP VIEW IF EXISTS `user_comments`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_comments` AS select `users`.`login_id` AS `login_id`,`users`.`name` AS `name`,`comments`.`id` AS `id`,`comments`.`user_id` AS `user_id`,`comments`.`post_id` AS `post_id`,`comments`.`text` AS `text`,`users`.`branch_id` AS `branch_id`,`users`.`department_id` AS `department_id`,`comments`.`insert_date` AS `insert_date` from (`users` join `comments`) where (`users`.`id` = `comments`.`user_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_posts`
--

/*!50001 DROP TABLE IF EXISTS `user_posts`*/;
/*!50001 DROP VIEW IF EXISTS `user_posts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_posts` AS select `users`.`login_id` AS `login_id`,`users`.`name` AS `name`,`posts`.`id` AS `id`,`posts`.`user_id` AS `user_id`,`posts`.`subject` AS `subject`,`posts`.`text` AS `text`,`posts`.`category` AS `category`,`users`.`branch_id` AS `branch_id`,`users`.`department_id` AS `department_id`,`posts`.`insert_date` AS `insert_date` from (`users` join `posts`) where (`users`.`id` = `posts`.`user_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-31 18:14:58
