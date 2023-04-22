
-- tbl_account_head_creation 
INSERT INTO `secureac_securepump`.`tbl_account_head_creation` (`id`, `account_head_name`, `mode_status`) VALUES ('1', 'Acound Head1', 'A');
INSERT INTO `secureac_securepump`.`tbl_account_head_creation` (`id`, `account_head_name`, `mode_status`) VALUES ('2', 'Account Head2', 'A');
INSERT INTO `secureac_securepump`.`tbl_account_head_creation` (`id`, `account_head_name`, `mode_status`) VALUES ('3', 'Account Head 3', 'A');
------------
--tbl_account_type_creation
INSERT INTO `secureac_securepump`.`tbl_account_type_creation` (`id`, `account_type_name`, `mode_status`) VALUES ('1', 'Saving', 'A');
INSERT INTO `secureac_securepump`.`tbl_account_type_creation` (`id`, `account_type_name`, `mode_status`) VALUES ('2', 'Debit ', 'A');
INSERT INTO `secureac_securepump`.`tbl_account_type_creation` (`id`, `account_type_name`, `mode_status`) VALUES ('3 ', 'Credit ', 'A');
INSERT INTO `secureac_securepump`.`tbl_account_type_creation` (`id`, `account_type_name`, `mode_status`) VALUES ('4', 'Bank', 'A');

---
--tbl_area_creation
INSERT INTO `secureac_securepump`.`tbl_area_creation` (`id`, `area_name`, `route`, `mode_status`) VALUES ('1', 'Kukatapally', 'HYD', 'A');
INSERT INTO `secureac_securepump`.`tbl_area_creation` (`id`, `area_name`, `route`, `mode_status`) VALUES ('2', 'Miyapur', 'HYD', 'A');
INSERT INTO `secureac_securepump`.`tbl_area_creation` (`id`, `area_name`, `route`, `mode_status`) VALUES ('3', 'KPHB', 'HYD', 'A');

----
--tbl_boy_creation 

INSERT INTO `secureac_securepump`.`tbl_boy_creation` (`id`, `address1`, `address2`, `boy_name`, `mobile_no`, `salary`) VALUES ('1', 'hyd', 'Hydearabad', 'Umapathi', '520050779', '10000');
INSERT INTO `secureac_securepump`.`tbl_boy_creation` (`id`, `address1`, `address2`, `boy_name`, `mobile_no`, `salary`) VALUES ('2', 'BNg', 'Banaglore ', 'Eshu', '520050780', '1500');


---
--tbl_branch_creation

INSERT INTO `secureac_securepump`.`tbl_branch_creation` (`id`, `addreess`, `branch_name`, `contact_name`, `mobileno`) VALUES ('1', 'Hyderabad', 'Kukatpally ', 'Umapathi', '852663625');
INSERT INTO `secureac_securepump`.`tbl_branch_creation` (`id`, `addreess`, `branch_name`, `contact_name`, `mobileno`) VALUES ('2', 'Hyderabad', 'KPHB', 'uma', '85266362889');
INSERT INTO `secureac_securepump`.`tbl_branch_creation` (`id`, `addreess`, `branch_name`, `contact_name`, `location`, `mobileno`) VALUES ('3', 'Hyderabad ', 'Madhapur ', 'Eshu ', '', '777889999');

-------
--tbl_composition_creation
INSERT INTO `secureac_securepump`.`tbl_composition_creation` (`id`, `c_name`, `mode_status`) VALUES ('1', 'composition1', 'A');
INSERT INTO `secureac_securepump`.`tbl_composition_creation` (`id`, `c_name`, `mode_status`) VALUES ('2', 'Composte 2', 'A');
INSERT INTO `secureac_securepump`.`tbl_composition_creation` (`id`, `c_name`, `mode_status`) VALUES ('3', 'composite 5', 'A');


---------
--tbl_customer_creation
INSERT INTO `secureac_securepump`.`tbl_customer_creation` (`id`, `address1`, `created_by`, `created_date`, `credit_limit`, `customer_name`, `discount`, `due_days`, `gstno`, `gst_type`, `mobile_no`, `opening_bal`, `opening_bal_date`, `rate_slab`, `updated_date`) VALUES ('4', 'sanjay ', 'admin', '2020-12-20 14:51:42', '10000', 'Eshu ', '789', '15', '78945454ZGT', '18', '83969654575', '40586632', '2020-11-12 18:30:00', '7785', '2020-12-21 14:51:42');
INSERT INTO `secureac_securepump`.`tbl_customer_creation` (`id`, `address1`, `created_by`, `created_date`, `credit_limit`, `customer_name`, `discount`, `due_days`, `gstno`, `gst_type`, `mobile_no`, `opening_bal`, `opening_bal_date`, `rate_slab`, `updated_date`) VALUES ('5', 'Kukatpally ', 'admin', '2020-12-20 14:51:42', '25000', 'Srav', '78922', '150', '89557uytu', '189', '8795262221', '4785962', '2020-11-17 18:30:00', '74474', '2020-12-22 14:51:42');


-------
--tbl_discount_slab_creation

INSERT INTO `secureac_securepump`.`tbl_discount_slab_creation` (`id`, `discount`, `dicount_slab_name`, `from_amt`, `to_amt`) VALUES ('1', '10', 'Save pack', '10000', '150000');
INSERT INTO `secureac_securepump`.`tbl_discount_slab_creation` (`id`, `discount`, `dicount_slab_name`, `from_amt`, `to_amt`) VALUES ('2', '15', 'Premium Pck', '25000', '30000');
INSERT INTO `secureac_securepump`.`tbl_discount_slab_creation` (`id`, `discount`, `dicount_slab_name`, `from_amt`, `to_amt`) VALUES ('3', '25', 'Super Value ', '35000', '45000zazaasza');

