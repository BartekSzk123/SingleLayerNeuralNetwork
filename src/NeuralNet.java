import java.util.List;
import java.util.Map;

public class NeuralNet {

    private List<Perceptron> perceptrons;
    private List<String> languages;

    public NeuralNet(List<Perceptron> perceptrons, List<String> languages) {
        this.perceptrons = perceptrons;
        this.languages = languages;
    }

    public String maximumSelector(double[] inputs){

        double maxScore = 0;
        String language = "";

        for(int i = 0; i < perceptrons.size(); i++){
            Perceptron perceptron = perceptrons.get(i);
            double score = perceptron.Score(inputs);

            if(score > maxScore){
                maxScore = score;
                language = languages.get(i);
            }
        }
        return language;
    }


}
