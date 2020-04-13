package ru.itis.lifecarespring.services;

public interface SMSService {
	void sendMessage(String phone, String text);
}
