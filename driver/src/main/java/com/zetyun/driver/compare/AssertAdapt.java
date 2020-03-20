package com.zetyun.driver.compare;

import com.zetyun.driver.interpreter.Interpreter;
import com.zetyun.driver.json.JsonParse;
import com.zetyun.driver.log.LogWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AssertAdapt {
    /**
     * 判断指定的condition是否出现在source中
     *
     * @param condition
     * @param source
     * @return
     */
    public boolean compare(String condition, String source) {
        try {
            if (condition.isEmpty() || source.isEmpty()) {
                LogWriter.debug(AssertAdapt.class, "Input string is empty");
                return false;
            }

            if (condition.startsWith("[")) {
                return compare_array(condition, source);
            } else {
                return compare_string(condition, source);
            }
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "compare method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 判断condition是否执行成功
     *
     * @param condition
     * @return
     */
    public boolean compare(Boolean condition) {
        try {
            return condition;
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "compare method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param condition
     * @param source
     * @return
     */
    public boolean equal(String condition, String source) {
        try {
            if (condition.isEmpty() || source.isEmpty()) {
                LogWriter.debug(AssertAdapt.class, "Input string is empty");
                return false;
            }

            return condition.equals(source);
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "equal method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 判断指定的condition是否出现在source中
     *
     * @param condition
     * @param source
     * @return
     */
    private boolean compare_array(String condition, String source) {
        try {
            String regex = "\\[((\"\\w+\"|\"\\w+\\.\\w+\"|\"(\\s*\\w+)+\"),*)+\\]";
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(condition).matches()) {
                LogWriter.error(AssertAdapt.class, "The judgment is not correct! value = " + condition);
                return false;
            }

            JsonParse jp = new JsonParse();
            if (!jp.isJson(source)) {
                //不处理非json格式字符串
                LogWriter.error(AssertAdapt.class, "The source[" + source + "] is not json");
                return false;
            }

            //从json源中查找字符串
            List<String> src_value = jp.getArray(source);
            if (src_value.size() == 0) {
                LogWriter.error(AssertAdapt.class, "Do not find any array from json string");
                return false;
            }

            for (String msg : src_value) {
                if (msg.equals(condition)) {
                    LogWriter.debug(AssertAdapt.class, "Find a array from json string, key = " + condition);
                    return true;
                }
            }

            return false;
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "compare_string method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 判断指定的condition是否出现在source中
     *
     * @param condition
     * @param source
     * @return
     */
    private boolean compare_string(String condition, String source) {
        try {
            boolean final_result = true;
            //存放检测条件
            List<String> conditions = new ArrayList<>();
            //存放执行结果
            List<String> assert_result = new ArrayList<>();
            LogWriter.debug(AssertAdapt.class, "Initializing the array succeeded");

            if (Interpreter.isCombination(condition)) {
                conditions = Interpreter.getConditions(condition);
                LogWriter.debug(AssertAdapt.class, String.format("The condition(%s) is a Combination", condition));
            } else if (Interpreter.isSingleExpression(condition) || Interpreter.isExpression(condition)) {
                conditions = Interpreter.getExpressions(condition);
                LogWriter.debug(AssertAdapt.class, String.format("The condition(%s) is a Expression", condition));
            }

            //执行conditions
            for (String con : conditions) {
                if (Interpreter.isCombination(con)) {
                    boolean result = compare_string(con, source);
                    if (result) {
                        LogWriter.debug(AssertAdapt.class, String.format("Assert con(%s) succeeded", con));
                        assert_result.add("True");
                    } else {
                        LogWriter.debug(AssertAdapt.class, String.format("Assert con(%s) failed", con));
                        assert_result.add("False");
                    }
                } else if (con.equals("&") || con.equals("|")) {
                    LogWriter.debug(AssertAdapt.class, String.format("The con(%s) is a keyword", con));
                    assert_result.add(con);
                } else {
                    boolean result = compare_singel_condition(con, source);
                    if (result) {
                        LogWriter.debug(AssertAdapt.class, String.format("Assert con(%s) succeeded", con));
                        assert_result.add("True");
                    } else {
                        LogWriter.debug(AssertAdapt.class, String.format("Assert con(%s) failed", con));
                        assert_result.add("False");
                    }
                }
            }

            //处理执行结果
            for (int i = 0; i < assert_result.size(); i++) {
                String result = assert_result.get(i);
                if (i == 0) {
                    final_result = Boolean.valueOf(result);
                } else {
                    if (result.equals("&")) {
                        final_result &= Boolean.valueOf(assert_result.get(i + 1));
                    } else if (result.equals("|")) {
                        final_result |= Boolean.valueOf(assert_result.get(i + 1));
                    }
                }
            }

            LogWriter.debug(AssertAdapt.class, "compare_string method execute succeeded");
            return final_result;
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "compare_string method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 检测单个条件是否满足
     *
     * @param condition
     * @param source
     * @return
     */
    private boolean compare_singel_condition(String condition, String source) {
        try {

            // Update regex to support "-".
            String regex = "\\w+:[\\w@.\\-\\s]+";
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(condition).matches()) {
                LogWriter.error(AssertAdapt.class, "The judgment is not correct! value = " + condition);
                return false;
            }

            JsonParse jp = new JsonParse();
            if (!jp.isJson(source)) {
                //不处理非json格式字符串
                LogWriter.error(AssertAdapt.class, "The source[" + source + "] is not json");
                return false;
            }

            //String[0]  表示key
            //String[1]  表示value
            String[] con = condition.split(":");

            //从json源中查找字符串
            List<String> src_value = jp.getValue(source, con[0]);
            if (src_value.size() == 0) {
                LogWriter.error(AssertAdapt.class, "Do not find any key from json string, key = " + con[0]);
                return false;
            }

            for (String msg : src_value) {
                if (msg.equals(con[1])) {
                    LogWriter.debug(AssertAdapt.class, "Find a key from json string, key = " + con[0] + " value = " + con[1]);
                    return true;
                }
            }

            return false;
        } catch (Exception ex) {
            LogWriter.error(AssertAdapt.class, "compare_singel_condition method exception, value = " + ex.getMessage());
            return false;
        }
    }
}
