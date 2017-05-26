package squarelotron;

public class Squarelotron {
    int[][] squarelotron;
    int size;

    // check check
    public Squarelotron(int n) {
        this.size = n;
        squarelotron = new int[n][n];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                squarelotron[i][j] = (size * i) + j + 1;
            }
        }
    }

    // Begin Helper methods

    /**
     * Display Square
     */
    public void showSquare() {
        for(int i = 0; i < this.squarelotron.length; i++) {
            for(int j = 0; j < this.squarelotron.length; j++) {
                System.out.printf(" %2d ",this.squarelotron[i][j]);
            }
            //	    remove method  when I submit
            System.out.println();
            System.out.println();
        }
    }


    public int findMaxRings() {
        return  (this.size % 2 == 0) ? (size / 2) : (size / 2) + 1;

    }

    public static void showArr(int[][] a) {
        for (int i=0;i<a.length;i++) {
            for(int j=0;j<a.length;j++) {
                System.out.printf(" %2d ",a[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }

    // called like:  originalSquare.swap(newSquare);
    public void swap(Squarelotron s) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                s.squarelotron[i][j] = this.squarelotron[i][j];
            }
        }
    }

    //    Begin Squarelotron Methods   //

    public Squarelotron upsideDownFlip(int ring) throws NumberFormatException {
        if( ring > this.findMaxRings() || ring < 1) {
            throw new NumberFormatException();
        }
        Squarelotron s = new Squarelotron(size);
        this.swap(s);

        int first = ring -1; // will be 1 -> 0, 2 -> 1, etc
        int last = size - ring;
        for(int i = 0; i <= size -1; i++) {
            for(int j = 0; j <= size -1; j++) {
                if (i == first || i == last) {
                    if (j >= first && j <= last) {
                        s.squarelotron[i][j] = this.squarelotron[size -1 -i][j];
                    }
                    else {
                    }
                }
                else if (i > first && i < last) { // gets all rows in between
                    if (j == first || j == last) {
                        s.squarelotron[i][j] = this.squarelotron[size -1 -i][j];
                    }
                }
            }
        }
        return s;
    }

    /**
     * Flips Square top to bottom
     * @param ring - integer
     * @return
     */
    public Squarelotron mainDiagonalFlip(int ring) throws NumberFormatException {
        if (ring > this.findMaxRings() || ring < 1) {
            throw new NumberFormatException();
        }
        int first = ring -1;
        int last = size - ring;
        Squarelotron s = new Squarelotron(size);
        this.swap(s);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (i == first || i == last) {
                    if (j >= first && j <= last) {
                        // process start and last row here
                        s.squarelotron[i][j] = this.squarelotron[j][i];
                    }
                }
                else if (i > first && i < last) {
                    if (j == first || j == last) {
                        s.squarelotron[i][j] = this.squarelotron[j][i];
                    }
                }
            }
        }
        return s;
    }


    public void rotateRight(int numberOfTurns) {

        int ring;
        boolean right = true;
        int maxRing = findMaxRings();

        // A Square to use as temp storage bewtween rotations
//	Squarelotron z = new Squarelotron(size);
        int [][] tmpArr = new int[size][size];
//
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                tmpArr[i][j] = this.squarelotron[i][j];
            }
        }

        if(numberOfTurns < 0) {
            right = false;
            numberOfTurns *= -1;
        }

        // Go through rings, to rotate entire Square
//	for(int l = 1; l <= maxRing; l++) {
        for(int k = 0; k < numberOfTurns; k++) { // loop each ring numberOfTurns

            for(int l = 1; l <= maxRing; l++) {
                ring = l;
                int first = ring -1;
                int last = size - ring;

                for(int i = 0; i < size; i++) { // outer loop
                    for(int j = 0; j < size; j++) { // inner loop

                        if (i == first) { // first row
                            if (j >= first && j <= last) { // first row within range

                                this.squarelotron[i][j] = (right) ? tmpArr[size -1 -j][first] :
                                        tmpArr[j][last];
                            } // all other elements in first row

                        }
                        else if (i == last) { // last row

                            if (j >= first && j <= last) { // elements within range
                                this.squarelotron[i][j] = (right) ? tmpArr[size -1 -j][last] :
                                        tmpArr[j][first];
                            } // all other elements in last row//
                        }

                        else if (i > first && i < last) {

                            if (j == last) { // in between rows -Right column
                                // process middle rows first and last here
                                this.squarelotron[i][j] = (right) ? tmpArr[first][i] :
                                        tmpArr[last][size - 1 -i];
                            }
                            else if (j == first) { // in between rows first column
                                this.squarelotron[i][j] = (right) ? tmpArr[last][i] :
                                        tmpArr[first][size - 1 -i];
                            }
                        }
                    }
                }
            }
            // copy arrays to temp array
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    tmpArr[i][j] = this.squarelotron[i][j];
                }
            }
        }
    }



    public static void main(String[] args) {
        Squarelotron s = new Squarelotron(4);
        s.upsideDownFlip(2);
    }




}