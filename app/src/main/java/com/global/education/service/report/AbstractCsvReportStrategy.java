package com.global.education.service.report;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.*;


public abstract class AbstractCsvReportStrategy implements CsvReportStrategy {

	@Override
	public String generate(List<UUID> userUuids) {
		OrderCSVReportBodyData seed = buildReportSeed(userUuids);
		return StringUtils.join(seed.getColumnNames(), LINE_SEPARATOR, buildReportBody(seed.getRows()));
	}

	protected abstract OrderCSVReportBodyData buildReportSeed(List<UUID> userUuids);

	private String buildReportBody(List<String> rows) {
		return String.join(EMPTY, rows);
	}

	@Getter
	@Setter
	@Builder
	protected static class OrderCSVReportBodyData {
		private String columnNames;
		private List<String> rows;
	}
}
