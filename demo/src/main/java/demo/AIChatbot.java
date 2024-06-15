package demo;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;

public class AIChatbot {
    private MultiLayerNetwork model;

    public AIChatbot(String modelPath) throws IOException {
        model = ModelSerializer.restoreMultiLayerNetwork(new File(modelPath));
    }

    public String getResponse(String input) {
        // Xử lý input và chuyển đổi thành INDArray phù hợp
        INDArray inputArray = Nd4j.create(new float[]{/* chuyển đổi input thành mảng float */});
        INDArray outputArray = model.output(inputArray);
        
        // Xử lý output để chuyển đổi thành string
        return outputArray.toString();
    }
}
