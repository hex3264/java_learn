package ru.stqa.java_learn.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.java_learn.mantis.model.UserData;
import ru.stqa.java_learn.mantis.model.Users;

import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }


    public UserHelper openUser(UserData user) {
        wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + user.getId() + "']")).click();
        return this;
    }


    public Users all() {
        Users users = new Users();
        List<WebElement> rows = wd.findElements(By.cssSelector("table tbody tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.cssSelector("a")).getAttribute("href").replaceAll(app.getProperty("web.baseUrl") + "manage_user_edit_page\\Q.\\Ephp\\Q?\\Euser_id=",""));
            String username = cells.get(0).findElement(By.cssSelector("a")).getText();
            String email = cells.get(2).getText();
            users.add(new UserData().withId(id).withUsername(username).withEmail(email));
        }
        return users;
    }

    public void resetPassword() {
        click(By.cssSelector("#manage-user-reset-form input[type='submit']"));

    }
}
