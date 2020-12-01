package com.global.education.service.report.pdf;

import java.io.ByteArrayOutputStream;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.global.education.model.UserDataEntity;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractPdfReportStrategy implements PdfReportStrategy {

	@Override
	public byte[] generate(UserDataEntity userDataEntity, Long courseId) {
		log.info("Generating PDF for {} with course {}", userDataEntity.getUuid(), courseId);

		String template = parseThymeleafTemplate(userDataEntity, courseId);
		return generatePdfFromTemplate(template);
	}

	private String parseThymeleafTemplate(UserDataEntity userDataEntity, Long courseId) {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);

		return engine.process(getTemplateLocation(), buildContext(userDataEntity, courseId));
	}

	@SneakyThrows
	private byte[] generatePdfFromTemplate(String template) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(template);
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();
		return outputStream.toByteArray();
	}

	protected abstract Context buildContext(UserDataEntity userDataEntity, Long courseId);

	protected abstract String getTemplateLocation();

}
