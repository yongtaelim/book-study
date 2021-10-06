package com.jessyt.object.chapter4.sub2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by LYT to 2021/10/05
 */
public class Page {
    public byte[] content(File file) throws IOException {
        byte[] array = new byte[1000];
        new FileInputStream(file).read(array);
        return array;
    }

    public int length(File file) throws Exception {
        try {
            return content(file).length;
        } catch (IOException e) {
            throw new Exception("계산이 불가능하다.", e);
        }
    }

    public int length(File file) throws IOException {
        return content(file).length;
    }

    public String content() throws IOException {
        int attempt = 0;
        while (true) {
            try {
                return http();
            } catch (IOException ex) {
                if (attempt >= 2) {
                    throw ex;
                }
            }
        }
    }

    @RetryOnFailure(attempts = 3)
    public String content() throws IOException {
        return http();
    }
}


