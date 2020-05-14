package com.dms.dao;

import java.sql.SQLException;

import com.dms.entity.SuperAdmin;

public interface SuperAdminDao {
SuperAdmin login(String said)throws Exception;

void updatePassword(String username, String md5) throws SQLException;

Boolean checkPassword(String username, String md5) throws SQLException;

void update(SuperAdmin superAdmin)throws SQLException;


}
