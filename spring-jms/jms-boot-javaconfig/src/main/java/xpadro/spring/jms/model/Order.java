package xpadro.spring.jms.model;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = -797586847427389162L;

	private final String id;
	private final String value;

	public Order(String id) {
		this(id, "Default Value");
	}

	public Order(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
