package org.example.mintao.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;

public class FileUpload {

    // 파일 업로드 메서드
    public static String uploadFile(HttpServletRequest request, String uploadPath) {
        String fileName = null; // 업로드된 파일의 이름을 저장할 변수
        try {
            // 업로드 디렉토리가 없으면 생성
            File uploadDir = new File(uploadPath); // 업로드 경로를 파일 객체로 생성
            if (!uploadDir.exists()) { // 디렉토리가 존재하지 않을 경우
                uploadDir.mkdirs(); // 디렉토리 생성
            }

            // 업로드된 파일 추출
            for (Part part : request.getParts()) { // 요청으로부터 모든 Part 객체를 반복
                // 파일 이름 추출
                String contentDisposition = part.getHeader("Content-Disposition"); // 헤더에서 파일 정보를 가져옴
                for (String content : contentDisposition.split(";")) { // 헤더 내용을 ';'로 분리
                    if (content.trim().startsWith("filename")) { // 파일 이름 정보를 포함하는 부분을 찾음
                        // 파일 이름 추출 및 양쪽 공백 제거
                        fileName = content.substring(content.indexOf("=") + 2, content.length() - 1).trim();
                    }
                }

                // 파일이 비어있지 않은 경우 저장
                if (fileName != null && !fileName.isEmpty()) { // 파일 이름이 유효한 경우
                    File uploadedFile = new File(uploadPath + File.separator + fileName); // 저장 경로와 파일 이름 결합

                    // 파일 저장
                    part.write(uploadedFile.getAbsolutePath()); // 지정된 경로에 파일 저장
                    System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath()); // 저장 경로 출력
                } else {
                    fileName = null; // 파일 이름이 없는 경우 null로 처리
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return fileName; // 업로드된 파일 이름 반환, 없으면 null 반환
    }
}
