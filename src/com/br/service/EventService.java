package com.br.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.dao.EventDao;
import com.br.dto.EventDto;

public class EventService {

	EventDao eventDao = new EventDao();
	//진행중인 이벤트 리스트
	public List<EventDto> getProgressEventListThumbnailType(int beginIndex, int endIndex){
		return eventDao.getProgressEventListThumbnailType(beginIndex, endIndex);
	}
	//진행중인 이벤트 리스트 총 갯수
	public int getProgressEventListThumbnailTypeTotalRows() {
		return eventDao.getProgressEventListThumbnailTypeTotalRows();
	}
	//진행중인 배너 리스트
	public List<EventDto> getProgressEventListBannerType(int beginIndex, int endIndex){
		return eventDao.getProgressEventListBannerType(beginIndex, endIndex);
	}
	//진행중인 배너 리스트 총 갯수
	public int getProgressEventListBannerTypeTotalRows() {
		return eventDao.getProgressEventListBannerTypeTotalRows();
	}
	//종료된 이벤트 리스트
	public List<EventDto> getEndedEventListThumbnailType(int beginIndex, int endIndex){
		return eventDao.getEndedEventListThumbnailType(beginIndex, endIndex);
	}
	//종료된 이벤트 리스트 총 갯수
	public int getEndedEventListThumbnailTypeTotalRows() {
		return eventDao.getEndedEventListThumbnailTypeTotalRows();
	}
	//종료된 배너 리스트
	public List<EventDto> getEndedEventListBannerType(int beginIndex, int endIndex){
		return eventDao.getEndedEventListBannerType(beginIndex, endIndex);
	}
	//종료된 배너 리스트 총 갯수
	public int getEndedEventListBannerTypeTotalRows() {
		return eventDao.getEndedEventListBannerTypeTotalRows();
	}
	
	
	
	
	/**
	 * 진행중인 컨텐트 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventProgressContentType() {
		List<EventDto> eventList = eventDao.getAllEventProgress();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("0".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	/**
	 * 진행중인 썸네일 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventProgressThumbnailType() {
		List<EventDto> eventList = eventDao.getAllEventProgress();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("1".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	/**
	 * 진행중인 배너 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventProgressBannerType() {
		List<EventDto> eventList = eventDao.getAllEventProgress();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("2".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	/**
	 * 종료된 컨텐트 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventTerminatedContentType() {
		List<EventDto> eventList = eventDao.getAllEventTerminated();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("0".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	/**
	 * 종료된 썸네일 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventTerminatedThumbnailType() {
		List<EventDto> eventList = eventDao.getAllEventTerminated();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("1".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	/**
	 * 종료된 배너 타입 이벤트 리스트 불러오기
	 * @return
	 */
	public List<EventDto> getAllEventTerminatedBannerType() {
		List<EventDto> eventList = eventDao.getAllEventTerminated();
		List<EventDto> events = new ArrayList<EventDto>();
		for(EventDto eventSelect : eventList) {
			if("2".equals(eventSelect.getEventType())) {
				EventDto event = eventSelectFunc(eventSelect);
				events.add(event);
			}
		}
		return events;
	}
	
	
	
	
	
	public void insertEvent(EventDto event) throws SQLException{
		
		int eventNo = 0;
		
		if(event != null) {
			eventNo = eventDao.insertEvent(event);
		}
		if(eventNo == 0) {
			return;
		}
		if(event.getContentImagePath() != null) {
			eventDao.insertEventImagePath(eventNo, event.getContentImagePath(), "0");
		}
		if(event.getThumbnailImagePath() != null) {
			eventDao.insertEventImagePath(eventNo, event.getThumbnailImagePath(), "1");
		}
		if(event.getBannerImagePath() != null) {
			eventDao.insertEventImagePath(eventNo, event.getBannerImagePath(), "2");
		}
		
	}
	public EventDto getEventImageByNo(int eventNo, String eventType) {
		
		EventDto event = null;
		
		if("content".equals(eventType)) {
			event = eventDao.getEventContentByNo(eventNo);
		} else if ("thumbnail".equals(eventType)) {
			event = eventDao.getEventThumbnailByNo(eventNo);
		} else if ("banner".equals(eventType)) {
			event = eventDao.getEventBannerByNo(eventNo);
		}
		
		return event;
	}
	
	public List<EventDto> getAllEventThumbnail(){
		return eventDao.getAllEventThumbnail();
	}
	public EventDto getEventContentByNo(int eventNo) {
		return eventDao.getEventContentByNo(eventNo);
	}
	public void deleteEventImage(int eventNo, String imageType) {
		eventDao.deleteEventImage(eventNo, imageType);
	}
	public EventDto getEventByNo(int eventNo) {
		List<EventDto> events = eventDao.getEventListByNo(eventNo);
		
		String contentImagePath = null;
		String thumbnailImagePath = null;
		String bannerImagePath = null;
		
		EventDto event = null;
		for(EventDto e : events) {
			event = new EventDto();
			event.setNo(e.getNo());
			event.setTitle(e.getTitle());
			event.setContent(e.getContent());
			event.setStartDate(e.getStartDate());
			event.setEndDate(e.getEndDate());
			event.setEnded(e.isEnded());
			event.setBanner(e.isBanner());
			if("0".equals(e.getEventType())){
				contentImagePath = e.getImagePath();
			} else if ("1".equals(e.getEventType())){
				thumbnailImagePath = e.getImagePath();
			} else if ("2".equals(e.getEventType())) {
				bannerImagePath = e.getImagePath();
			}
		}
		event.setContentImagePath(contentImagePath);
		event.setThumbnailImagePath(thumbnailImagePath);
		event.setBannerImagePath(bannerImagePath);
	
		return event;
	}
	
	private EventDto eventSelectFunc(EventDto eventSelect) {
			EventDto event = new EventDto();
			event.setNo(eventSelect.getNo());
			event.setTitle(eventSelect.getTitle());
			event.setContent(eventSelect.getContent());
			event.setStartDate(eventSelect.getStartDate());
			event.setEndDate(eventSelect.getEndDate());
			event.setEnded(eventSelect.isEnded());
			event.setBanner(eventSelect.isBanner());
			event.setImagePath(eventSelect.getImagePath());
			event.setEventType(eventSelect.getEventType());
			return event;
	}
	
}
