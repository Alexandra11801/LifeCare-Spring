package ru.itis.lifecarespring.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSServiceImpl implements SMSService {

	@Override
	public void sendMessage(String phone, String text) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("alexaver11801@gmail.com", "0ZRsKZ9G666CnPpatz5ybaKRXkVV");
		String uri = "https://@gate.smsaero.ru/v2/sms/send?"
				+ "number=" + phone
				+ "&text=" + text
				+ "&sign=SMS Aero&channel=DIRECT";
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	}
}
