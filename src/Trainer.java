import java.util.List;
import java.util.Map;

public class Trainer {

    private NeuralNet neuralNet;
    private double learningRate;
    private Map<String, List<String>> texts;

    public Trainer(NeuralNet neuralNet, double learningRate, Map<String, List<String>> texts) {
        this.neuralNet = neuralNet;
        this.learningRate = learningRate;
        this.texts = texts;
    }


    public void learn() {

        List <Perceptron> perceptrons = neuralNet.getPerceptrons();
        List<String> languages = neuralNet.getLanguages();

        for(int j = 0; j < 50; j++){
            for (String language : texts.keySet()) {

                for (String text : texts.get(language)) {
                    double[] input = TextProcessor.processText(text);

                    for (int i = 0; i < perceptrons.size(); i++) {

                        Perceptron perceptron = perceptrons.get(i);

                        int d = languages.get(i).equals(language) ? 1 : 0;
                        int y = perceptron.Output(input);

                        if(d != y){
                            perceptron.deltaRule(input,d,y,learningRate);
                        }

                    }

                }
            }
        }

    }

}
