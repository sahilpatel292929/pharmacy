
INSERT INTO TBL_ACCOUNT_HEAD_CREATION (id,account_head_name,mode_status) VALUES (1,'Assets','A');
INSERT INTO TBL_ACCOUNT_HEAD_CREATION (id,account_head_name,mode_status) VALUES (2,'Liability','A');
INSERT INTO TBL_ACCOUNT_HEAD_CREATION (id,account_head_name,mode_status) VALUES (3,'Income','A');
INSERT INTO TBL_ACCOUNT_HEAD_CREATION (id,account_head_name,mode_status) VALUES (4,'Expenses ','A');

INSERT INTO TBL_GST_TYPE (`id`,`gst_type_name`,`mode_status`) VALUES (1,'UnRegistered','A');
INSERT INTO TBL_GST_TYPE (`id`,`gst_type_name`,`mode_status`) VALUES (2,'Composite','A');
INSERT INTO TBL_GST_TYPE (`id`,`gst_type_name`,`mode_status`) VALUES (3,'Register ','A');

INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (1,'Bank','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (2,'Cash','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (3,'Wallet','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (4,'Sundry debtors','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (5,'Sundry creditors','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (6,'Direct income','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (7,'Indirect income','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (8,'Direct expenses','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (9,'Indirect expenses','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (10,'Asset','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (11,'Loan','A');
INSERT INTO `TBL_ACCOUNT_TYPE_CREATION` (`id`,`account_type_name`,`mode_status`) VALUES (12,'Investment','A');

INSERT INTO `TBL_GST_PERCENTAGE` (`id`,`name`,`status`,`value`) VALUES (1,'0%','A','0');
INSERT INTO `TBL_GST_PERCENTAGE` (`id`,`name`,`status`,`value`) VALUES (2,'5%','A','5');
INSERT INTO `TBL_GST_PERCENTAGE` (`id`,`name`,`status`,`value`) VALUES (3,'12%','A','12');
INSERT INTO `TBL_GST_PERCENTAGE` (`id`,`name`,`status`,`value`) VALUES (4,'18%','A','18');
INSERT INTO `TBL_GST_PERCENTAGE` (`id`,`name`,`status`,`value`) VALUES (5,'28%','A','28');

INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (1,'A','Customer name mandatory');
INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (2,'A','Customer name and mobile no mandatory');
INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (3,'A','Customer name and address mandatory');
INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (4,'A','Customer name, mobile no and address mandatory');
INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (5,'A','Customer name and doctor name mandatory');
INSERT INTO TBL_WARNING_CREATION (`id`,`mode_status`,`warning_name`) VALUES (6,'A','Customer name, address and doctor name, address mandatory');


