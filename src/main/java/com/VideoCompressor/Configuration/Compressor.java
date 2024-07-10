package com.VideoCompressor.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Compressor {
    public static byte[] compressfile(byte[] data){
        Deflater def = new Deflater();
        def.setInput(data);
        def.setLevel(Deflater.BEST_COMPRESSION);
        def.finish();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (!def.finished()) {
            int size=def.deflate(buffer);
            baos.write(buffer,0,size);
        }
        try{
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] output = baos.toByteArray();
        System.out.println(data.length/1024+" KB");
        System.out.println(output.length/1024+" KB");
        return output;
    }
    public static  byte[]   decompressfile(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream stream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int size = inflater.inflate(buffer);
                stream.write(buffer, 0, size);
            }
            stream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream.toByteArray();
    }


}
