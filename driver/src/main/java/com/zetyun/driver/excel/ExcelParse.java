package com.zetyun.driver.excel;

import com.zetyun.datatemplate.scenariotemplate.ScenarioDataTemplate;
import com.zetyun.driver.log.LogWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;

public class ExcelParse {
    private String filepath;
    private Workbook workbook;
    private Sheet sheet;

    /**
     * 默认构造函数
     * @param path
     */
    public ExcelParse(String path){
        try {
            this.filepath = path;
            workbook = WorkbookFactory.create(new File(this.filepath));
            LogWriter.error(ExcelParse.class, "Create Workbook success");
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "Create Workbook fail, exception: " + ex.getMessage());
        }
    }

    /**
     * 设置当前sheet
     * @param name sheet名称
     * @return
     */
    public boolean setWorkSheet(String name){
        try{
            sheet = workbook.getSheet(name);
            LogWriter.debug(ExcelParse.class, "Set Worksheet success");

            return true;
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get Sheets Attribute fail, exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * 设置当前sheet
     * @param pos sheet位置
     * @return
     */
    public boolean setWorkSheet(int pos){
        try{
            sheet = workbook.getSheetAt(pos);
            LogWriter.debug(ExcelParse.class, "Set Worksheet success");

            return true;
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get Sheets Attribute fail, exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * 获取当前sheet中的总可用行数
     * @return
     */
    public int getRows(){
        try {
            return this.sheet.getPhysicalNumberOfRows();
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get physical number Of rows fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取当前sheet总可用列数
     * @return
     */
    public int getColumn(){
        try {
            int rows = getRows();
            if (rows > 0){
                return this.sheet.getRow(0).getPhysicalNumberOfCells();
            }else{
                return -1;
            }
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get physical number Of rows fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取单元格的内容
     * @param row       行位置
     * @param column    列位置
     * @return
     */
    public String getCellValue(int row, int column){
        try {
            String value = "";

            Row cell_r = this.sheet.getRow(row);
            LogWriter.debug(ExcelParse.class, "Find cell row position success");

            Cell cell = cell_r.getCell(column);
            LogWriter.debug(ExcelParse.class, "Find cell column position success");

            switch (cell.getCellType()){
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
                default:
                    value = "";
                    break;
            }
            LogWriter.debug(ExcelParse.class, "Get cell value success");

            return value;
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get cell value fail, exception: " + ex.getMessage());
            return null;
        }
    }

    /**
     * 判断是否为合并区域
     * @param row
     * @param column
     * @return
     */
    public boolean isMerged(int row, int column){
        try {
            int sheetMergeCount = this.sheet.getNumMergedRegions();
            LogWriter.debug(ExcelParse.class, "get sheet merged regions number success");

            for (int i = 0; i < sheetMergeCount; i++) {
                CellRangeAddress range = this.sheet.getMergedRegion(i);
                LogWriter.debug(ExcelParse.class, "get merged regions range address success");

                int firstColumn = range.getFirstColumn();
                int lastColumn = range.getLastColumn();
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();

                if (row >= firstRow && row <= lastRow) {
                    if (column >= firstColumn && column <= lastColumn) {
                        LogWriter.debug(ExcelParse.class, "find merged region(row = " + String.valueOf(row) + ", column = " + String.valueOf(column) + ")");
                        return true;
                    }
                }
            }

            return false;
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "judge cell is merged fail, exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * 获取cell的起始行位置
     * @param row
     * @param column
     * @return
     */
    public int getCellStartRow(int row, int column){
        try {
            CellRangeAddress range = getMergedRange(row, column);
            if(range != null){
                return range.getFirstRow();
            }else {
                return -1;
            }
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get row start position fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取cell行结束位置
     * @param row
     * @param column
     * @return
     */
    public int getCellEndRow(int row, int column){
        try {
            CellRangeAddress range = getMergedRange(row, column);
            if(range != null){
                return range.getLastRow();
            }else {
                return -1;
            }
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get end start position fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取列起始位置
     * @param row
     * @param column
     * @return
     */
    public int getCellStartColumn(int row, int column){
        try {
            CellRangeAddress range = getMergedRange(row, column);
            if(range != null){
                return range.getFirstColumn();
            }else {
                return -1;
            }
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get column start position fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取列结束位置
     * @param row
     * @param column
     * @return
     */
    public int getCellEndColumn(int row, int column){
        try {
            CellRangeAddress range = getMergedRange(row, column);
            if(range != null){
                return range.getLastColumn();
            }else {
                return -1;
            }
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "get column end position fail, exception: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * 获取Merge cell range
     * @param row
     * @param column
     * @return
     */
    private CellRangeAddress getMergedRange(int row, int column){
        try {
            int sheetMergeCount = this.sheet.getNumMergedRegions();
            LogWriter.debug(ExcelParse.class, "get sheet merged regions number success");

            for (int i = 0; i < sheetMergeCount; i++) {
                CellRangeAddress range = this.sheet.getMergedRegion(i);
                LogWriter.debug(ExcelParse.class, "get merged regions range address success");

                int firstColumn = range.getFirstColumn();
                int lastColumn = range.getLastColumn();
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();

                if (row >= firstRow && row <= lastRow) {
                    if (column >= firstColumn && column <= lastColumn) {
                        LogWriter.debug(ExcelParse.class, "find merged region(row = " + String.valueOf(row) + ", column = " + String.valueOf(column) + ")");
                        return range;
                    }
                }
            }

            return null;
        }catch (Exception ex){
            LogWriter.error(ExcelParse.class, "judge cell is merged fail, exception: " + ex.getMessage());
            return null;
        }
    }
}
