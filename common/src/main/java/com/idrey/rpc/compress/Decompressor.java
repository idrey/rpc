package com.idrey.rpc.compress;

import com.idrey.rpc.enumeration.CompressCode;

public interface Decompressor {
    byte[] decompress(byte[] compressedData, int decompressedLength);

    static Decompressor getDecompressor(CompressCode code) {
        switch (code) {
            case LZ4:
                return new RpcLz4Decompressor();
            case ZSTD:
                return new RpcZstdDecompressor();
            default:
                throw new IllegalArgumentException("Unknown decompressor");
        }
    }

}
