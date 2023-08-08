package ru.stqa.java_learn.mantis.appmanager;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.java_learn.mantis.model.MailMessage;

import java.util.List;

public class LinkHelper {

    public static String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
