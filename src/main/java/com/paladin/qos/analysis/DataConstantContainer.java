package com.paladin.qos.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.common.core.container.ConstantsContainer.KeyValue;
import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.core.VersionContainerManager;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataEventService;
import com.paladin.qos.service.data.DataUnitService;

@Component
public class DataConstantContainer implements VersionContainer {

	private final static String container_id = "data_constant_container";
	
	@Override
	public String getId() {
		return container_id;
	}

	private final static String TYPE_EVENT = "data-event-type";
	private final static String TYPE_UNIT = "data-unit-type";

	@Autowired
	private ConstantsContainer constantsContainer;
	@Autowired
	private DataEventService dataEventService;
	@Autowired
	private DataUnitService dataUnitService;

	private static List<Event> events;
	private static List<Unit> units;

	public boolean initialize() {

		List<DataEvent> dataEvents = dataEventService.findAll();
		List<DataUnit> dataUnits = dataUnitService.findAll();

		List<Event> events = new ArrayList<>();
		List<Unit> units = new ArrayList<>();

		List<KeyValue> eventKeyValues = new ArrayList<>();

		for (DataEvent dataEvent : dataEvents) {
			String id = dataEvent.getId();
			String name = dataEvent.getName();
			Integer enabled = dataEvent.getEnabled();
			
			eventKeyValues.add(new KeyValue(id, name));
			Event event = new Event();
			event.setId(id);
			event.setName(name);
			event.setEnabled(enabled != null && enabled.intValue() == 1);
		}

		List<KeyValue> unitKeyValues = new ArrayList<>();

		for (DataUnit dataUnit : dataUnits) {
			String id = dataUnit.getId();
			String name = dataUnit.getName();
			
			unitKeyValues.add(new KeyValue(id, name));
			Unit unit = new Unit();
			unit.setId(id);
			unit.setName(name);
		}

		constantsContainer.putConstant(TYPE_EVENT, eventKeyValues);
		constantsContainer.putConstant(TYPE_UNIT, unitKeyValues);

		DataConstantContainer.events = Collections.unmodifiableList(events);
		DataConstantContainer.units = Collections.unmodifiableList(units);

		return true;
	}

	@Override
	public boolean versionChangedHandle(long version) {
		initialize();
		return false;
	}
	
	public static void updateData() {
		VersionContainerManager.versionChanged(container_id);
	}

	public static List<Event> getEventList() {
		return events;
	}

	public static List<Unit> getUnitList() {
		return units;
	}

	public static class Event {

		private String id;
		private String name;
		private boolean enabled;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

	public static class Unit {

		private String id;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public int order() {
		// 需要在常量容器后执行
		return 10;
	};

}
