-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.18-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- shop 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `shop`;

-- 테이블 shop.auth_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `auth_info` (
  `auth_code` int(11) NOT NULL COMMENT '등급',
  `auth_memo` text NOT NULL COMMENT '등급설명',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`auth_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='권한 설명을 위한 테이블';

-- 테이블 데이터 shop.auth_info:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `auth_info` DISABLE KEYS */;
INSERT IGNORE INTO `auth_info` (`auth_code`, `auth_memo`, `createdate`) VALUES
	(0, '인턴', '2023-01-05 09:28:00'),
	(1, '직원', '2023-01-05 09:28:09'),
	(99, '매니저', '2023-01-05 09:28:24');
/*!40000 ALTER TABLE `auth_info` ENABLE KEYS */;

-- 테이블 shop.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `goods_code` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `cart_quantity` int(11) NOT NULL COMMENT '장바구니 수량',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`,`customer_id`),
  KEY `FK_cart_customer` (`customer_id`),
  CONSTRAINT `FK_cart_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_cart_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='장바구니 테이블(비로그인-session / 로그인-db)';

-- 테이블 데이터 shop.cart:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT IGNORE INTO `cart` (`goods_code`, `customer_id`, `cart_quantity`, `createdate`) VALUES
	(1, 'test', 2, '2023-01-05 15:18:59'),
	(2, 'test', 3, '2023-01-05 15:17:48'),
	(4, 'test', 1, '2023-01-06 13:50:05'),
	(6, 'test', 1, '2023-01-06 13:26:49');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- 테이블 shop.customer 구조 내보내기
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_code` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(100) NOT NULL,
  `customer_pw` varchar(100) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `customer_phone` varchar(100) NOT NULL,
  `point` int(11) NOT NULL COMMENT '현재 포인트',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`customer_code`),
  UNIQUE KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='고객 테이블';

-- 테이블 데이터 shop.customer:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT IGNORE INTO `customer` (`customer_code`, `customer_id`, `customer_pw`, `customer_name`, `customer_phone`, `point`, `createdate`) VALUES
	(1, 'test', '*A4B6157319038724E3560894F7F932C8886EBFCF', '고객테스트', '01012341234', 100, '2023-01-05 09:30:32'),
	(2, 'test3', '*A4B6157319038724E3560894F7F932C8886EBFCF', 'asd', '1010230123', 100, '2023-01-05 14:07:34'),
	(4, 'test4', '*A4B6157319038724E3560894F7F932C8886EBFCF', 'asd', '123123', 100, '2023-01-06 15:25:09');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- 테이블 shop.customer_address 구조 내보내기
