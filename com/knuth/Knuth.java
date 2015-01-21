package com.knuth;

public class Knuth
{
	String pat;
	String txt;
	Boolean is;
	int c=0;
	int count;
	public Knuth(String pat,String txt)
	{
		count=0;
		this.pat = pat;
		this.txt = txt;
		
		
	}
		public Boolean find()
		{
			int M = pat.length();
			int N = txt.length();
			for (int i = 0; i < N - M; i++)
			{
			int j;
			for (j = 0; j < M; j++)
			if (txt.charAt(i+j) != pat.charAt(j))
			break;
			if (j == M) return true;
			}
			return false; 
	}
		
		

		
}

