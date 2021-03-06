package at.archistar.crypto.math;

import de.flexiprovider.common.math.linearalgebra.GF2mMatrix;

/**
 * 
 * GF2mMatrix does not support rightMultiply, add a (very)
 * simple implementation of this operation.
 * 
 * @author Andreas Happe <andreashappe@snikt.net>
 */
public class CustomMatrix extends GF2mMatrix {
	public CustomMatrix(int[][] data) {
		super(GF256.gf256, data);
	}
	
	public CustomMatrix(byte[] encoded) {
		super(GF256.gf256, encoded);
	}

	public int[] rightMultiply(int vec[]) {
		
		assert(vec.length == matrix.length);
		assert(vec.length == matrix[0].length);
		
		int[] result = new int[vec.length];
		for(int i=0; i < vec.length; i++) {
			int tmp = 0;
			for (int j=0; j < vec.length; j++) {
				tmp = GF256.add(tmp, GF256.mult(matrix[i][j], vec[j]));
			}
			result[i] = tmp;
		}
		
		return result;
	}
	
	public int[] getRow(int i) {
		return matrix[i];
	}
	
	public void output() {
		System.err.println("matrix:");
		for(int [] tmp : matrix) {
			for(int i: tmp) {
				System.err.print(" " + i);
			}
			System.err.println("");
		}
	}

}
