package com.example.myapplication;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class T1 {

    private Interpreter mInterpreter;
    private TensorBuffer mInputTensorBuffer;
    private TensorBuffer mOutputTensorBuffer;
    private int[] mInputShape;
    private int[] mOutputShape;

    public T1(String modelPath) throws IOException {

        FileInputStream inputStream = new FileInputStream(modelPath);
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = 0;
        long declaredLength = fileChannel.size();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);


        mInterpreter = new Interpreter(mappedByteBuffer);


        mInputShape = mInterpreter.getInputTensor(0).shape();
        mOutputShape = mInterpreter.getOutputTensor(0).shape();


        DataType inputDataType = mInterpreter.getInputTensor(0).dataType();
        mInputTensorBuffer = TensorBuffer.createFixedSize(mInputShape, inputDataType);
        DataType outputDataType = mInterpreter.getOutputTensor(0).dataType();
        mOutputTensorBuffer = TensorBuffer.createFixedSize(mOutputShape, outputDataType);
    }
}

