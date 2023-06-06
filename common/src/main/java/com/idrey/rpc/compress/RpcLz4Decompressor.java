package com.idrey.rpc.compress;

import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class RpcLz4Decompressor implements Decompressor{
    private final LZ4FastDecompressor decompressor;

    public RpcLz4Decompressor() {
        this.decompressor = LZ4Factory.fastestInstance().fastDecompressor();
    }
    @Override
    public byte[] decompress(byte[] compressedData, int decompressedLength) {
        byte[] restored = new byte[decompressedLength];
        int length = decompressor.decompress(compressedData, 0, restored, 0, decompressedLength);
        return restored;
    }
}
