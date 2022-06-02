package com.springboot6772.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Message
{ 
	private String content;
	private String messageType;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Message(String content, String messageType) {
		super();
		this.content = content;
		this.messageType = messageType;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
