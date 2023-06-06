package com.idrey.rpc.compress;

import com.github.luben.zstd.Zstd;

public class RpcZstdDecompressor implements Decompressor{
    @Override
    public byte[] decompress(byte[] compressedData, int decompressedLength) {
        return Zstd.decompress(compressedData, decompressedLength);
    }
}
