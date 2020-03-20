package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;

public enum VertretungsEventType implements Serializable {

	CANCELED, ROOM_CHANGE, INSTEAD_OF, DESPITE_ABSENSE, MOVING, TEACHER_CHANGE, SUBSTITUTE, CARE, FREE, UNDEFINED

}
