package project.demo.beans;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserBean {

    private int user_idx;

    @Size(min=2, max = 4)
    @Pattern(regexp="[가-힣]*") //한글만 입력
    private String user_name;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_id;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_pw;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_pw2;

    private boolean userIdExist;
    private boolean userLogin;

    //처음 Bean이만들어졌을땐 중복검사 안한거니까 false세팅
    public UserBean() {
        this.userIdExist = false;
        this.userLogin = false;
    }
}
