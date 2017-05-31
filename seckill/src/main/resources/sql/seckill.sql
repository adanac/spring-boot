-- View structure for v1
-- ----------------------------
DROP VIEW IF EXISTS `v1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v1` AS SELECT *FROM seckill ;

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(IN v_seckill_id bigint, IN v_phone BIGINT,
IN v_kill_time TIMESTAMP, OUT r_result INT)
BEGIN
		DECLARE insert_count INT DEFAULT 0;
		START TRANSACTION;
		INSERT ignore INTO seckill_order (seckill_id, user_phone, create_time)
		VALUES(v_seckill_id, v_phone, v_kill_time);
		SELECT ROW_COUNT() INTO insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			SET r_result = -1;
		ELSEIF (insert_count < 0) THEN
			ROLLBACK ;
			SET r_result = -2;
		ELSE
			UPDATE seckill_product SET number = number - 1
			WHERE seckill_id = v_seckill_id AND end_time > v_kill_time
			AND start_time < v_kill_time AND number > 0;
			SELECT ROW_COUNT() INTO insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				SET r_result = 0;
			ELSEIF (insert_count < 0) THEN
				ROLLBACK;
				SET r_result = -2;
			ELSE
				COMMIT;
			SET r_result = 1;
			END IF;
		END IF;
	END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;