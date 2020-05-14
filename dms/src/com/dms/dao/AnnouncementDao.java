package com.dms.dao;

import java.util.List;

import com.dms.entity.Announcement;

public interface AnnouncementDao {

	void release(Announcement announcement)throws Exception;

	List<Announcement> findAllAnnouncement(String bid)throws Exception;

	void delete(String aid)throws Exception;

	List<Announcement> findAllAnnouncementBySuper(String uid)throws Exception;

}
