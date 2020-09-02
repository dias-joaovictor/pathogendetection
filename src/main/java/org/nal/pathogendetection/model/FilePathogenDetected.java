package org.nal.pathogendetection.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FilePathogenDetected implements Serializable {

	private static final long serialVersionUID = 8729677266352241188L;

	private String fileName;

	private Map<String, PathogenValue> mapValues;

	public FilePathogenDetected(final String fileName, final Set<String> columns) {
		this.fileName = fileName;
		if (columns != null && !columns.isEmpty()) {
			this.mapValues = columns//
					.stream()//
					.map(column -> {
						return new PathogenValue(column.trim(), false);
					}).collect(Collectors.toMap(PathogenValue::getColumn, item -> item));
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public TreeSet<PathogenValue> getAllValues() {
		return new TreeSet(this.mapValues.values());
	}

	public void found(final String column) {
		this.mapValues.get(column).setValue(true);
	}

	public String getColumnNames() {
		final StringBuilder sb = new StringBuilder("fileName|");
		final Iterator<String> iterator = this.mapValues.keySet().iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next());
			if (iterator.hasNext()) {
				sb.append("|");
			}
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(this.getFileName());
		sb.append("|");
		final Iterator<PathogenValue> iterator = this.getAllValues().iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next().isValue());
			if (iterator.hasNext()) {
				sb.append("|");
			}
		}

		return sb.toString();

	}

}
