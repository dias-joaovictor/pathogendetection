package org.nal.pathogendetection.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.nal.pathogendetection.exception.ProcessException;
import org.nal.pathogendetection.model.FilePathogenDetected;

public final class PathogenDetectorGeneratorService {

	private PathogenDetectorGeneratorService() {
		super();
	}

	private static final List<String> trueValues = Arrays.asList("COMPLETE", "POINT");

	public static void getPathogenDetected(final File inputFile, final File outputFile) {

		if (inputFile == null || !inputFile.exists()) {
			throw new ProcessException("The file doesn't exists");
		}
		if (outputFile == null || !outputFile.exists()) {
			throw new ProcessException("The destination folder doesn't exists");
		}

		final List<FilePathogenDetected> files = getPathogenDetected(inputFile);
		if (files != null && !files.isEmpty()) {
			final StringBuilder sb = new StringBuilder();
			sb.append(files.get(0).getColumnNames());
			sb.append(System.lineSeparator());
			files.stream().forEach(item -> {
				sb.append(item.toString());
				sb.append(System.lineSeparator());
			});

			files.stream().forEach(item -> {
				try {
					final FileWriter newFile = new FileWriter(outputFile.getAbsolutePath()//
							.concat(getFileName()));
					newFile.write(sb.toString());
					newFile.close();

				} catch (final IOException e) {
					throw new ProcessException(e);
				}
			});

		}
	}

	public static String getFileName() {
		return System.getProperty("file.separator").concat("pathogendetectionoutput.csv");
	}

	protected static List<FilePathogenDetected> getPathogenDetected(final File file) {
		if (file == null || !file.exists()) {
			throw new ProcessException("No such file was found");
		}
		final List<String> values = getAllLinesInFile(file);
		verifyValues(values);
		values.remove(0);

		final List<String> columnsValues = values.parallelStream().map(item -> {
			return item.split("\\|")[1];
		}).collect(Collectors.toList());

		final Set<String> column = new HashSet<>();

		for (final String columnValue : columnsValues) {
			final String[] columns = columnValue.split(",");

			for (final String col : columns) {
				column.add(col.split("=")[0]);
			}
		}

		return values.parallelStream().map(item -> {
			final String[] data = item.split("\\|");
			final String name = data[0];
			final String dataValues = data[1];
			final FilePathogenDetected fileDetected = new FilePathogenDetected(name, column);

			for (final String param : dataValues.split(",")) {
				final String[] paramValues = param.split("=");

				if (trueValues.stream().anyMatch(itemValue -> itemValue.equals(paramValues[1].trim()))) {
					fileDetected.found(paramValues[0].trim());
				}
			}

			return fileDetected;
		}).collect(Collectors.toList());

	}

	private static void verifyValues(final List<String> values) {
		if (values == null || values.isEmpty() || values.size() < 2) {
			throw new ProcessException("The file is empty");
		}
		if (!getDefaultFileHeader().equals(values.get(0))) {
			throw new ProcessException("The file is invalid. CSV Header doesn`t match with the model`s header");
		}
	}

	private static String getDefaultFileHeader() {
		return "lineName|value";
	}

	private static List<String> getAllLinesInFile(final File file) {
		try {
			return Files.readAllLines(Paths.get(file.getAbsolutePath()));
		} catch (final IOException io) {
			throw new ProcessException("Error reading the file", io);
		}
	}
}
