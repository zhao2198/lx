package com.lx.core.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult<T> {
	private Integer code;
	
	private String message;

	private String timestamp;

	private T data;


}
