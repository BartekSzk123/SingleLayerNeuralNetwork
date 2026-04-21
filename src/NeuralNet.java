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

        if(languages.size() != perceptrons.size()){
            throw new IllegalArgumentException("The number of languages does not match the number of perceptrons");
        }

        double maxScore = Double.NEGATIVE_INFINITY;
        String language = "";
        normalizeInput(inputs);

        for(int i = 0; i < perceptrons.size(); i++){
            Perceptron perceptron = perceptrons.get(i);
            perceptron.normalizeWeights();
            double score = perceptron.score(inputs);

            if(score > maxScore){
                maxScore = score;
                language = languages.get(i);
            }
        }
        return language;
    }

    private void normalizeInput(double[] inputs){
        double norm = 0.0;
        for(double input : inputs){
            norm += input * input;
        }
        norm = Math.sqrt(norm);
        if (norm == 0) return;

        for(int i = 0; i < inputs.length; i++){
            inputs[i] /= norm;
        }
    }

    public List<Perceptron> getPerceptrons(){
        return perceptrons;
    }

    public List<String> getLanguages(){
        return languages;
    }


}
