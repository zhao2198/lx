
package com.lx.core.common.utils;

/**
 * Create Date: 2018年5月9日 上午10:35:03
 * 
 * @version: V3.0.1
 * @author: zhao wei
 */
public enum CollectionRedisKeyType {

	COLLECTION_DEVICE_EQUMAP("c:d:equMap", 1 * 60 * 60 * 24 * 7),

	COLLECTION_DEVICE_PROJECTCODEMAP("c:d:projectMap", 1 * 60 * 60 * 24 * 7),

	// COLLECTION_DEVICE_CURRENELECTR("c:d:currentElectr:", 1 * 60 * 60 * 24 * 7),

	COLLECTION_ALARM_ALARMSETTINGMAP("c:a:alarmSettingMap:", 1 * 60 * 60 * 24 * 7);

	private String prefix;// 前缀
	private long expiredTime; // 有效期

	CollectionRedisKeyType(String prefix, long expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	@Override
	public String toString() {
		return this.getPrefix();
	}

}
