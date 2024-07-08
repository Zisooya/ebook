package com.jg.ebook.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
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

	final public static String PDF_EXTENSION = "PDF";		//파일 확장자

	@Value("${ebook.value.pdf.dir}")
	private String PDF_DIR;

	@SneakyThrows
	public String[] getTextOfPdf(String fileName){
		// pdf 파일 경로
		String pdfName = fileName.concat(".").concat(PdfUtil.PDF_EXTENSION.toLowerCase());
		String pdfPath = PDF_DIR.concat(pdfName);

		//PDFBox 설정
		InputStream stream = new FileInputStream(new File(pdfPath));
		PDDocument document = PDDocument.load(stream);;
		PDFTextStripper stripper =  new PDFTextStripper();

		//텍스트 추출
		String extractText = stripper.getText(document);

		//공백 제거
		//extractText = extractText.trim().replace(" ", "");

		//특정 문자열로 가르기
		String[] splitExtractText = extractText.split("—");

		//특정 문자 추출
		/*Set<String> emails = new HashSet<>();

		//정규식
		String regEx = "";

		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(extractText);

		//추출한 텍스트에서 정규식에 의한 특정 문자 추출
		while (matcher.find()) {
			emails.add(matcher.group());
		}

		log.info(Arrays.toString(splitExtractText));
		*/

		//PDF 파일 스트림 닫기
		document.close();

		return splitExtractText;
	}

}
