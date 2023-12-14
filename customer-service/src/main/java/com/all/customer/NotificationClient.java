package com.all.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Notification-Service")
public interface NotificationClient {
	
	@PostMapping("/notification")
	public void sendNotification(@RequestBody NotificationRequest notificationRequest, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) String token);
}
