package com.hoshino.study.logback.firstversion.regex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2022-09-22
 */
public class RegexReplacementList {

    /**
     * 脱敏正则列表
     */
    private List<RegexReplacement> replace = new ArrayList<>();
    /**
     * 添加规则（因为replace类型是list，必须指定addReplace方法用以添加多个）
     *
     * @param replacement replacement
     */
    public void addReplace(RegexReplacement replacement) {
        replace.add(replacement);
    }

    public List<RegexReplacement> getReplace() {
        return replace;
    }

    public void setReplace(List<RegexReplacement> replace) {
        this.replace = replace;
    }

}
