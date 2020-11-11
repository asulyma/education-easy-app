package com.global.education.service.report;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.*;


public interface CsvReportStrategy {

	String COMMA = ",";
	String LINE_SEPARATOR = "\r\n";

	String generate(List<UUID> userUuids);

	String generateFilename();

	default void append(StringBuilder csvContent, String value) {
		csvContent.append(isEmpty(value) ? EMPTY : value).append(COMMA);
	}

	default void append(StringBuilder csvContent, Object value) {
		csvContent.append(Objects.isNull(value) ? EMPTY : String.valueOf(value)).append(COMMA);
	}

}
