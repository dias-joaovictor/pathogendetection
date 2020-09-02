package org.nal.pathogendetection.model;

import java.io.Serializable;

public class PathogenValue implements Serializable, Comparable<PathogenValue> {

	private static final long serialVersionUID = 997180466622908968L;

	private String column;

	private boolean value;

	public PathogenValue(final String column, final boolean value) {
		super();
		this.column = column;
		this.value = value;
	}

	public String getColumn() {
		return this.column;
	}

	public void setColumn(final String column) {
		this.column = column;
	}

	public boolean isValue() {
		return this.value;
	}

	public void setValue(final boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PathogenValue [column=" + this.column + ", value=" + this.value + "]";
	}

	@Override
	public int compareTo(final PathogenValue o) {
		return this.column.compareTo(o.column);
	}

}
