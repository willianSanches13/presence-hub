package com.oficina.presence_hub.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
public class EmailService {

    private static final String MAILJET_API_KEY = System.getenv("MAILJET_KEY");
    private static final String MAILJET_API_SECRET = System.getenv("MAILJET_SECRET");

    @Autowired
    private TemplateEngine templateEngine;


    public void sendEmail(String toEmail, String subject, String url) throws Exception {

        OkHttpClient okHttpClient = new OkHttpClient();

        ClientOptions clientOptions = ClientOptions.builder()
                .baseUrl("https://api.mailjet.com")
                .apiKey(MAILJET_API_KEY)
                .apiSecretKey(MAILJET_API_SECRET)
                .okHttpClient(okHttpClient)
                .build();

        MailjetClient client = new MailjetClient(clientOptions);
        Context context = new Context();
        context.setVariable("url", url);
        String content = templateEngine.process("email-template", context);

        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "williamsanches70@gmail.com")
                                        .put("Name", "ellp"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", toEmail)
                                                .put("Name", "Recipient Name")))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.HTMLPART, content)));

        MailjetResponse response = client.post(request);
        log.info("Response status: {}", response.getStatus());
        log.info("Response data: {}", response.getData().toString());
    }
}