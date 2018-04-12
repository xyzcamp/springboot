package sample08;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@SpringBootApplication
public class Sample08Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Sample08Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// simplemail();
		mailwithfreemarker();
	}

	private void simplemail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("robinwen.tw@gmail.com", "robin.wen@acer.com");
		message.setSubject("Hi 你好中文堃");
		message.setText("2這是內文中文堃<span style='color:red;'>換個色</span>");
		emailSender.send(message);
		System.out.println("simplemail OK");
	}

	@Autowired
	public JavaMailSender emailSender;

	private void sendmailwithattachment() throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		// 收件者(多筆), 標題, 內容(HTML or not)
		helper.setTo(new String[] { "robinwen.tw@gmail.com", "robin.wen@acer.com" });
		helper.setSubject("Hi 你好, 有附件中文堃");
		helper.setText("這是內文有附件中文堃<span style='color=red'>換個色</span>", true);

		// 加入附件
		FileSystemResource file1 = new FileSystemResource(new File("Z:/a1.pdf"));
		FileSystemResource file2 = new FileSystemResource(new File("Z:/a2.pdf"));
		helper.addAttachment("附1號中文堃.pdf", file1);
		helper.addAttachment("附2號中文堃.pdf", file2);

		emailSender.send(message);
	}

	@Autowired
	Configuration configuration;

	private void mailwithfreemarker() throws MessagingException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {

		String content = "";

		// prepare data
		Map<String, String> data = new HashMap<>();
		data.put("name", "ABC中文堃");
		data.put("how", "如何呢");

		// get template
		Template t = configuration.getTemplate("test01.ftl");

		content = FreeMarkerTemplateUtils.processTemplateIntoString(t, data);

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(new String[] { "robinwen.tw@gmail.com", "robin.wen@acer.com" });
		helper.setSubject("Hi 你好, 有附件中文堃");
		helper.setText(content, true);

		FileSystemResource file1 = new FileSystemResource(new File("Z:/a1.pdf"));
		FileSystemResource file2 = new FileSystemResource(new File("Z:/a2.pdf"));
		helper.addAttachment("附1號中文堃.pdf", file1);
		helper.addAttachment("附2號中文堃.pdf", file2);

		emailSender.send(message);
		System.out.println("mailwithfreemarker OK");

	}
}
