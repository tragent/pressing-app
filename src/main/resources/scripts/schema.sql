-- Create default permission if not exist
INSERT INTO permission(id, name, description) VALUES(NULL, 'Create user', NULL) ON DUPLICATE KEY UPDATE name='Create user';
SET @PermissionId := LAST_INSERT_ID();

-- Create default role if not exist
INSERT INTO role(id, name, description) VALUES(NULL, 'ROLE_ADMINISTRATION', NULL) ON DUPLICATE KEY UPDATE name='ROLE_ADMINISTRATION';
SET @RoleId := LAST_INSERT_ID();

-- Create default user if not exist
INSERT INTO user(id, first_name, last_name, username, password, telephone, is_active) 
	VALUES(NULL, 'Administrator', 'Administrator', 'Admin', '$2a$10$OSVh6T1Ah20r7NT9bAU5Xug8gEWYMegyHBu2XBARfE1xKPWRc0qNW', '(+237) 677996655', 1) 
	ON DUPLICATE KEY UPDATE username='Admin', password='$2a$10$OSVh6T1Ah20r7NT9bAU5Xug8gEWYMegyHBu2XBARfE1xKPWRc0qNW';
SET @userId := LAST_INSERT_ID();

INSERT IGNORE INTO `pressing`.`user_role` (`user_id`, `role_id`) VALUES (@UserId, @RoleId);

INSERT IGNORE INTO `pressing`.`role_permission` (`role_id`, `permission_id`) VALUES (@RoleId, @PermissionId);
