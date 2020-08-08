package healthconnect.utils.roleValidator;

import healthconnect.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {

    @Autowired
    private SessionRegistry sessionRegistry;


    public void expireUserSessions(String username) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {

                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    int a  = 5;
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                        information.expireNow();
                        int b = 6;
                    }
                }

        }
    }
}