package org.spring.springboot.utils;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

public class CSVUtils {
    public  static List<File> getCSVFiles(String dirPath){
        File file = new File( dirPath);
        if (!file.isDirectory())
            return null;
        Collection<File> listFiles = FileUtils.listFiles(new File("dirPath"),FileFilterUtils.suffixFileFilter("csv"), DirectoryFileFilter.INSTANCE);
        List <File> csvList =  Lists.newArrayList();
        csvList.addAll(listFiles);
        return  csvList;
    }
    public static Iterable<CSVRecord> getRecord4CSV(File file) throws IOException {
        Reader in = new FileReader(file);
        return CSVFormat.EXCEL.parse(in);
    }
}
