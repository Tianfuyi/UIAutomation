package com.zetyun.uitest.utility;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * 文件操作工具
 */
public class FileUtil {

    public static List<String> getFileNames(String path){
        List<String> names = new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                //System.out.println(file.getName());
                names.add(file.getName());
            }
        }
        return names;
    }

    public static boolean createFile(String fileName){

        File file= new File(fileName);
        if (file.exists()) {
            System.out.println("Create file " + fileName + "failure. The file exists already.");
            return false;
        }

        if (fileName.endsWith(File.separator)){
            System.out.println("Create file " + fileName + "failure. The file can't be a folder.");
            return false;
        }

        //check the destination folder

        if (!file.getParentFile().exists()){
            System.out.println("The destination folder doesn't exist. We need to create it.");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("The destination folder creation failure.");
                return false;

            }

        }

        try {


            if (file.createNewFile()){
                System.out.println("Create file " + fileName + "successfully.");
                return true;
            }
            else{
                System.out.println("Create file " + fileName + "doesn't successfully.");
                return false;

            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Create file " + fileName + "doesn't successfully." + e.getMessage());
            return false;

        }

    }

    public static boolean createDir(String dirName){

        File dir = new File(dirName);
        if (dir.exists()){
            System.out.println("Folder creation " + dirName + "Failure. The Folder exists already.");
            return false;
        }

        if (dir.mkdirs()){
            System.out.println("Folder creation " + dirName + "success.");
            return true;
        }
        else {
            System.out.println("Folder creation " + dirName + "Failure.");
            return false;

        }


    }

    public static void deleteFile(String dirName){
        File myDelFile = new File(dirName);
        try {
            myDelFile.delete();
        }
        catch (Exception e) {
            System.out.println("Folder delete " + dirName + "Failure.");
            e.printStackTrace();
        }
    }

    public static void deleteAllFilesInFolder(String dir) {
        File folder = new File(dir);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File f : files){
                f.delete();
              //  Reporter.log(f+" was deleted!",true);
            }
        }else
            Assert.assertTrue(dir+" is not a directory!",false);
    }

    public static void moveFile(String oldDirName, String newDirName){
        try {
            File afile = new File(oldDirName);
            if (afile.renameTo(new File(newDirName))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isDownloadFile(String dirName){
        boolean flag = false;
        File dir = new File(dirName);
        if(dir.listFiles().length!=0){
            flag = true;
        }
        return flag;
    }


    public static boolean isDownloadFolderNotEmpty(String dirName){
        boolean flag = false;
        File dir = new File(dirName);
        String[] filelist = dir.list();
        System.out.println(filelist.length);
        if(filelist.length!=0){
            flag = true;
        }
        return flag;
    }

    public static boolean isExpectedColumnInFile(String dirName, String expectedColumn) throws Exception{
        boolean flag = false;
        File dir = new File(dirName);
        String[] filelist = dir.list();
        String fileName = filelist[0];

        String inputFileName = dirName+"\\"+fileName;

        File inputFile = new File(inputFileName);

        List<String> columns = readXlsx(inputFile);

        for(String col:columns){
            if(expectedColumn.equals(col)){
                flag = true;
                break;
            }
        }
        return flag;
    }

//	public static void main(String[] arg) throws Exception {
//
//		isExpectedColumnInFile("C:\\Users\\esupport\\Downloads","a");
//	}

    public static List<String> readXlsx(File inputFile)
    {

        List<String> cellValues = new ArrayList<String>();
        try
        {
            // Get the workbook instance for XLSX file
            @SuppressWarnings("resource")
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));

            // Get first sheet from the workbook
            XSSFSheet sheet = wb.getSheetAt(0);

            Row row;
            Cell cell;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();


            while (cellIterator.hasNext())
            {
                cell = cellIterator.next();
                System.out.println(cell.getStringCellValue());
                cellValues.add(cell.getStringCellValue());
            }


        }
        catch (Exception e)
        {
            System.err.println("Exception :" + e.getMessage());
        }
        return cellValues;
    }

    public static List<String> readCSVFile(String filePath) throws FileNotFoundException {
        //String downloadDir = System.getProperty("user.dir")+"\\Download\\IWS_Batch_Lookup_Template.csv";
        Scanner scanner = new Scanner(new File(filePath),"UTF-8");
        scanner.useDelimiter(",");

        List<String> arrStr = new ArrayList<String>();

        while(scanner.hasNext()){
            arrStr.add(scanner.next());
            System.err.println(scanner.next()+"|");

        }
        scanner.close();

        return arrStr;
    }

    public static String readHtml(String filePath){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }

        return contentBuilder.toString();
    }

    public static void copyFile(String sourcePath, String destPath) throws Exception{
        //String basePath = System.getProperty("user.dir");
        //String tempateFilePath = basePath+"\\Download\\IWS_Batch_Lookup_Template.csv";
        //String storedFile =  basePath+"\\DataProviders\\iwsBatchQuery\\IWS_Batch_Lookup_Template.csv";

        File source = new File(sourcePath);
        File dest = new File(destPath);
        Files.copy(source.toPath(), dest.toPath());
    }

    public static String getDesktopPath(){
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        return desktopPath;
    }

    public static void main(String[] args){
        String dirName = System.getProperty("user.dir") + "\\Download";
        createDir(dirName);

        File dir = new File(dirName);
        String[] filelist = dir.list();

        for (int i = 0; i < filelist.length; i++) {
            System.out.println(dirName + "\\" + filelist[i]);
            File readfile = new File(dirName + "\\" + filelist[i]);
            if(!readfile.isDirectory()){
                readfile.delete();
            }
        }


    }

    //	public static void main(String[] arg) throws Exception {
    //
    //		isExpectedColumnInFile("C:\\Users\\esupport\\Downloads","a");
    //	}





}
