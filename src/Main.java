import java.util.*;

public class Main {
    public static void main(String[] args) {

        try{
            Map<String, List<String>> data = new LinkedHashMap<>(DataLoader.loadData("Languages"));

            List<String> languages = new ArrayList<>(data.keySet());
            List<Perceptron> perceptrons = new ArrayList<>();
            Random rand = new Random();

            for(String language : languages){
                double[] weights = new double[26];

                for(int i = 0; i < weights.length; i++){
                    weights[i] = rand.nextDouble() - 0.5;
                }

                perceptrons.add(new Perceptron(weights,0.0));
            }

            NeuralNet neuralNet = new NeuralNet(perceptrons,languages);
            Trainer trainer = new Trainer(neuralNet,0.01,data);
            trainer.learn();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter text: ");
            String text = scanner.nextLine();

            double[] input = TextProcessor.processText(text);
            String result = neuralNet.maximumSelector(input);

            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
