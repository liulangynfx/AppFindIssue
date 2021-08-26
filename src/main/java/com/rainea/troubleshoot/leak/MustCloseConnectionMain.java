package com.rainea.troubleshoot.leak;

import com.rainea.troubleshoot.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 数据库、io等连接必须被关闭，否则连接信息不会被gc回收，下面分别实现手动关闭和通过try-with-resources语句块自动关闭
 *
 * @author liulang
 * @date 2021-08-25
 **/
public class MustCloseConnectionMain {

    public static void main(String[] args) {

    }

    /**
     * 手动关闭连接
     *
     * @param path
     * @throws IOException
     */
    public void manuallyClose(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            br.readLine();
        } finally {
            br.close();
        }
    }

    /**
     * 自动关闭连接
     *
     * @param zipFileName
     * @param outputFileName
     * @throws IOException
     */
    public void autoClose(String zipFileName, String outputFileName) throws IOException {
        java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);
        try (
                java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);
                java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath);
        ) {

            for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                // Get the entry name and write it to the output file
                String zipEntryName = ((java.util.zip.ZipEntry)entries.nextElement()).getName() + "\n";
                writer.write(zipEntryName, 0, zipEntryName.length());
            }
        }
    }
}
