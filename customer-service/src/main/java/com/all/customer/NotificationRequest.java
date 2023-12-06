package com.all.customer;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

	private Integer toCustomerId;
	private String toCustomerEmail;
	private String sender;
	private String message;
	private LocalDateTime sentAt;
}
