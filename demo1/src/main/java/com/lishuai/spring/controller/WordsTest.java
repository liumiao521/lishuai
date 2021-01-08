package com.lishuai.spring.controller;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class WordsTest {
    /**
     * maven打包时，需要把模板文件取消打包，要不然会有进制问题
     * @param args
     */
    public static void main(String[] args) {

    }

    public void wordUtils() throws Exception {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("处罚通知单.docx");
        Document doc = new Document(stream);
        doc.getRange().replace("projectName", "123", true, false); //data.getDepartment 部门名称
        ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
        // 保存为word
        doc.save(dstStream, SaveFormat.DOCX);
        // 直接保存为pdf
        ByteArrayOutputStream dstStream2 = new ByteArrayOutputStream();
        doc.save(dstStream, SaveFormat.PDF);

    }
}
