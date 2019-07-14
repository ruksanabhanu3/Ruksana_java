package EvmImplDirectAddressing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
* The Class SourceEVMDirectAddressing program reads an output file of EVM and stores 
* the voter_id and candidate_id in the data structure.
* It Performs add, find and count operations.
*
* @author  Ruksana Bhanu
* @version 1.0
* @since   2018-05-09 
*/

class VoterCandidateRecord {
	int voter_id;
	int candidate_id;
}

class electionCount {
	VoterCandidateRecord[] list; //list of type VoterCandidateRecord. It contains voter_id and candidate_id.

	public electionCount() { //Constructor
		list = new VoterCandidateRecord[899999]; //Allocating Maximum size to the list.
	}
	
	 /**
	   * This method is used to add the voter_id and candidate_id to the list using DirectAddressing
	   * @param voter_id
	   * @param candidate_id
	   * @return Nothing. Stores the voter_id and candidate_id in the list.
	   */
	public void add(int voter_id, int candidate_id) {
		VoterCandidateRecord record = new VoterCandidateRecord();//Each record contains voter_id and candidate_id.
		record.voter_id = voter_id; 
		record.candidate_id = candidate_id;
		list[record.voter_id - 100000] = record;//Adding a record to the list at the correct index using DirectAddressing.
	}
	
	
	/**
	   * This method is used to find the candidate_id for whom vote was casted by using DirectAddressing
	   * @param voter_id . Should be of 6 digits and within the range.
	   * @return Nothing. Prints candidate_id for whom the vote was cast.
	   */	
	public void find(int voter_id) {
		if ((Integer.toString(voter_id) != null) && (Integer.toString(voter_id).length() == 6)) {//Checks for not null condition and voter_id with 6 digits or not.
			if (list[voter_id - 100000] == null)//Checks if the voter_id exists in the input data file or not
				System.out.println("No such VoterId exists in EVM data");
			else
				System.out.println(list[voter_id - 100000].candidate_id);//Prints Candidate_id by getting from its index.
		} else
			System.out.println("Not a Valid VoterId.Out of Range.");//Prints if voter_id is not in the 6 digits range.
	}
	
	/**
	   * This method is used to count the number of votes received by him/her by using DirectAddressing
	   * @param CandidateId . Should be of 3 digits and within the range.
	   * @return Count of votes. Prints the total number of votes received by him/her
	   */	
	public int count(int candidate_id) {
		int count = 0;
		if ((Integer.toString(candidate_id) != null) && (Integer.toString(candidate_id).length() == 3)) {//Checks for not null condition and candidate_id with 3 digits or not.
			for (int i = 0; i < list.length - 1; i++) {
				if ((list[i] != null) && (list[i].candidate_id == candidate_id))//if input candidate_id is exists in the list, count is incremented
					count++;
			}
			if (count > 0)
				System.out.println(count);//Prints the count of votes casted for the candidate_id
			else
				System.out.println("No Such CandidateId exists in EVM data");//Prints, if candidate_id is not present in input data file.
		} else
			System.out.println("Not a Valid CandidateId.Out of Range.");//Prints, if input is not in 3 digits range.s
		return count;
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
public class SourceDirectAddressing {

	public static void main(String[] args) throws IOException {
		String line = null;
		String fileName = "C:\\RUKSANA\\data.txt";
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		electionCount electionCount = new electionCount();
		while ((line = bufferedReader.readLine()) != null) {
			String[] a = line.split("\t");//Splits each line with tab delimeter.
			int voter_id=Integer.parseInt(a[0]);
			int candidate_id=Integer.parseInt(a[1]);
			
			if((Integer.toString(voter_id)!=null && Integer.toString(voter_id).length()==6))//Checks for appropriate range for voter_id and candidate_id
				if((Integer.toString(candidate_id)!=null && Integer.toString(candidate_id).length()==3))			
					electionCount.add(voter_id, candidate_id); //Method call for adding into datastructure.
				else
					System.out.println("Not a Valid CandidateId.Out of Range.");
			else
				System.out.println("Not a Valid voterId.Out of Range.");
		}
		
		bufferedReader.close();
		electionCount.find(151030);//Valid Input and exists in the data structure 
		electionCount.find(111111);//Valid Input but not exists in the data structure 
		electionCount.find(1210301);//Invalid Input. Out of Range.
		electionCount.find(000001);//Invalid Input. Out of Range.
		
		electionCount.count(135);//Valid Input and exists in the data structure 
		electionCount.count(111);//Valid Input but not exists in the data structure 
		electionCount.count(1351);//Invalid Input. Out of Range.
		electionCount.count(001);//Invalid Input. Out of Range.
	}

}