import java.util.*;

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
        List<Perceptron> perceptrons = neuralNet.getPerceptrons();
        List<String> languages = neuralNet.getLanguages();


        List<Map.Entry<String, String>> allExamples = new ArrayList<>();
        for (String language : texts.keySet()) {
            for (String text : texts.get(language)) {
                allExamples.add(Map.entry(language, text));
            }
        }

        Random rand = new Random();
        for (int j = 0; j < 500; j++) {
            Collections.shuffle(allExamples, rand);

            for (Map.Entry<String, String> example : allExamples) {
                String language = example.getKey();
                double[] input = TextProcessor.processText(example.getValue());

                for (int i = 0; i < perceptrons.size(); i++) {
                    Perceptron perceptron = perceptrons.get(i);
                    int d = languages.get(i).equals(language) ? 1 : 0;
                    int y = perceptron.Output(input);
                    if (d != y) {
                        perceptron.deltaRule(input, d, y, learningRate);
                    }
                }
            }
        }
    }

}
