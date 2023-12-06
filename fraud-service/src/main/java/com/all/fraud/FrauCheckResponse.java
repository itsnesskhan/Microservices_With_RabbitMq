package com.all.fraud;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FrauCheckResponse {

	private boolean isFraudster;
}
