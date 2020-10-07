package com.gumpread.domain.translate;

import lombok.Data;

import java.util.List;

/**
 * 封装翻译所得到的结果；
 */
@Data
public class TranslateResult {
    private String from;
    private String to;
    private List<TransSubResult> trans_result;
    private int error_code;

}
