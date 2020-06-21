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
	 * 이벤트 추가
	 * @param event
	 * @throws SQLException
	 */
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
	/**
	 * 이벤트 이미지 타입별로 받아오기
	 * @param eventNo
	 * @param eventType
	 * @return
	 */
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
	/**
	 * 현재 상태에 따라서 종료, 시작을 자동으로 해주는 토글 이벤트 메소드
	 * @param event
	 * @throws SQLException
	 */
	public void eventStartToggle(EventDto event) throws SQLException {
		if(event.isEnded() == false) {
			event.setEnded(true);
		} else {
			event.setEnded(false);
		}
		eventDao.updateEvent(event);
	}
	/**
	 * 이미지 타입과 번호를 받아서 해당 이미지 테이블의 데이터를 null 로 바꾼다.
	 * @param eventNo
	 * @param imageType
	 */
	public void deleteEventImage(int eventNo, String imageType) {
		eventDao.updateEventImage(eventNo, "", imageType);
	}
	/**
	 * 해당 이미지 넘버를 받아와 하나의 객체로 변환한다.
	 * @param eventNo
	 * @return
	 */
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
	
}
