package project.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.demo.beans.UserBean;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserBean userBean = (UserBean) target;

        String beanName = errors.getObjectName();

        // user 빈일때만 유효성 검사
        if(beanName.equals("user")){
            if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false){
                errors.rejectValue("user_pw", "NotEquals");
                errors.rejectValue("user_pw2", "NotEquals");
            }

            if(userBean.isUserIdExist() == false){
                errors.rejectValue("user_id", "DontCheckUserIdExist");
            }
        }


    }
}
