package test.interactive;

public class FindValue {

	public static final int MIN_VALUE = -0x80000000;
	
	public static int FindMinValue(int[] arrs ){
		if( null == arrs)
			return MIN_VALUE;
		int ilen = arrs.length;
		if( 1 == ilen ){
			return arrs[0];
		}
		int i = 0;
		for( i = 0; i < ilen - 1; ++i){
			if( arrs[i] > arrs[i+1]){
				continue;
			}else{
				return arrs[i];
			}
		}
		if(  ilen -1 == i)
			return arrs[i];
		return MIN_VALUE;
	}
	public static void main(String[] args) {  
		int [] arrs = { 9, 8, 6, 3, 5, 7};
		int val = FindMinValue( arrs );
		System.out.println(val);
	}
}