---------
--tbl_gst_type
INSERT INTO `secureac_securepump`.`tbl_gst_type` (`id`, `gst_type_name`, `mode_status`) VALUES ('1', '0', 'A');
INSERT INTO `secureac_securepump`.`tbl_gst_type` (`id`, `gst_type_name`, `mode_status`) VALUES ('2', '5', 'A');
INSERT INTO `secureac_securepump`.`tbl_gst_type` (`id`, `gst_type_name`, `mode_status`) VALUES ('3', '12', 'A');
INSERT INTO `secureac_securepump`.`tbl_gst_type` (`id`, `gst_type_name`, `mode_status`) VALUES ('4', '18', 'A');

------
--tbl_hsn_sac_creation
INSERT INTO `secureac_securepump`.`tbl_hsn_sac_creation` (`id`, `descirption`, `hsn_name`, `mode_status`) VALUES ('1', 'hsn value fied', 'HSN1', 'A');
INSERT INTO `secureac_securepump`.`tbl_hsn_sac_creation` (`id`, `descirption`, `hsn_name`, `mode_status`) VALUES ('2', 'predefined value', 'HSN2', 'A');
INSERT INTO `secureac_securepump`.`tbl_hsn_sac_creation` (`id`, `descirption`, `hsn_name`, `mode_status`) VALUES ('3', 'automatical value', 'HS23', 'A');

---
--
INSERT INTO `secureac_securepump`.`tbl_manufacturer_creation` (`id`, `manufacturer_name`, `mode_status`) VALUES ('1', 'Appollo', 'A');
INSERT INTO `secureac_securepump`.`tbl_manufacturer_creation` (`id`, `manufacturer_name`, `mode_status`) VALUES ('2', 'Zenpack', 'A');
INSERT INTO `secureac_securepump`.`tbl_manufacturer_creation` (`id`, `manufacturer_name`, `mode_status`) VALUES ('3', 'Paracetomol', 'A');
INSERT INTO `secureac_securepump`.`tbl_manufacturer_creation` (`id`, `manufacturer_name`, `mode_status`) VALUES ('4', 'Zinc', 'A');

---
INSERT INTO `secureac_securepump`.`tbl_pack_creation` (`id`, `pack_name`, `quantity`, `mode_status`) VALUES ('1', 'qsk', '10', 'A');
INSERT INTO `secureac_securepump`.`tbl_pack_creation` (`id`, `pack_name`, `quantity`, `mode_status`) VALUES ('2', 'zpl', '15', 'A');
INSERT INTO `secureac_securepump`.`tbl_pack_creation` (`id`, `pack_name`, `quantity`, `mode_status`) VALUES ('3', 'qxk', '15', 'A');

-----

INSERT INTO `secureac_securepump`.`tbl_route_creation` (`id`, `route_name`, `mode_status`) VALUES ('1', 'KPHB', 'A');
INSERT INTO `secureac_securepump`.`tbl_route_creation` (`id`, `route_name`, `mode_status`) VALUES ('2', 'KUK', 'A');
INSERT INTO `secureac_securepump`.`tbl_route_creation` (`id`, `route_name`, `mode_status`) VALUES ('3', 'MDN', 'A');

---
INSERT INTO `secureac_securepump`.`tbl_scheduler_creation` (`id`, `scheduler_name`, `mode_status`, `warning_msg`, `warning`) VALUES ('1', 'morning', 'A', 'Red warning', 'Red');
INSERT INTO `secureac_securepump`.`tbl_scheduler_creation` (`id`, `scheduler_name`, `mode_status`, `warning_msg`, `warning`) VALUES ('2', 'Afternoon', 'A', 'Green Warning', 'Green');
INSERT INTO `secureac_securepump`.`tbl_scheduler_creation` (`id`, `scheduler_name`, `mode_status`, `warning_msg`, `warning`) VALUES ('3', 'Evening ', 'A', 'Orange Warning', 'Orange');


---
INSERT INTO `secureac_securepump`.`tbl_stock_creation` (`id`, `mode_status`, `stock_name`, `barnch_creation_id`) VALUES ('1', 'A', 'Entry', '2');
INSERT INTO `secureac_securepump`.`tbl_stock_creation` (`id`, `mode_status`, `stock_name`, `barnch_creation_id`) VALUES ('2', 'A', 'packere gene', '2');

---
INSERT INTO `secureac_securepump`.`tbl_store_type_creation` (`id`, `status`, `store_type_name`) VALUES ('1', 'A', 'Apollo');
INSERT INTO `secureac_securepump`.`tbl_store_type_creation` (`id`, `status`, `store_type_name`) VALUES ('2', 'A', 'Medplus');
INSERT INTO `secureac_securepump`.`tbl_store_type_creation` (`id`, `status`, `store_type_name`) VALUES ('3', 'A', 'Adithya');


----
INSERT INTO `secureac_securepump`.`tbl_warning_creation` (`id`, `mode_status`, `warning_name`) VALUES ('1', 'A', 'High');
INSERT INTO `secureac_securepump`.`tbl_warning_creation` (`id`, `mode_status`, `warning_name`) VALUES ('2', 'A', 'Low');

------

INSERT INTO `secure_pharma_db`.`tbl_group_creation` (`id`, `group_name`, `mode_status`) VALUES ('1', 'group1', 'A');
INSERT INTO `secure_pharma_db`.`tbl_group_creation` (`id`, `group_name`, `mode_status`) VALUES ('2', 'group2', 'A');


----------



