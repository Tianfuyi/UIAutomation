package com.zetyun.driver.interpreter;

import com.zetyun.driver.log.LogWriter;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    public static boolean isSingleExpression(String condition) {
        if (condition.contains(":")) {
            String[] a = condition.split(":");
            if (a.length - 1 == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断condition是否为组合表达式
     *
     * @param condition
     * @return
     */
    public static boolean isExpression(String condition) {
        if (condition.contains("&") ||
                condition.contains("|") ||
                condition.contains("(") ||
                condition.contains(")")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从expression中，获取子condition或combination
     *
     * @param expression
     * @return
     */
    public static List<String> getExpressions(String expression) {
        try {
            List<String> ans = new ArrayList<>();
            int a = 0;
            StringBuilder sb = new StringBuilder();

            LogWriter.debug(Interpreter.class, "Begin to parse expression[" + expression + "]");

            while (a < expression.length()) {
                if (isKeyword(expression.charAt(a))) {
                    if (sb.toString().length() > 0) {
                        LogWriter.debug(Interpreter.class, "Find a expression, value = " + sb.toString());
                        ans.add(sb.toString());
                    }
                    LogWriter.debug(Interpreter.class, "Add a keyword to list, value = " + String.valueOf(expression.charAt(a)));
                    ans.add(String.valueOf(expression.charAt(a)));
                    sb = new StringBuilder();
                } else if (isLeftBacket(expression.charAt(a))) {
                    int i = a;
                    while (i < expression.length()) {
                        if (isRightBracket(expression.charAt(i))) {
                            sb.append(expression.charAt(i));
                            ans.add(sb.toString());
                            LogWriter.debug(Interpreter.class, "Find a expression, value = " + sb.toString());
                            sb = new StringBuilder();
                            break;
                        } else {
                            sb.append(String.valueOf(expression.charAt(i)));
                        }
                        i++;
                    }
                    a = i;
                } else {
                    sb.append(expression.charAt(a));
                }

                a++;
            }

            if (sb.toString().length() > 0) {
                ans.add(sb.toString());
            }

            LogWriter.debug(Interpreter.class, "Parse expression[" + expression + "] success");
            return ans;
        } catch (Exception ex) {
            LogWriter.debug(Interpreter.class, "getExpressions exception, value = " + ex.getMessage());
            return null;
        }
    }

    /**
     * 判断condition是否为联合表达式
     *
     * @param condition
     * @return
     */
    public static boolean isCombination(String condition) {
        if (condition.startsWith("(") && condition.endsWith(")")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取condition的集合
     *
     * @param expression
     * @return
     */
    public static List<String> getConditions(String expression) {
        try {
            if (isCombination(expression)) {
                List<String> stringList = new ArrayList<>();
                int a = 0;
                String message = expression.replaceAll("[\\(\\)]", "");
                StringBuilder sb = new StringBuilder();

                LogWriter.debug(Interpreter.class, "Begin to parse expression[" + expression + "]");

                while (a < message.length()) {
                    if (isKeyword(message.charAt(a))) {
                        if (sb.toString().length() > 0) {
                            LogWriter.debug(Interpreter.class, "Find a expression, value = " + sb.toString());
                            stringList.add(sb.toString());
                        }
                        LogWriter.debug(Interpreter.class, "Add a keyword to list, value = " + String.valueOf(message.charAt(a)));
                        stringList.add(String.valueOf(message.charAt(a)));
                        sb = new StringBuilder();
                    } else {
                        sb.append(message.charAt(a));
                    }

                    a++;
                }

                if (sb.toString().length() > 0) {
                    stringList.add(sb.toString());
                }

                LogWriter.debug(Interpreter.class, "Parse expression[" + expression + "] success");
                return stringList;
            } else {
                LogWriter.debug(Interpreter.class, "Expression not start with ( or end with )");
                return null;
            }
        } catch (Exception ex) {
            LogWriter.debug(Interpreter.class, "getConditions exception, value = " + ex.getMessage());
            return null;
        }
    }

    /**
     * 判断是否为预定义关键字，包括& |
     *
     * @param c
     * @return
     */
    private static boolean isKeyword(char c) {
        boolean flag;
        switch (c) {
            case '&':
            case '|':
                flag = true;
                break;
            default:
                flag = false;
                break;
        }

        return flag;
    }

    /**
     * 判断是否为左括号
     *
     * @param c
     * @return
     */
    private static boolean isLeftBacket(char c) {
        boolean flag;
        switch (c) {
            case '(':
                flag = true;
                break;
            default:
                flag = false;
                break;
        }

        return flag;
    }

    /**
     * 判断是否为右括号
     *
     * @param c
     * @return
     */
    private static boolean isRightBracket(char c) {
        boolean flag;
        switch (c) {
            case ')':
                flag = true;
                break;
            default:
                flag = false;
                break;
        }

        return flag;
    }
}
