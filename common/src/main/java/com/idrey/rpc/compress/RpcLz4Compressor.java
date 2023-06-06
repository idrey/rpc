package com.idrey.rpc.compress;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class RpcLz4Compressor implements Compressor{
    private final LZ4Compressor compressor;
    private int compressedLength;
    private int decompressedLength;
    public RpcLz4Compressor() {
        this.compressor = LZ4Factory.fastestInstance().fastCompressor();
        this.compressedLength = 0;
        this.decompressedLength = 0;
    }
    @Override
    public byte[] compress(byte[] uncompressedData) {
        int uncompressedLength = uncompressedData.length;
        decompressedLength = uncompressedLength;
        int maxCompressedLength = compressor.maxCompressedLength(uncompressedLength);
        byte[] compressed = new byte[maxCompressedLength];
        compressedLength = compressor.compress(uncompressedData, 0, uncompressedLength, compressed, 0, maxCompressedLength);
        return compressed;
    }

    @Override
    public int getCompressedLength() {
        return compressedLength;
    }

    @Override
    public int getDecompressedLength() {
        return decompressedLength;
    }

}
