public class Perceptron {

    private double[] weights;
    private double threshold;

    public Perceptron(double[] weights, double threshold) {
        this.weights = weights;
        this.threshold = threshold;
    }

    public void deltaRule(double[] inputs, int d, int y, double learningRate) {

        int diff = d - y;
        double updateFactor = learningRate * diff;

        for (int i = 0; i < inputs.length; i++) {

            this.weights[i] = this.weights[i] + (inputs[i] * updateFactor);
        }

        this.threshold = this.threshold - updateFactor;
    }

    public int Output(double[] inputs) {

        double result = 0;
        for (int i = 0; i < inputs.length; i++) {
            result += inputs[i] * this.weights[i];
        }

        if (result > this.threshold) {
            return 1;
        } else {
            return 0;
        }

    }

    public double score(double[] inputs) {
        double result = 0;
        for (int i = 0; i < inputs.length; i++) {
            result += inputs[i] * this.weights[i];
        }
        return result;
    }

    public void normalizeWeights() {
        double norm = 0.0;

        for (double w : this.weights) {
            norm += w * w;
        }

        norm = Math.sqrt(norm);
        if (norm == 0) return;

        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] /= norm;
        }
    }
}
