package com.weituitu.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * Created by Sundy on 2017/6/8.
 */
public class TestByteBufExamples {
    public static void main(String[] args) {
        // ByteBufExamples.heapBuffer();
        // ByteBufExamples.directBuffer();
        // ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
        // ByteBuffer byteBuffer2 = ByteBuffer.allocate(10);
        // ByteBufExamples.byteBufferComposite(byteBuffer1, byteBuffer2);
        // ByteBufExamples.byteBufComposite();
        // ByteBufExamples.byteProcessor();
        // ByteBufExamples.byteBufSlice();
        // ByteBufExamples.byteBufCopy();
        // ByteBufExamples.byteBufSetGet();
        ByteBufExamples.byteBufWriteRead();
    }
}
