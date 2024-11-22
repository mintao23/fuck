package org.example.mintao.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;

public class FileUpload {

    // 파일 업로드 메서드
    public static String uploadFile(HttpServletRequest request, String uploadPath) {
        String fileName = null;
        try {
            // 업로드 디렉토리가 없으면 생성
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 업로드된 파일 추출
            for (Part part : request.getParts()) {
                // 파일 이름 추출
                String contentDisposition = part.getHeader("Content-Disposition");
                for (String content : contentDisposition.split(";")) {
                    if (content.trim().startsWith("filename")) {
                        fileName = content.substring(content.indexOf("=") + 2, content.length() - 1);
                        fileName = fileName.trim(); // 파일 이름 공백 제거
                    }
                }

                // 파일이 비어있지 않은 경우 저장
                if (fileName != null && !fileName.isEmpty()) {
                    File uploadedFile = new File(uploadPath + File.separator + fileName);

                    // 파일 저장
                    part.write(uploadedFile.getAbsolutePath());
                    System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath());
                } else {
                    fileName = null; // 파일 이름이 없는 경우 null로 처리
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName; // 업로드된 파일 이름 반환, 없으면 null 반환
    }
}
