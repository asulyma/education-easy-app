package com.global.education.service.report.csv;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.education.common.model.Progress;
import com.global.education.model.UserDataEntity;
import com.global.education.service.UserDataService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserCsvReportStrategy extends AbstractCsvReportStrategy {

	private final UserDataService userDataService;

	@Override
	protected OrderCSVReportBodyData buildReportSeed(List<UUID> userUuids) {
		List<UserDataEntity> users = userDataService.findAllUsers(userUuids);
		return OrderCSVReportBodyData.builder().columnNames(generateColumnNames()).rows(generateRows(users)).build();
	}

	private String generateColumnNames() {
		StringBuilder csvHeader = new StringBuilder();

		append(csvHeader, "User UUID");
		append(csvHeader, "User Name");
		append(csvHeader, "Email");
		append(csvHeader, "Rank");
		append(csvHeader, "Total Finished Courses");
		append(csvHeader, "Total In Progress Courses");

		return csvHeader.toString();
	}

	private List<String> generateRows(List<UserDataEntity> users) {
		return users.stream().map(this::generateRow).collect(Collectors.toList());
	}

	private String generateRow(UserDataEntity user) {
		StringBuilder csvContent = new StringBuilder();

		append(csvContent, user.getUuid());
		append(csvContent, user.getUsername());
		append(csvContent, user.getEmail());
		append(csvContent, user.getRank().getDescription());
		append(csvContent, user.getProgressMap().values().stream().filter(Progress::isFinish).count());
		append(csvContent, user.getProgressMap().values().stream().filter(progress -> !progress.isFinish()).count());

		return csvContent.toString();
	}

	@Override
	public String generateFilename() {
		return "Users.csv";
	}
}
