package com.jg.ebook.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PdfUtil {

	@SneakyThrows
	public String getTextOfPdf(){
		// pdf 파일 경로
		String fileUrl = "C:\\Users\\user\\Desktop\\e-book용_ 공연 _훔친 개 훔친 아기 Stolen baby, Stolen dog_의 기록.pdf";

		//PDFBox 설정
		InputStream stream = new FileInputStream(new File(fileUrl));
		PDDocument document = PDDocument.load(stream);;
		PDFTextStripper stripper =  new PDFTextStripper();

		//텍스트 추출
		String extractText = stripper.getText(document);

		//공백 제거
		extractText = extractText.trim().replace(" ", "");

		//특정 문자 추출 (예제: 이메일)
		Set<String> emails = new HashSet<>();

		//이메일 정규식
		String regEx = "";

		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(extractText);

		//추출한 텍스트에서 정규식에 의한 특정 문자 추출
		while (matcher.find()) {
			emails.add(matcher.group());
		}

		//PDF 파일 스트림 닫기
		document.close();
		log.info(extractText);

		return extractText;
	}

}
