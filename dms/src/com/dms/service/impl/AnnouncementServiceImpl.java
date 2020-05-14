package com.dms.service.impl;

import java.util.List;

import com.dms.dao.AnnouncementDao;
import com.dms.entity.Announcement;
import com.dms.service.AnnouncementService;
import com.dms.utils.BeanFactory;

public class AnnouncementServiceImpl implements AnnouncementService{

	@Override
	public void release(Announcement announcement) throws Exception {
		AnnouncementDao ad = (AnnouncementDao) BeanFactory.getBean("AnnouncementDao");
		ad.release(announcement);
	}

	@Override
	public List<Announcement> findAllAnnouncement(String bid) throws Exception {
		AnnouncementDao ad = (AnnouncementDao) BeanFactory.getBean("AnnouncementDao");
		return ad.findAllAnnouncement(bid);
	}

	@Override
	public void delete(String aid) throws Exception {
		AnnouncementDao ad = (AnnouncementDao) BeanFactory.getBean("AnnouncementDao");
		ad.delete(aid);
		
	}

	@Override
	public List<Announcement> findAllAnnouncementBySuper(String uid) throws Exception {
		AnnouncementDao ad = (AnnouncementDao) BeanFactory.getBean("AnnouncementDao");
		return ad.findAllAnnouncementBySuper(uid);
	}

}
