package kr.or.ddit.chat.team_chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;
import kr.or.ddit.chat.team_chat.service.ITeamChatService;
import kr.or.ddit.chat.team_chat_room.service.ITeamChatRoomService;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

public class SocketChatHandler extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(SocketChatHandler.class);

	private List<WebSocketSession> sessionList; // 소켓에 연결된 세션정보
	private Map<String, List<CrewVO>> map = null;
	
	@Resource(name="teamChatRoomService")
	private ITeamChatRoomService chatRoomService;
	@Resource(name="teamChatService")
	private ITeamChatService chatService;
	
	
	public SocketChatHandler() {
		sessionList = new ArrayList<>();
		map = new HashMap<String, List<CrewVO>>();
	}

	// 클라이언트가 웹소켓에 접속하여 연결이 맺어진 후에 호출
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String user = getUser(session);
		
		// roomID와 채팅방명 가져오기
		TeamChatVO teamChatVO = getRoomVO(session);
		// 채팅방 유저리스트 가져오기
		List<CrewVO> crewList = getCrewList(session);
		
		Map<String, Object> map = null;
		
		for (WebSocketSession currentSession : sessionList) {
			map = currentSession.getAttributes();
			TeamChatVO teamChatVO2 = (TeamChatVO) map.get("TEAM_INFO");
			
			if(teamChatVO.getChat_room_id_fk() == teamChatVO2.getChat_room_id_fk())
				currentSession.sendMessage(new TextMessage(user + ":님이 입장하셨습니다." ));
		}
		
		sessionList.add(session);
		logger.debug("채팅 접속 : {}", user);
	}

	// 클라이언트가 웹소켓에 메세지를 전송한 경우 : 모든 사용자에게 메세지를 전달
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug("소켓 session : {}", session);
		
		String user = getUser(session);
		// roomID와 채팅방명 가져오기
		TeamChatVO teamChatVO = getRoomVO(session);
		teamChatVO.setChat_content(message.getPayload());
		
		logger.debug("메세지전송 = {} : {}", user, message.getPayload());
		Map<String, Object> map = null;
		
		chatService.insertChat(teamChatVO);
		
		for (WebSocketSession currentSession : sessionList) {
			map = currentSession.getAttributes();
			TeamChatVO teamChatVO2 = (TeamChatVO) map.get("TEAM_INFO");
			
			if(teamChatVO.getChat_room_id_fk() == teamChatVO2.getChat_room_id_fk())
				currentSession.sendMessage(new TextMessage(user + ":" + message.getPayload()));
		}
	}

	// 클라이언트 연결이 종료된경우 : 연결 리스트에서 해당 사용자 제거
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String user = getUser(session);
		// roomID와 채팅방명 가져오기
		
//		Map<String, Object> map = null;
//		
//		for (WebSocketSession currentSession : sessionList) {
//			map = currentSession.getAttributes();
//			TeamChatVO teamChatVO2 = (TeamChatVO) map.get("TEAM_INFO");
//			
//			TeamChatVO teamChatVO = getRoomVO(session);
//			
//			if(teamChatVO.getChat_room_id_fk() == teamChatVO2.getChat_room_id_fk()) {
//				currentSession.sendMessage(new TextMessage(user + ":님의 연결이 종료되었습니다."));
//				sessionList.remove(session);
//			}
//			break;
//		}
				
		sessionList.remove(session);
		logger.debug("연결 끊김 : {}", user);
	}

	// webSocketSession으로부터 userId 정보 조회
	private String getUser(WebSocketSession session) {
		UserVO userVO = (UserVO) session.getAttributes().get("USER_INFO");
		return userVO.getUser_id();
	}
	
	// webSocketSession으로부터 roomId와 채팅방명 정보 조회
	private TeamChatVO getRoomVO(WebSocketSession session) {
		TeamChatVO teamChatVO = (TeamChatVO) session.getAttributes().get("TEAM_INFO");
		return teamChatVO;
	}
	
	// webSocketSession으로부터 채팅방 유저리스트 정보 조회
	private List<CrewVO> getCrewList(WebSocketSession session){
		List<CrewVO> crewList = (List<CrewVO>) session.getAttributes().get("CREWLIST");
		return crewList;
	}
	
	// 서버측에서 모든 websocket session으로 보내는 메세지
	public void serverToClient() throws IOException {
		for(WebSocketSession wSession : sessionList) {
			wSession.sendMessage(new TextMessage("서버 전송 메세지"));
		}
	}
}