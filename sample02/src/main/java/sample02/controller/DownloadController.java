package sample02.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {

	// 下載 or 顯示image
	@RequestMapping(value = "/dl1")
	@ResponseBody
	public void dl1(HttpServletResponse response) throws ParseException, IOException {
		File file = new File("W:/1.jpg");
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		OutputStream outputStream = response.getOutputStream();

		FileCopyUtils.copy(inputStream, outputStream);
	}

	// 下載 or 顯示image
	@RequestMapping(value = "/dl2")
	@ResponseBody
	public ResponseEntity<byte[]> dl2() throws IOException {
		Path path = Paths.get("W:/1.jpg");
		byte[] data = Files.readAllBytes(path);
		// Resource resource = new ByteArrayResource(data);

		return ResponseEntity.ok().header("Content-Disposition", "attachment;filename=" + path.getFileName().toString())
				.contentType(MediaType.IMAGE_JPEG).contentLength(data.length).body(data);
	}

	// 無下載, 只能顯示image
	@RequestMapping(value = "/dl3")
	@ResponseBody
	public ResponseEntity<byte[]> dl3() throws IOException {
		Path path = Paths.get("W:/1.jpg");
		byte[] data = Files.readAllBytes(path);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
	}

}
