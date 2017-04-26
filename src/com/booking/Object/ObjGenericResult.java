package com.booking.Object;

import java.io.Serializable;

public class ObjGenericResult implements Serializable{

	private static final long serialVersionUID = 3021069554693504166L;
	
	private ObjActor objActor;
	private ObjMessage objMessage;
	public ObjActor getObjActor() {
		return objActor;
	}
	public void setObjActor(ObjActor objActor) {
		this.objActor = objActor;
	}
	public ObjMessage getObjMessage() {
		return objMessage;
	}
	public void setObjMessage(ObjMessage objMessage) {
		this.objMessage = objMessage;
	}
	
	
}
