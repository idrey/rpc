package com.idrey.rpc.test;

import com.github.luben.zstd.Zstd;
import com.idrey.rpc.compress.Compressor;
import com.idrey.rpc.compress.Decompressor;

import java.util.Random;

public class CompressorTest {
    public static void main(String[] args) {
        byte[] data = new byte[300000];
        for(int i = 0; i < data.length; i++) {
            data[i] = (byte) new Random().nextInt(20);
        }
//        byte[] compressedData = compressor.compress(data);
//        System.out.println("Compress length: " + compressedData.length);
//        int uncompressedLength = compressor.getDecompressedLength();
//        int compressedLength = compressor.getCompressedLength();
//        byte[] uncompressedData = decompressor.decompress(compressedData, uncompressedLength);
//        System.out.println("Decompress length: " + uncompressedData.length);
        byte[] zstdCompressData = Zstd.compress(data);
        byte[] uncompressed = Zstd.decompress(zstdCompressData, 300000);
        System.out.println(zstdCompressData.length);
        System.out.println(uncompressed.length);
    }
}
