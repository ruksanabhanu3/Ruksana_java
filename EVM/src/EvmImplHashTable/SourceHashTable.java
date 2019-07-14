package EvmImplHashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
/**
* The Class SouceEVMHashTable program reads an output file of EVM and stores 
* the voter_id and candidate_id in the Hash Table data structure.
* It Performs add, find and count operations.
*
* @author  Ruksana Bhanu
* @version 1.0
* @since   2018-05-09 
*/
class ElectionCount {
	Hashtable<Integer, Integer> voterIdCandidateId = new Hashtable<Integer, Integer>(); //Hash table object voterIdCandidateId with key-Voter_id , value-Candidate_ids
	Hashtable<Integer, Integer> candidateIdCount = new Hashtable<Integer, Integer>(); //Hash table object candidateIdCount with key-candidate_id , value-count of votes casted for that candidate.
	/**
	   * This method is used to add the voter_id and candidate_id to the list using HashTable as Data Structure.
	   * and, stores candidateId and no .of votes casted for that candidate in candidateIdCount.
	   * @param voter_id
	   * @param candidate_id
	   * @return Nothing. Stores the voter_id and candidate_id in the list.
	   */
	public void add(int voter_id, int candidate_id) {
		voterIdCandidateId.put(voter_id, candidate_id);//Adds voter_id and candidate_id to the Hash table voterIdCandidateId.
		
		if(candidateIdCount.containsKey(candidate_id)) //Checks if candidateId already exists in candidateIdCount. If exists, it increments the count. Otherwise assigns 1.
			candidateIdCount.put(candidate_id, candidateIdCount.get(candidate_id)+1);
		else
			candidateIdCount.put(candidate_id, 1);	
	}

	/**
	   * This method is used to find the candidate_id for whom vote was casted by using HashTable as Data Structure.
	   * @param voter_id Should be of 6 digits and within the range.
	   * @return Nothing. Prints the candidate_id for whom the vote was cast.
	   */
	
	public void find(int voter_id) {
		if ((Integer.toString(voter_id) != null) && (Integer.toString(voter_id).length() == 6)) {//Checks for not null condition and voter_id with 6 digits or not.
			if(voterIdCandidateId.containsKey(voter_id)) {
				System.out.println(voterIdCandidateId.get(voter_id));//If Valid and Exists, prints candidateId by fetching using key. i.e, voter_id
			}
			else {
				System.out.println("No such VoterId exists in EVM data");//If input does'nt exists in the input data file.
			}
		}
		else
			System.out.println("Not a Valid VoterId.Out of Range.");//Prints if voter_id is not in the 6 digits range.
	}
	
	/**
	   * This method is used to count the number of votes received by him/her by using HashTable as Data Structure.
	   * @param CandidateId Should be of 3 digits and within the range.
	   * @return Nothing. Prints the candidate_id for whom the vote was cast.
	   */

	public void count(int candidate_id) {

		if ((Integer.toString(candidate_id) != null) && (Integer.toString(candidate_id).length() == 3)) {//Checks for not null condition and candidate_id with 3 digits or not.
			if(candidateIdCount.containsKey(candidate_id))
				System.out.println(candidateIdCount.get(candidate_id));//If Valid and Exists, prints candidateId by fetching using key. i.e, voter_id
			else 
				System.out.println("No such CandidateId exists in EVM data");//If input does'nt exists in the input data file
		}
		else
			System.out.println("Not a Valid CandidateID.Out of Range.");//Prints if voter_id is not in the 6 digits range.
	}
}

/**
 * This is the main method which makes use of add,find and count methods.
 * Reads an input file and performs the add, find and count methods
 * @param args Unused.
 * @return Nothing.
 * @exception IOException On input error.
 * @see IOException
 */
public class SourceHashTable {

	public static void main(String[] args) throws IOException {
		String line = null;
		String fileName = "C:\\RUKSANA\\data.txt";
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ElectionCount elc = new ElectionCount();
		while ((line = bufferedReader.readLine()) != null) {
			String[] a = line.split("\t");//Splits each line with tab delimeter.
			int voter_id=Integer.parseInt(a[0]);
			int candidate_id=Integer.parseInt(a[1]);
			
			if((Integer.toString(voter_id)!=null && Integer.toString(voter_id).length()==6))//Checks for appropriate range for voter_id and candidate_id
				if((Integer.toString(candidate_id)!=null && Integer.toString(candidate_id).length()==3))			
					elc.add(voter_id, candidate_id); //Method call for adding into datastructure.
				else
					System.out.println("Not a Valid CandidateId.Out of Range.");
			else
				System.out.println("Not a Valid voterId.Out of Range.");
		}
		elc.find(151030);//Valid Input and exists in the data structure 
		elc.find(121030);//Valid Input and but not exists in the data structure 
		elc.find(11111111);//Invalid Input. Out of Range.
		elc.find(000005);//Invalid Input. Out of Range.

		elc.count(135);//Valid Input and exists in the data structure 
		elc.count(111);//Valid Input and but not exists in the data structure 
		elc.count(1111);//Invalid Input. Out of Range.
		bufferedReader.close();
	}
}
