package com.xiaozhu;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


public class ShiroMain {
    public static void main(String[] args) {
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("someKey", "value");

        if ( !currentUser.isAuthenticated() ) {
            //collect user principals and credentials in a gui specific manner
            //such as username/password html form, X509 certificate, OpenID, etc.
            //We'll use the username/password example here since it is the most common.
            //(do you know what movie this is from? ;)
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            try{
                try {
                    currentUser.login(token);
                    //if no exception, that's it, we're done!
                } catch ( UnknownAccountException uae ) {
                    //username wasn't in the system, show them an error message?
                } catch ( IncorrectCredentialsException ice ) {
                    //password didn't match, try again?
                } catch ( LockedAccountException lae ) {
                    //account for that username is locked - can't login.  Show them a message?
                }
            } catch ( AuthenticationException ae) {
                //unexpected condition - error?
            }
        }

    }
}