CREATE TABLE IF NOT EXISTS `customer_address` (
  `address_code` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK용',
  `customer_id` varchar(100) NOT NULL,
  `address` text NOT NULL COMMENT '주소',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`address_code`),
  KEY `FK__customer` (`customer_id`),
  CONSTRAINT `FK__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='고객주소 테이블';

-- 테이블 데이터 shop.customer_address:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
INSERT IGNORE INTO `customer_address` (`address_code`, `customer_id`, `address`, `createdate`) VALUES
	(1, 'test', '서울 금천구 가산디지털1로 151 세진 이노플렉스, 306호 ', '2023-01-05 09:31:16');
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;

-- 테이블 shop.emp 구조 내보내기
CREATE TABLE IF NOT EXISTS `emp` (
  `emp_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '사원코드',
  `emp_id` varchar(100) NOT NULL,
  `emp_pw` varchar(100) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `active` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT 'Y(활성화) N(비활성화, 계정 사용 불가)',
  `auth_code` int(11) DEFAULT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`emp_code`),
  UNIQUE KEY `emp_id` (`emp_id`),
  KEY `FK_emp_auth_info` (`auth_code`),
  CONSTRAINT `FK_emp_auth_info` FOREIGN KEY (`auth_code`) REFERENCES `auth_info` (`auth_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='직원 테이블';

-- 테이블 데이터 shop.emp:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT IGNORE INTO `emp` (`emp_code`, `emp_id`, `emp_pw`, `emp_name`, `active`, `auth_code`, `createdate`) VALUES
	(1, 'admin', '*A4B6157319038724E3560894F7F932C8886EBFCF', '테스트', 'Y', 1, '2023-01-05 09:29:38');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;

-- 테이블 shop.goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods` (
  `goods_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `goods_title` varchar(100) NOT NULL COMMENT '상품이름',
  `goods_artist` varchar(100) NOT NULL COMMENT '가수이름',
  `goods_content` text NOT NULL COMMENT '앨범 설명',
  `goods_price` int(11) NOT NULL COMMENT '가격',
  `soldout` enum('Y','N') NOT NULL COMMENT 'Y(품절) N(재고있음)',
  `emp_id` varchar(100) NOT NULL COMMENT '상품 등록 직원 아이디',
  `hit` int(11) NOT NULL DEFAULT 0 COMMENT '상품 레벨-우선순위 선정시 사용',
  `view` int(11) NOT NULL COMMENT '조회수',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='상품 테이블';

-- 테이블 데이터 shop.goods:~8 rows (대략적) 내보내기
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT IGNORE INTO `goods` (`goods_code`, `goods_title`, `goods_artist`, `goods_content`, `goods_price`, `soldout`, `emp_id`, `hit`, `view`, `createdate`) VALUES
	(1, 'OTHER WAYS TO VENT', 'The internet', '132', 20000, 'N', '1', 0, 0, '2023-01-04 20:55:24'),
	(2, 'Glue', 'The internet', '123', 20000, 'N', '1', 0, 0, '2023-01-04 21:03:49'),
	(3, 'Broken Hearts Club', 'The internet', '123', 13000, 'N', '1', 0, 0, '2023-01-04 21:39:22'),
	(4, 'Abstract', 'The internet', '123', 13000, 'N', '1', 0, 0, '2023-01-04 21:43:40'),
	(5, 'SOS', 'The internet', '123', 20000, 'N', '1', 0, 0, '2023-01-04 21:46:37'),
	(6, 'Stuck In The Middle', 'The internet', '123', 11000, 'N', '1', 0, 0, '2023-01-04 21:48:12'),
	(7, 'Ego Death', 'The internet', '123', 25000, 'N', '1', 0, 0, '2023-01-05 12:12:42'),
	(9, 'Title', 'Kiana Lede', 'asd', 12000, 'N', 'admin', 0, 0, '2023-01-05 17:01:27'),
	(10, 'TitleA', 'Kiana LedeA', 'asdA', 12000, 'N', 'admin', 0, 0, '2023-01-06 11:15:29');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- 테이블 shop.goods_img 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods_img` (
  `goods_code` int(11) NOT NULL COMMENT '상품코드',
  `filename` varchar(100) NOT NULL COMMENT '파일이름',
  `origin_name` varchar(100) NOT NULL COMMENT '원본이름',
  `content_type` varchar(100) NOT NULL COMMENT '파일 타입',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`),
  CONSTRAINT `FK_goods_img_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='상품 이미지 테이블';

-- 테이블 데이터 shop.goods_img:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `goods_img` DISABLE KEYS */;
INSERT IGNORE INTO `goods_img` (`goods_code`, `filename`, `origin_name`, `content_type`, `createdate`) VALUES
	(1, 'OTHER WAYS TO VENT.jpg', 'OTHER WAYS TO VENT.jpg', 'image/jpeg', '2023-01-04 20:55:24'),
	(2, 'Glue.jpg', 'Glue.jpg', 'image/jpeg', '2023-01-04 21:03:49'),
	(3, 'Broken Hearts Club.jpg', 'Broken Hearts Club.jpg', 'image/jpeg', '2023-01-04 21:39:22'),
	(4, 'Abstract.jpg', 'Abstract.jpg', 'image/jpeg', '2023-01-04 21:43:40'),
	(5, 'SOS.jpg', 'SOS.jpg', 'image/jpeg', '2023-01-04 21:46:37'),
	(6, 'Stuck In The Middle.png', 'Stuck In The Middle.png', 'image/png', '2023-01-04 21:48:12'),
	(7, 'Ego Death.jpg', 'Ego Death.jpg', 'image/jpeg', '2023-01-05 12:12:42'),
	(9, 'Title.jpg', 'Title.jpg', 'image/jpeg', '2023-01-05 17:01:27'),
	(10, 'Ego Death1.jpg', 'Ego Death.jpg', 'image/jpeg', '2023-01-06 11:15:29');
/*!40000 ALTER TABLE `goods_img` ENABLE KEYS */;

-- 테이블 shop.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_code` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(200) NOT NULL,
  `notice_content` text NOT NULL,
  `emp_id` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`notice_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 테이블 데이터 shop.notice:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT IGNORE INTO `notice` (`notice_code`, `notice_title`, `notice_content`, `emp_id`, `createdate`) VALUES
	(1, 'test', '수정 테스트', '1', '2023-01-04 16:16:58'),
	(4, '12as', 'asdasdasd', 'admin', '2023-01-05 14:09:01');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 테이블 shop.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `order_code` int(11) NOT NULL AUTO_INCREMENT,
  `goods_code` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `address_code` int(11) NOT NULL COMMENT '배송지',
  `order_quantity` int(11) NOT NULL COMMENT '주문 수량',
  `order_state` enum('결제','취소','배송중','배송완료','구매확정') NOT NULL,
  `order_price` int(11) NOT NULL COMMENT '주문 가격',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`),
  KEY `FK_order_goods` (`goods_code`),
  KEY `FK_order_customer` (`customer_id`),
  KEY `FK_order_customer_address` (`address_code`),
  CONSTRAINT `FK_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_customer_address` FOREIGN KEY (`address_code`) REFERENCES `customer_address` (`address_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='주문 테이블';

-- 테이블 데이터 shop.orders:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT IGNORE INTO `orders` (`order_code`, `goods_code`, `customer_id`, `address_code`, `order_quantity`, `order_state`, `order_price`, `createdate`) VALUES
	(1, 1, 'test', 1, 2, '결제', 20000, '2023-01-05 09:31:47'),
	(2, 3, 'test', 1, 3, '결제', 123, '2023-01-06 14:51:11');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 테이블 shop.outid 구조 내보내기
CREATE TABLE IF NOT EXISTS `outid` (
  `id` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp() COMMENT '탈퇴일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='탈퇴, 새로운 아이디 생성시 모두 outid에 저장(직원, 고객 모두 포함)';

-- 테이블 데이터 shop.outid:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `outid` DISABLE KEYS */;
INSERT IGNORE INTO `outid` (`id`, `createdate`) VALUES
	('test2', '2023-01-06 11:32:04');
/*!40000 ALTER TABLE `outid` ENABLE KEYS */;

-- 테이블 shop.point_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `point_history` (
  `order_code` int(11) NOT NULL COMMENT '리뷰 작성된 주문만 지급',
  `point_kind` enum('적립','사용') NOT NULL COMMENT '적립(+) 사용(-)',
  `point` int(11) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`,`point_kind`),
  CONSTRAINT `FK_point_history_orders` FOREIGN KEY (`order_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='포인트 이력테이블';

-- 테이블 데이터 shop.point_history:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `point_history` DISABLE KEYS */;
INSERT IGNORE INTO `point_history` (`order_code`, `point_kind`, `point`, `createdate`) VALUES
	(1, '적립', 200, '2023-01-06 15:41:44');
/*!40000 ALTER TABLE `point_history` ENABLE KEYS */;

-- 테이블 shop.pw_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `pw_history` (
  `customer_id` varchar(100) NOT NULL,
  `pw` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`customer_id`,`pw`),
  CONSTRAINT `FK__pw_history_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='변경이력 고객별 최근 3개까지';

-- 테이블 데이터 shop.pw_history:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `pw_history` DISABLE KEYS */;
INSERT IGNORE INTO `pw_history` (`customer_id`, `pw`, `createdate`) VALUES
	('test4', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', '2023-01-06 15:26:20');
/*!40000 ALTER TABLE `pw_history` ENABLE KEYS */;

-- 테이블 shop.question 구조 내보내기
CREATE TABLE IF NOT EXISTS `question` (
  `question_code` int(11) NOT NULL AUTO_INCREMENT,
  `orders_code` int(11) NOT NULL COMMENT '조인 고객정보 확인',
  `category` enum('배송','반품','상품','교환','기타') NOT NULL,
  `question_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`question_code`),
  KEY `FK__orders` (`orders_code`),
  CONSTRAINT `FK__orders` FOREIGN KEY (`orders_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='문의 테이블(주문한 고객만 작성 가능)';

-- 테이블 데이터 shop.question:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT IGNORE INTO `question` (`question_code`, `orders_code`, `category`, `question_memo`, `createdate`) VALUES
	(1, 1, '배송', '배송문의', '2023-01-05 09:32:06'),
	(2, 1, '반품', '반품문의', '2023-01-06 13:51:07');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;

-- 테이블 shop.question_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `question_comment` (
  `comment_code` int(11) NOT NULL AUTO_INCREMENT,
  `question_code` int(11) NOT NULL,
  `comment_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`comment_code`),
  KEY `FK__question` (`question_code`),
  CONSTRAINT `FK__question` FOREIGN KEY (`question_code`) REFERENCES `question` (`question_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='문의사항 답변(댓글형태)';

-- 테이블 데이터 shop.question_comment:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `question_comment` DISABLE KEYS */;
INSERT IGNORE INTO `question_comment` (`comment_code`, `question_code`, `comment_memo`, `createdate`) VALUES
	(1, 1, '5일에 출고 완료되었습니다.', '2023-01-06 13:51:47');
/*!40000 ALTER TABLE `question_comment` ENABLE KEYS */;

-- 테이블 shop.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `order_code` int(11) NOT NULL,
  `review_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`),
  CONSTRAINT `FK_review_orders` FOREIGN KEY (`order_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='주문 테이블에서 구매확정된 주문만 고객이 작성 가능';

-- 테이블 데이터 shop.review:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT IGNORE INTO `review` (`order_code`, `review_memo`, `createdate`) VALUES
	(1, '아주 좋아요', '2023-01-06 13:52:07');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- 테이블 shop.site_counter 구조 내보내기
CREATE TABLE IF NOT EXISTS `site_counter` (
  `counter_date` date NOT NULL DEFAULT curdate(),
  `counter_num` int(11) NOT NULL,
  PRIMARY KEY (`counter_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='방문자수';

-- 테이블 데이터 shop.site_counter:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `site_counter` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_counter` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
