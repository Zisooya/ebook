package com.jg.ebook;

import com.jg.ebook.util.FileUtil;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class EbookApplicationTests {

	final public static String PDF_EXTENSION = "PDF";		//파일 확장자

	@Value("${ebook.value.pdf.dir}")
	private String PDF_DIR;

	@Test
	void contextLoads() {
	}

	@Test
	@SneakyThrows
	public void getTextOfPdf(){
		// pdf 파일 경로
		String pdfName = "e-book용_ 공연 _훔친 개 훔친 아기 Stolen baby, Stolen dog_의 기록".concat(".").concat(FileUtil.PDF_EXTENSION.toLowerCase());
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

		//특정 문자 추출 (예제: 이메일)
		Set<String> emails = new HashSet<>();

		//이메일 정규식
		String regEx = "";

		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(extractText);

		System.out.println("=============== start =================");
		//추출한 텍스트에서 정규식에 의한 특정 문자 추출
		while (matcher.find()) {
			emails.add(matcher.group());
		}
		//System.out.println(extractText);
		//System.out.println(Arrays.toString(splitExtractText));
		for(int i=0; i<splitExtractText.length; i++) {
			System.out.println(splitExtractText[i]);
		}
		System.out.println("=============== end =================");


		//PDF 파일 스트림 닫기
		document.close();
	}

	@Test
	public void getFileStr() throws IOException {
		// pdf 파일 경로
		String pdfName = "e-book용_ 공연 _훔친 개 훔친 아기 Stolen baby, Stolen dog_의 기록".concat(".").concat("txt");
		String pdfPath = PDF_DIR.concat(pdfName);

		BufferedReader reader = new BufferedReader(
				new FileReader(pdfPath, StandardCharsets.UTF_8)
		);

		String str;

		while ((str = reader.readLine()) != null) {
			System.out.println(str);
		}

		reader.close();

		System.out.println(str);
	}
}
