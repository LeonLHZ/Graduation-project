package com.dms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dms.dao.AnnouncementDao;
import com.dms.entity.Admin;
import com.dms.entity.Announcement;
import com.dms.entity.SuperAdmin;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.MyConventer;

public class AnnouncementDaoImpl implements AnnouncementDao{

	@Override
	public void release(Announcement announcement) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into announcement values(?,?,?,?,?)";
		qr.update(sql,announcement.getBid(),announcement.getReleasetime(),announcement.getContent(),announcement.getAid(),announcement.getAnnid());
	}

	@Override
	public List<Announcement> findAllAnnouncement(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from announcement a inner join admin b on a.aid = b.aid where a.bid=? order by a.releasetime";
		List<Map<String, Object>>list=qr.query(sql,new MapListHandler(),bid);
		List<Announcement>aList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			Admin admin = new Admin();
			BeanUtils.populate(admin , map);
			Announcement announcement = new Announcement();
			BeanUtils.populate(announcement , map);
			announcement.setAdmin(admin);
			aList.add(announcement);
		}
		
		return aList;
	}

	@Override
	public void delete(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from announcement where annid = ?";
		qr.update(sql,aid);
	}

	@Override
	public List<Announcement> findAllAnnouncementBySuper(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from announcement a inner join superadmin b on a.bid = b.said where a.bid=? order by a.releasetime";
		List<Map<String, Object>>list=qr.query(sql,new MapListHandler(),uid);
		List<Announcement>aList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			SuperAdmin admin = new SuperAdmin();
			BeanUtils.populate(admin , map);
			Announcement announcement = new Announcement();
			BeanUtils.populate(announcement , map);
			announcement.setSuperadmin(admin);
			aList.add(announcement);
		}
		
		return aList;
	}

}
