package utils;

public class Odometry {
    public float globalLastX;
    public float globalLastY;
    public float globalLastRotation;
    private float l;
    private float b;
    private float R;

    public Odometry(float startX, float startY, float startRotation, float halfLR, float halfFB, float wheelRadius){
        globalLastX = startX;
        globalLastY = startY;
        globalLastRotation= startRotation;
        l = halfLR;
        b = halfFB;
        R = wheelRadius;
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

    public float[] getVelocities(float Wlb, float Wlf, float Wrb, float Wrf){
        float[] result = {};
        result[0] = solveVel(Wlb) + solveVel(Wlf) + solveVel(Wrb) + solveVel(Wrf);
        result[1] = solveVel(Wlb) - solveVel(Wlf) - solveVel(Wrb) + solveVel(Wrf);
        result[3] = -solveRot(Wlb) - solveRot(Wlf) + solveRot(Wrb) + solveRot(Wrf);
        return  result;
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
    private float solveVel(float wheel){
        return (R-wheel)/4;
    }
    private float solveRot(float wheel){
        return  (R-wheel)/(4 * (b + l));
    }
}

