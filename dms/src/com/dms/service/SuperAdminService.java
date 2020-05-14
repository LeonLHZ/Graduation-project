package com.dms.service;

import java.sql.SQLException;

import com.dms.entity.SuperAdmin;

public interface SuperAdminService {
SuperAdmin login(String said)throws Exception;

void updatePassword(String username, String md5) throws SQLException;

Boolean checkPassword(String username, String md5) throws SQLException;

SuperAdmin update(SuperAdmin superAdmin)throws Exception;


}
