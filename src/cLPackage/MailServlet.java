/**
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cLPackage;

// [START simple_includes]
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]

// [START multipart_includes]
import java.io.UnsupportedEncodingException;
// [END multipart_includes]

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {
    String userEmail;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Sending simple email.");
        userEmail = (String)req.getSession().getAttribute("email");
        sendSimpleMail();
        resp.sendRedirect("/profile");
    }

    private void sendSimpleMail() {
        // [START simple_example]
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userEmail, "User"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("cyfbjp001@gmail.com", "Yifang"));
            msg.setSubject("Test from DevRoot");
            msg.setText("This is a test");
            Transport.send(msg);
        } catch (AddressException e) {
            System.out.println("Some Exception 1");
        } catch (MessagingException e) {
            System.out.println("Some Exception 2");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Some Exception 3");
        }
        // [END simple_example]
    }


}