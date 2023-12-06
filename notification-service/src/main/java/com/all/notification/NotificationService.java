package com.all.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	@Autowired
	private final NotificationRepository notificationRepository;
	
	public void sendNotification(NotificationRequest notificationRequest) {
		notificationRepository.save(
				Notification.builder()
				.toCustomerId(notificationRequest.getToCustomerId())
				.toCustomerEmail(notificationRequest.getToCustomerEmail())
				.sender("Nassprivates")
				.message(notificationRequest.getMessage())
				.build()
				);
	}
}
