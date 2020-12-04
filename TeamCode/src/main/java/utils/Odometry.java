package utils;

public class Odometry {
    float globalLastX;
    float globalLastY;
    float globalLastRotation;

    public Odometry(float x, float y, float rotation){
        globalLastX = x;
        globalLastY = y;
        globalLastRotation= rotation;
    }

    public float[] calculate(float xChange, float yChange, float rotationChange){
        float newGlobalX;
        float newGlobalY;
        float newGlobalRotation;

        float[][] M1Global = {{globalLastX}, {globalLastY}, {globalLastRotation}};
        float[][] M2Global = {
                {(float) Math.cos(globalLastRotation), (float) -Math.sin(globalLastRotation), 0},
                {(float) Math.sin(globalLastRotation), (float) Math.cos(globalLastRotation), 0},
                {0, 0, 1}
        };
        float[][] M3Global = {
                {(float) (Math.sin(rotationChange)/rotationChange), (float) (-(1 - Math.cos(rotationChange))/rotationChange), 0},
                {(float) ((1-Math.cos(rotationChange))/rotationChange), (float) (Math.sin(rotationChange)/rotationChange), 0},
                {0, 0, 1}
        };
        float[][] M4Global = {
                {xChange},
                {yChange},
                {rotationChange}
        };
        float[][] currentPosition = this.addMatrices(M1Global, this.multiplyMatrices(this.multiplyMatrices(M2Global, M3Global), M4Global));// M2Global * M3Global * M4Global;

        float[] finalCurrentPosition = new float[]{currentPosition[0][0], currentPosition[0][1], currentPosition[0][2]};

        globalLastX = finalCurrentPosition[0];
        globalLastY = finalCurrentPosition[1];
        globalLastRotation = finalCurrentPosition[2];

        return finalCurrentPosition;
    }


        public static float[][] multiplyMatrices(float[][] A, float[][] B) {

            int aRows = A.length;
            int aColumns = A[0].length;
            int bRows = B.length;
            int bColumns = B[0].length;

            if (aColumns != bRows) {
                throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
            }

            float[][] C = new float[aRows][bColumns];
            for (int i = 0; i < aRows; i++) {
                for (int j = 0; j < bColumns; j++) {
                    C[i][j] = 0;
                }
            }

            for (int i = 0; i < aRows; i++) { // aRow
                for (int j = 0; j < bColumns; j++) { // bColumn
                    for (int k = 0; k < aColumns; k++) { // aColumn
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }

            return C;
        }
        public static float[][] addMatrices(float[][] A, float[][] B){
            float[][] c = {};
            for(int i = 0; i < A.length; i++){
                for(int j = 0; j < A[0].length; j++){
                    c[i][j] = A[i][j] + B[i][j];
                }
            }
            return c;
        }

}

