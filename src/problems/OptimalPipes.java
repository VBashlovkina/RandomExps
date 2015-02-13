package problems;

import java.util.ArrayList;

public class OptimalPipes {

    public static int needPipes(int pipeLength, int[] requests) {
	ArrayList<Integer> usedPipes = new ArrayList<>();
	return needPipesHelper(pipeLength, requests, usedPipes, 0, requests.length);
    }

    @SuppressWarnings("unchecked")
    static int needPipesHelper(int pipeLength, int[] requests,
	    ArrayList<Integer> usedPipes, int startIndex, int currentMin) {

	if (startIndex > requests.length - 1)
	    return usedPipes.size();
	ArrayList<Integer> clone;
	int newResult;
	int i, j;
	boolean addedInTheForLoop = false;
	//for each request
	for (i = startIndex; i < requests.length; i++) {
	    int currentRequest = requests[i];
	    //for each used pipe, see if current request fits 
	    addedInTheForLoop = false;
	    for (j = 0; j < usedPipes.size(); j++)		    
	    {
		if (currentRequest <= pipeLength - usedPipes.get(j))
		{
		    clone = new ArrayList<Integer>();
		    for (int k = 0; k < usedPipes.size(); k++)
			clone.add(k, usedPipes.get(k));
		    clone.set(j, currentRequest + usedPipes.get(j));
		    addedInTheForLoop = true;
		    newResult = needPipesHelper(pipeLength, requests, clone, i+1, currentMin);
		    currentMin = currentMin > newResult ? newResult : currentMin;
		}
	    }
	    if (!addedInTheForLoop)
	    {
		clone = new ArrayList<Integer>();
		    for (int k = 0; k < usedPipes.size(); k++)
			clone.add(k, usedPipes.get(k));
		clone.add(requests[i]);
		newResult = needPipesHelper(pipeLength, requests, clone, i+1, currentMin);
		currentMin = currentMin > newResult ? newResult : currentMin;
	    }
	    
	}//for 

	return currentMin;
    }

    public static void main(String[] args) {
	int res = needPipes(10, new int[]{2, 9, 1});

    }

}
