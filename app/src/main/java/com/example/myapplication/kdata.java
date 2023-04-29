/* import org.tensorflow.lite.support.tokenizer.Tokenizer;
import org.tensorflow.lite.support.tokenizer.Tokenizer.Model;
import org.tensorflow.lite.support.tokenizer.Tokenizer.Options;

public class MainActivity extends AppCompatActivity {
    private Tokenizer tokenizer;

    private void loadTokenizer() {
        // 토크나이저 모델 로드
        Model model = Model.newInstance(this.getApplicationContext());
        try {
            // assets 폴더에서 토크나이저 모델 파일 읽기
            InputStream stream = getAssets().open("tokenizer.tflite");
            // 토크나이저 모델 로드
            tokenizer = new Tokenizer.Builder()
                    .model(model)
                    .options(new Options.Builder()
                            .maxSeqLen(128) // 문장 길이를 128로 제한
                            .build())
                    .build();
            // 토크나이저 인스턴스 초기화
            tokenizer.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 모델 인스턴스 해제
            model.close();
        }
    }

    private List<Integer> encodeText(String text) {
        // 텍스트를 토큰화하여 인덱스 시퀀스로 변환
        List<String> tokens = tokenizer.tokenize(text);
        return tokenizer.encode(tokens);
    }

    private List<Integer> preprocessText(String text) {
        // 텍스트를 인코딩하고 패딩 처리를 수행하여 텐서플로우 모델의 입력 형식에 맞게 변환
        List<Integer> encoded = encodeText(text);
        // 패딩 처리
        while (encoded.size() < 128) {
            encoded.add(0);
        }
        return encoded.subList(0, 128);
    }

    private void someMethod() {
        // 파이어베이스에서 텍스트 데이터 가져오기
        String text = "가져온 텍스트 데이터";

        // 전처리 수행
        List<Integer> input = preprocessText(text);

        // 텐서플로우 라이트 모델에 입력으로 전달할 수 있는 형태로 변환
        float[][] inputTensor = new float[1][128];
        for (int i = 0; i < input.size(); i++) {
            inputTensor[0][i] = input.get(i);
        }

        // 모델 실행
        interpreter.run(inputTensor, outputTensor);
    }
}
*/