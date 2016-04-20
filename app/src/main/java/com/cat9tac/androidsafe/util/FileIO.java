package com.cat9tac.androidsafe.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by v_ypfu on 2016/3/30.
 */
public class FileIO {
    private  final static String sdcard_path="/mnt/sdcard/";
    public void getFileEncrypt(String url,String password) throws Exception {
        File file = new File(url);
        if (file.exists()) {

            String str=getByte(file).toString().trim();
            String result = AESCipher.encrypt(password, str);
            File encryptedFile=createFile(url);
            writeFile(encryptedFile,result.getBytes());
        }
    }

    public void getFileDecrypt(String target,String url,String password) throws Exception {
        File encryptfile = createFile(target);
        File decryptfile = createFile(url);
        byte[] encryptBytes=getByte(encryptfile);
        String s=AESCipher.decrypt(password,encryptBytes.toString());
        writeFile(decryptfile,s.getBytes());
    }
    public File createFile(String url) throws IOException {
        File file=new File(url);
        if(!file.exists()){
            file.createNewFile();
        }
        return file;

    }
    public void writeFile(File file,byte[] bytes) throws IOException {
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes);
        fos.close();

    }

    public byte[] getByte(File file) throws IOException {
        byte[] bytes = null;
        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) {
                Loger.logd("文件太大");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                Loger.logd("文件长度出错");
                return null;
            }
            is.close();
        }
        return bytes;
    }
}
