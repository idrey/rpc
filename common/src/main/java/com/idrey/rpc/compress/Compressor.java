package com.idrey.rpc.compress;

import com.idrey.rpc.enumeration.CompressCode;

public interface Compressor {

    byte[] compress(byte[] uncompressedData);
    int getCompressedLength();
    int getDecompressedLength();
    static Compressor getCompressor(CompressCode code) {
        switch (code) {
            case LZ4:
                return new RpcLz4Compressor();
            case ZSTD:
                return new RpcZstdCompressor();
            default:
                throw new IllegalArgumentException("Unknown compressor");
        }
    }

}
