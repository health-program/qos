package com.paladin.qos.analysis;

import java.util.Date;
import java.util.List;

/**
 * 数据输入处理
 * @author TontoZhou
 * @since 2019年8月13日
 */
public class DataInputHandler {
	
	public void handle(List<DataPacket> dataPackets, String eventId) {
		
		
		
	}
	
	public static class DataPacket{
		private Object data;
		private Date time;
		private String unitId;
		
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		public String getUnitId() {
			return unitId;
		}
		public void setUnitId(String unitId) {
			this.unitId = unitId;
		}
	}
	
}
