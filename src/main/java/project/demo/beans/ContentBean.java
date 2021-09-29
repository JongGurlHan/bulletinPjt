package project.demo.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ContentBean {

    private int content_idx;

    @NotBlank
    private String content_subject;

    @NotBlank
    private String content_text;

    //브라우저가 보낸 파일데이터를 담기 위해 사용할 변수
    //브라우저가 보낼 파일 데이터는 MultipartFile객체로 만들어져서 빈에 주입시도
    private MultipartFile upload_file;

    //db에 저장되어져 있을 파일 이름을 담을 변수
    private String content_file;

    private int content_writer_idx;

    private int content_board_idx;

    private String content_date;
}
