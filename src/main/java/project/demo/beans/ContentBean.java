package project.demo.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ContentBean {

    private int content_idx;

    @NotBlank
    private String content_subject;

    @NotBlank
    private String content_text;

    private String content_file;

    private int content_writer_idx;

    private int content_board_idx;

    private String content_date;
}
