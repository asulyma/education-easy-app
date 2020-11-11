package com.global.education.service.report.csv;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractCsvReportStrategy implements CsvReportStrategy {

	@Override
	public String generate(List<UUID> userUuids) {
		log.info("Start generating CSV Report for {} users", userUuids.size());

		OrderCSVReportBodyData seed = buildReportSeed(userUuids);
		return StringUtils.join(seed.getColumnNames(), LINE_SEPARATOR, buildReportBody(seed.getRows()));
	}

	protected abstract OrderCSVReportBodyData buildReportSeed(List<UUID> userUuids);

	private String buildReportBody(List<String> rows) {
		return String.join(LINE_SEPARATOR, rows);
	}

	@Getter
	@Setter
	@Builder
	protected static class OrderCSVReportBodyData {
		private String columnNames;
		private List<String> rows;
	}
}
