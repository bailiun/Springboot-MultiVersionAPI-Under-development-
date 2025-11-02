package org.bailiun.multipleversionscoexist.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class PMarc {
    String marc1 = "";//第一段标识
    StringBuilder marc2 = new StringBuilder();//第二段目次
    StringBuilder marc3 = new StringBuilder();//第三段记录
    String marc = null;//marc数据
    char _RecordSplitChar = '\u001D';  // 区间分隔符
    char _FieldSplitChar = '\u001E';  // 字段分割符
    char _SubFieldSplitChar = '\u001F';  // 子字段分隔符
    private final Map<String, String> CODE_MAP = new HashMap<>();//用于处理拼接时其前面的字母
    private final Map<String, String[]> NUM_MAP = new TreeMap<>();

    public PMarc() {
        // 701直接由200的生成@a,4,9
        // 801自动生成:CHData
        // 100以及以前的
        NUM_MAP.put("001", new String[]{"control_num"});
        NUM_MAP.put("005", new String[]{"create_time"});
        NUM_MAP.put("010", new String[]{"sbx_ISBN", "sbx_bind", "sbx_priceSta", "unISBN"});
        NUM_MAP.put("100", new String[]{"inform_block"});
        NUM_MAP.put("101", new String[]{"sbx_language"});
        NUM_MAP.put("102", new String[]{"sbx_country", "sbx_city"});
        NUM_MAP.put("105", new String[]{"text_material"});
        NUM_MAP.put("106", new String[]{"morphological"});
        NUM_MAP.put("200", new String[]{"sbx_title", "sbx_othTitle", "sbx_paraT", "sbx_titleVice", "sbx_author", "sbx_divisionCode", "sbx_othAuthor", "sbx_divisionName", "sbx_othTitleChi", "sbx_titlePY", "sbx_authorPY"});
        NUM_MAP.put("205", new String[]{"sbx_issue"});
        NUM_MAP.put("210", new String[]{"sbx_published", "sbx_press", "print_data", "publisher_name", "sbx_pubdate"});
        NUM_MAP.put("215", new String[]{"sbx_pages", "sbx_size", "sbx_picNote", "sbx_attachment"});
        NUM_MAP.put("225", new String[]{"sbx_subtitle", "sbx_otherSub", "sbx_subVice", "other_subtitle_chi", "sbx_subDivCode", "sbx_subDivName", "sbx_subAuthor", "t"});
        NUM_MAP.put("300", new String[]{"sbx_footnote"});
        NUM_MAP.put("312", new String[]{"sbx_titleAnnot"});
        NUM_MAP.put("330", new String[]{"sbx_remark"});
        NUM_MAP.put("461", new String[]{"genCollect"});
        NUM_MAP.put("500", new String[]{"sbx_uniTitle", "sbx_episode", "sbx_epiName", "genCollect", "genCollect", "genCollect", "genCollect", "genCollect", "genCollect"});
        NUM_MAP.put("606", new String[]{"sbx_theme", "sbx_addTheme", "2", "3", "4", "5", "6"});
        NUM_MAP.put("690", new String[]{"sbx_classical", "sbx_issue"});
        NUM_MAP.put("701", new String[]{"mainObject", "other_part", "personal_name_modifier", "generation", "age", "original_names", "relate_pronoun", "pinyin"});
        NUM_MAP.put("702", new String[]{"mainObject"});
        NUM_MAP.put("801", new String[]{"nations", "organization", "data", "ordinances"});

        CODE_MAP.put("control_num", "");
        CODE_MAP.put("create_time", "");
        CODE_MAP.put("sbx_ISBN", "a");
        CODE_MAP.put("sbx_bind", "b");
        CODE_MAP.put("sbx_priceSta", "d");
        CODE_MAP.put("unISBN", "z");
        CODE_MAP.put("inform_block", "a");
        CODE_MAP.put("sbx_language", "a");
        CODE_MAP.put("sbx_country", "a");
        CODE_MAP.put("sbx_city", "b");
        CODE_MAP.put("text_material", "a");
        CODE_MAP.put("morphological", "a");
        CODE_MAP.put("sbx_title", "a");
        CODE_MAP.put("sbx_othTitle", "c");
        CODE_MAP.put("sbx_paraT", "d");
        CODE_MAP.put("sbx_titleVice", "e");
        CODE_MAP.put("sbx_author", "f");
        CODE_MAP.put("sbx_divisionCode", "h");
        CODE_MAP.put("sbx_othAuthor", "g");
        CODE_MAP.put("sbx_divisionName", "i");
        CODE_MAP.put("sbx_othTitleChi", "z");
        CODE_MAP.put("sbx_titlePY", "A");
        CODE_MAP.put("sbx_authorPY", "F");
        CODE_MAP.put("sbx_issue", "a");
        CODE_MAP.put("sbx_published", "a");
        CODE_MAP.put("sbx_press", "b");
        CODE_MAP.put("print_data", "h");
        CODE_MAP.put("publisher_name", "c");
        CODE_MAP.put("sbx_pubdate", "d");
        CODE_MAP.put("sbx_pages", "a");
        CODE_MAP.put("sbx_size", "d");
        CODE_MAP.put("sbx_picNote", "c");
        CODE_MAP.put("sbx_attachment", "e");
        CODE_MAP.put("sbx_subtitle", "a");
        CODE_MAP.put("sbx_otherSub", "d");
        CODE_MAP.put("sbx_subVice", "e");
        CODE_MAP.put("other_subtitle_chi", "z");
        CODE_MAP.put("sbx_subDivCode", "h");
        CODE_MAP.put("sbx_subDivName", "i");
        CODE_MAP.put("sbx_subAuthor", "f");
        CODE_MAP.put("sbx_footnote", "a");
        CODE_MAP.put("sbx_titleAnnot", "a");
        CODE_MAP.put("sbx_remark", "a");
        CODE_MAP.put("genCollect", "a");
        CODE_MAP.put("sbx_uniTitle", "a");
        CODE_MAP.put("sbx_episode", "h");
        CODE_MAP.put("sbx_epiName", "i");
        CODE_MAP.put("sbx_theme", "a");
        CODE_MAP.put("sbx_addTheme", "x");
        CODE_MAP.put("sbx_classical", "a");
//        CODE_MAP.put("sbx_issue", "v");
        CODE_MAP.put("other_part", "b");
        CODE_MAP.put("personal_name_modifier", "c");
        CODE_MAP.put("generation", "d");
        CODE_MAP.put("age", "f");
        CODE_MAP.put("original_names", "g");
        CODE_MAP.put("relate_pronoun", "4");
        CODE_MAP.put("pinyin", "A");
        CODE_MAP.put("mainObject", "a");
        CODE_MAP.put("nations", "a");
        CODE_MAP.put("organization", "b");
        CODE_MAP.put("data", "c");
        CODE_MAP.put("ordinances", "g");
    }

    /**
     * &#064;作者:  bailiun
     * &#064;版本:  1.0.0
     * &#064;功能:  接受完整编目数据,并且生成marc数据
     * &#064;参数-->marcDate:编目的数据,
     * &#064;警告!  -->  marcDate数据必须由TreeMap进行排序!!!
     */
    public String pjMarc(Map<String, String> marcData) {
        int i = 0;
        for (String s : NUM_MAP.keySet()) {
            StringBuilder temp = new StringBuilder(_FieldSplitChar + "  ");
            String[] arr = NUM_MAP.get(s);
            if (Objects.equals(s, "001") || Objects.equals(s, "005")) {
                temp = new StringBuilder(_FieldSplitChar + "");
                for (String s1 : arr) {
                    if (marcData.get(s1) == null) {
                        continue;
                    }
                    temp.append(_FieldSplitChar).append(CODE_MAP.get(s1)).append(marcData.get(s1));
                }
                marc3.append(temp);
                marc2.append(s).append(String.format("%04d", temp.length())).append(String.format("%05d", i));
                i += temp.length();
                continue;
            }
            boolean flax = true;
            for (String s1 : arr) {
                if (marcData.get(s1) == null) {
                    continue;
                }
                temp.append(_SubFieldSplitChar).append(CODE_MAP.get(s1)).append(marcData.get(s1));
                flax = false;
            }
            if (flax) {
                continue;
            }
            marc3.append(temp);
            marc2.append(s).append(String.format("%04d", temp.length())).append(String.format("%05d", i));
            i += temp.length();
        }
        marc3.append(_FieldSplitChar);

        marc1 = String.format("%05d", marc3.length() + marc2.length() + 1)
                + "pam0" + " 22" + String.format("%05d", marc2.length() + 1 + 24) + "   " + "450 "
        ;

        marc = marc1 + marc2 + marc3 + _RecordSplitChar;

        return marc;
    }

}

