package com.dms.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dms.entity.Admin;
import com.dms.entity.User;
import com.sun.org.apache.bcel.internal.generic.NEW;



public class Demo {
public static void main(String[] args) {
	User user = new User();
	Admin admin = new Admin();
	user.setAdmin(admin);
	admin.setName("qwe");
	admin.setName("qw");
	System.out.println(user.getAdmin().getName());
	
}
}
