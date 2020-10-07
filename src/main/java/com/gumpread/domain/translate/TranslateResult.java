package com.gumpread.domain.translate;

import lombok.Data;

import java.util.List;

@Data
public class TranslateResult {
    private String from;
    private String to;
    private List<TransSubResult> trans_result;
    private int error_code;

}
