package com.all.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

	@Autowired
	private final NotificationService notificationService;
	
	@PostMapping
	public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
		log.info("new notification... {}",notificationRequest);
		notificationService.sendNotification(notificationRequest);
	}
}
