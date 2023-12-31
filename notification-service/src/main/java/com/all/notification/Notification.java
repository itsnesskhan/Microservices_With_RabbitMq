package com.all.notification;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {

	@Id
	@SequenceGenerator(
			name = "notification_id_generator",
			sequenceName = "notification_id_generator"
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "notification_id_generator"
			)
	private Integer notificationId;
	
	private Integer toCustomerId;
	private String toCustomerEmail;
	private String sender;
	private String message;
	@CreationTimestamp
	private LocalDateTime sentAt;
}
