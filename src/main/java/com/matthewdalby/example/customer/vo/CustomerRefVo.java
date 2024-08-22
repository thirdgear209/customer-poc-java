package com.matthewdalby.example.customer.vo;

import java.sql.Timestamp;

public record CustomerRefVo (
	Long id,
	String customerNumber,
	String firstName,
	String lastName,
	Timestamp createdAt,
	Timestamp updatedAt,
	String phoneNumber) {
}
