package task2;

/**
 * 4x4 matrix
 */
public class MyMatrix implements Sumabil {
    private final int[][]matrix;

    public MyMatrix() {
        matrix = new int[4][4];
    }

    public MyMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public void addValue(Sumabil value) {
        try {
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    matrix[i][j] += ((MyMatrix) value).getMatrix()[i][j];
                }
            }
        } catch (Exception e) {
            System.out.println("A 4x4 matrix is required");
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyMatrix{matrix=[\n");

        for (int i = 0; i < 4; ++i) {
            result.append("\t");
            for (int j = 0; j < 4; ++j) {
                if (j < 3) {
                    result.append(this.matrix[i][j]).append(", ");
                } else {
                    result.append(this.matrix[i][j]);
                }
            }
            result.append(";\n");
        }

        result.append("]}\n");
        return result.toString();
    }
}