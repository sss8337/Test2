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
        // 모델 파일을 읽어옴
        FileInputStream inputStream = new FileInputStream(modelPath);
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = 0;
        long declaredLength = fileChannel.size();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

        // Interpreter 객체 생성
        mInterpreter = new Interpreter(mappedByteBuffer);

        // 입력과 출력 데이터의 모양 정보를 가져옴
        mInputShape = mInterpreter.getInputTensor(0).shape();
        mOutputShape = mInterpreter.getOutputTensor(0).shape();

        // TensorBuffer 객체 생성
        DataType inputDataType = mInterpreter.getInputTensor(0).dataType();
        mInputTensorBuffer = TensorBuffer.createFixedSize(mInputShape, inputDataType);
        DataType outputDataType = mInterpreter.getOutputTensor(0).dataType();
        mOutputTensorBuffer = TensorBuffer.createFixedSize(mOutputShape, outputDataType);
    }
}

