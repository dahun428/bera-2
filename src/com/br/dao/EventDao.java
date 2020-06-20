package com.br.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.br.dto.EventDto;
import com.br.utils.ConnectionUtil;
import com.br.utils.JdbcHelper;
import com.br.utils.JdbcHelper.RowMapper;
import com.br.utils.QueryUtil;

public class EventDao {
	
	/**
	 * pagenation index 에 해당하는 진행중인 썸네일리스트를 가져온다.
	 * @param beginIndex pagenation 시작
	 * @param endIndex	pagenation 끝
	 * @return
	 */
	public List<EventDto> getProgressEventListThumbnailType(int beginIndex, int endIndex){
		String query = QueryUtil.getSQL("event.getProgressEventListThumbnailType");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		}, beginIndex, endIndex);
	}
	/**
	 * 진행중인 썸네일 리스트의 총 갯수를 가져온다.
	 * @return
	 */
	public int getProgressEventListThumbnailTypeTotalRows() {
		String query = QueryUtil.getSQL("event.getProgressEventListThumbnailTypeCount");
		return JdbcHelper.selectOne(query, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt("cnt");
			}
		});
	}
	/**
	 * 진행중인 배너 리스트의 총 갯수를 가져온다.
	 * @return
	 */
	public int getProgressEventListBannerTypeTotalRows() {
		String query = QueryUtil.getSQL("event.getProgressEventListBannerTypeCount");
		return JdbcHelper.selectOne(query, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt("cnt");
			}
		});
	}
	/**
	 * pagenation index 에 해당하는 진행중인 배너 가져온다.
	 * @param beginIndex pagenation 시작
	 * @param endIndex	pagenation 끝
	 * @return
	 */
	public List<EventDto> getProgressEventListBannerType(int beginIndex, int endIndex){
		String query = QueryUtil.getSQL("event.getProgressEventListBannerType");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		}, beginIndex, endIndex);
	}
	/**
	 * 종료된 이벤트 리스트의 총 갯수를 가져온다.
	 * @return
	 */
	public int getEndedEventListThumbnailTypeTotalRows() {
		String query = QueryUtil.getSQL("event.getEndedEventListThumbnailTypeTotalRows");
		return JdbcHelper.selectOne(query, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt("cnt");
			}
		});
	}
	/**
	 * pagenation index 에 해당하는 종료된 이벤트 리스트를 가져온다.
	 * @param beginIndex pagenation 시작
	 * @param endIndex	pagenation 끝
	 * @return
	 */
	public List<EventDto> getEndedEventListThumbnailType(int beginIndex, int endIndex){
		String query = QueryUtil.getSQL("event.getEndedEventListThumbnailType");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		}, beginIndex, endIndex);
	}
	/**
	 * 종료된 배너 리스트의 총 갯수를 가져온다.
	 * @return
	 */
	public int getEndedEventListBannerTypeTotalRows() {
		String query = QueryUtil.getSQL("event.getEndedEventListBannerTypeTotalRows");
		return JdbcHelper.selectOne(query, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt("cnt");
			}
		});
	}
	/**
	 * pagenation index 에 해당하는 종료된 배너 리스트를 가져온다.
	 * @param beginIndex pagenation 시작
	 * @param endIndex	pagenation 끝
	 * @return
	 */
	public List<EventDto> getEndedEventListBannerType(int beginIndex, int endIndex){
		String query = QueryUtil.getSQL("event.getEndedEventListBannerType");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		}, beginIndex, endIndex);
	}
	
	
	
	
	
	
	public List<EventDto> getAllEventProgress() {
		String query = QueryUtil.getSQL("event.getAllEventProgress");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		});
	}
	public List<EventDto> getAllEventTerminated() {
		String query = QueryUtil.getSQL("event.getAllEventTerminated");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		});
	}
	

	
	
	/**
	 * eventNo와 image 이름(path값) 을 전달받아 DB에 insert 한다.
	 * @param eventNo
	 * @param imagePath
	 * @param imageType :: (CHAR) "0" content, "1" thumbnail, "2" banner
	 */
	public void insertEventImagePath(int eventNo, String imagePath, String imageType) {
		
		String query = QueryUtil.getSQL("event.insertEventImagePath");
		JdbcHelper.insert(query, eventNo, imagePath, imageType);
	}
	/**
	 * eventDto 를 insert 한뒤 해당하는 이벤트 no 값을 받는다.
	 * @param event
	 * @return eventNo
	 * @throws SQLException
	 */
	public int insertEvent(EventDto event) throws SQLException {
		
		String query = QueryUtil.getSQL("event.insertEvent");
		String query2 = QueryUtil.getSQL("event.insertGetNo");
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, event.getTitle());
		pstmt.setString(2, event.getContent());
		pstmt.setDate(3, new java.sql.Date(event.getStartDate().getTime()));
		pstmt.setDate(4, new java.sql.Date(event.getEndDate().getTime()));
		pstmt.setBoolean(5, event.isBanner());
		pstmt.executeUpdate();
		
		int no = 0;
		pstmt = conn.prepareStatement(query2);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			no = rs.getInt("event_no");
			return no;
		} 
		
		ConnectionUtil.close(conn, pstmt, rs);
		
		return no;
	
	}
	/**
	 * detail.jsp 를 위해 imgType = 0 인  eventDto 객체만을 반환한다.
	 * @param eventNo
	 * @return
	 */
	public EventDto getEventContentByNo(int eventNo) {
		String query = QueryUtil.getSQL("event.getEventContentByNo");
		return JdbcHelper.selectOne(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				
				EventDto event = new EventDto();
				event.setNo(rs.getInt("event_no"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setStartDate(rs.getDate("start_date"));
				event.setEndDate(rs.getDate("end_date"));
				event.setEnded(rs.getBoolean("ended"));
				event.setBanner(rs.getBoolean("onlybanner"));
				event.setImagePath(rs.getString("image_path"));
				event.setEventType(rs.getString("image_type"));
				
				return event;
			}
		}, eventNo);
		
	}
	public EventDto getEventThumbnailByNo(int eventNo) {
		String query = QueryUtil.getSQL("event.getEventThumbnailByNo");
		return JdbcHelper.selectOne(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				
				EventDto event = new EventDto();
				event.setNo(rs.getInt("event_no"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setStartDate(rs.getDate("start_date"));
				event.setEndDate(rs.getDate("end_date"));
				event.setEnded(rs.getBoolean("ended"));
				event.setBanner(rs.getBoolean("onlybanner"));
				event.setImagePath(rs.getString("image_path"));
				event.setEventType(rs.getString("image_type"));
				
				return event;
			}
		}, eventNo);
		
	}
	public EventDto getEventBannerByNo(int eventNo) {
		String query = QueryUtil.getSQL("event.getEventBannerByNo");
		return JdbcHelper.selectOne(query, new RowMapper<EventDto>() {
			public EventDto mapRow(ResultSet rs) throws SQLException {
				
				EventDto event = new EventDto();
				event.setNo(rs.getInt("event_no"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setStartDate(rs.getDate("start_date"));
				event.setEndDate(rs.getDate("end_date"));
				event.setEnded(rs.getBoolean("ended"));
				event.setBanner(rs.getBoolean("onlybanner"));
				event.setImagePath(rs.getString("image_path"));
				event.setEventType(rs.getString("image_type"));
				
				return event;
			}
		}, eventNo);
		
	}
	public List<EventDto> getAllEventThumbnail(){
		
		String query = QueryUtil.getSQL("event.getAllEventThumbnail");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {

			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		});
	}
	public List<EventDto> getAllEventContent(){
		
		String query = QueryUtil.getSQL("event.getAllEventContent");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {

			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		});
	}
	public List<EventDto> getAllEventBanner(){
		
		String query = QueryUtil.getSQL("event.getAllEventBanner");
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {

			public EventDto mapRow(ResultSet rs) throws SQLException {
				return resultFunc(rs);
			}
		});
	}
	public void/*List<Event>*/ getAllEvent(/**/) {
		
		
	}

	/**
	 * 이벤트 넘버에 해당하는 EventDto List를 반환한다.
	 * @param eventNo
	 * @return
	 */
	public List<EventDto> getEventListByNo(int eventNo) {
		
		String query = QueryUtil.getSQL("event.getEventListByNo");
		
		return JdbcHelper.selectList(query, new RowMapper<EventDto>() {
			
			public EventDto mapRow(ResultSet rs) throws SQLException {
				
				EventDto event = new EventDto();
				event.setNo(rs.getInt("event_no"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setStartDate(rs.getDate("start_date"));
				event.setEndDate(rs.getDate("end_date"));
				event.setEnded(rs.getBoolean("ended"));
				event.setBanner(rs.getBoolean("onlybanner"));
				event.setImagePath(rs.getString("image_path"));
				event.setEventType(rs.getString("image_type"));
				
				return event;
			}
			
		}, eventNo);
		
	}
	public void/*List<Event>*/ getEndedEvent() {}
	public void deleteEventImage(int eventNo, String imageType) {
		String query = QueryUtil.getSQL("event.deleteEventImage");
		JdbcHelper.update(query, imageType, eventNo);
	}
	
	private EventDto resultFunc(ResultSet rs) throws SQLException {
	
		EventDto event = new EventDto();
		event.setNo(rs.getInt("event_no"));
		event.setTitle(rs.getString("title"));
		event.setContent(rs.getString("content"));
		event.setStartDate(rs.getDate("start_date"));
		event.setEndDate(rs.getDate("end_date"));
		event.setEnded(rs.getBoolean("ended"));
		event.setBanner(rs.getBoolean("onlybanner"));
		event.setImagePath(rs.getString("image_path"));
		event.setEventType(rs.getString("image_type"));
		
		return event;
	}
	
}


