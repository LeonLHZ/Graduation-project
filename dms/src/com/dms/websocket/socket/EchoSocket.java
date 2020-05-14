package com.dms.websocket.socket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoSocket {
	
@OnOpen
public void open(Session session) {
	System.out.println("sessionId:"+session.getId());
}
@OnClose
public void closeSession(Session session) {
	System.out.println("session:"+session.getId()+"关闭");
}
@OnMessage
public void message(Session session,String msg) {
	System.out.println(msg);
	try {
		session.getBasicRemote().sendText("服务器说：你也好");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
