package com.idrey.rpc.compress;

import com.github.luben.zstd.Zstd;

public class RpcZstdCompressor implements Compressor{
    private int compressedLength;
    private int decompressedLength;

    @Override
    public byte[] compress(byte[] uncompressedData) {
        this.decompressedLength = uncompressedData.length;
        byte[] compressedData = Zstd.compress(uncompressedData);
        this.compressedLength = compressedData.length;
        return compressedData;
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
