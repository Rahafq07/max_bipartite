

import java.util.ArrayList;

/**
 * <h1> Assignment of Hospitals to Applicants using Maximum Bipartite Matching Algorithm. </h1>
 * 
 * the program will find an assignment of hospitals to applicants in such that as many applicants 
 * as possible get positions in the hospital using maximum bipartite matching algorithm. 
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 21-12-2021
 */

public class max_bipartite {

    // global variables declaration
    
    // create applicants and the hospitals arrays 
    static String[] Applicants = {"Ahmed", "Mahmoud", "Eman", "Fatimah", "Kamel", "Nojood"};
    static String[] Hospitalas = {"King Abdelaziz University", "King Fahd", "East Jeddah", "King Fahad Armed Forces", "King Faisal Specialist", "Ministry of National Guard"};
    
    //ArrayList to set applicants
    static ArrayList<Integer> set_applicants = new ArrayList<>();

    // create variables to store number of Applicants & Hospitalas
    static int Noapplicants = Applicants.length;
    static int NoHospitala = Hospitalas.length;
    
    //A graph with M applicant and N hospitals
    static int Graph[][] = new int[][]{ 
                                      {1, 1, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1},
                                      {1, 0, 0, 1, 0, 0},
                                      {0, 0, 1, 0, 0, 0},
                                      {0, 0, 0, 1, 1, 0},
                                      {0, 0, 0, 0, 0, 1}
                                      };
    
    //create applicants array to store assigning hospital 
    static int applicants[] = new int[NoHospitala];
    
    /**
     * This is the main method which print the starting sentence, and Call maxMatch method.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("------------------- WELCOME TO MAXIMUM BIPARTITE MATCHING ALGORITHM -------------------\n\n");
        System.out.println(">> The maximum possible number of assignments of hospitals to applicants: " + maxMatch()+"\n");
    }
    
    /**
     * This is the maximum matching method which apply Maximum Bipartite Matching algorithm then return number of matching found in the graph.
     * 
     * @return number of matching found in the graph
     */
    public static int maxMatch() {
        
        //an array to track which hospital is assigned to which applicant
        int track[] = new int[NoHospitala];    
        
        int NoOfMatch = 0;
        
        for (int i = 0; i < NoHospitala; i++) {
            track[i] = -1;    //initially set all jobs are available
            set_applicants.add(-1);     //initialize the set applicant(M) 
        }
        
        for (int  i= 0; i < Noapplicants; i++) {    //for all applicants
            
            boolean isvisited[] = new boolean[NoHospitala]; // create variables to check if the applicant is visited 
            
            if (bipartiteMatch(i, isvisited, track)) //when u get a hospital
            {
                System.out.println("set applicants: \n");
                
                for (int j = 0; j < set_applicants.size(); j++) {
                    
                    if(set_applicants.get(j)>-1)
                    System.out.print("> "+Applicants[j]+" TO "+Hospitalas[set_applicants.get(j)]+"\n");
                    
                }
                System.out.println("\n------------------------------------------------------------\n");
                NoOfMatch++; //increase number Of Match
            }

        }
        return NoOfMatch; //return number Of Match
    }
    
    /**
     * This is the bipartite Match method which check if a matching for applicant is possible and return true if a match is found.
     * 
     * @param i applicants index
     * @param isvisited an array to check if the applicant is visited 
     * @param track an array to track which hospital is assigned to which applicant
     * @return true if a match is found
     */
    public static boolean bipartiteMatch(int i, boolean isvisited[], int track[]) {
        
        for (int j = 0; j < NoHospitala; j++) {    //for all hospital 0 to NoHospitala-1
            if (Graph[i][j] == 1 && !isvisited[j]) {    //when hospital v is not isvisited and u is interested
                
                isvisited[j] = true;    //mark as hospital v is isvisited
                
                //when v is not tracked or previously tracked
                if (track[j] < 0 || bipartiteMatch(track[j], isvisited, track)) {
                    
                    track[j] = i;    //assign hospital v to applicant u
                    System.out.println("bipartite Match: "+Applicants[i] + " is assigned to " + Hospitalas[j]+"\n");
                    set_applicants.set(i, j); // add the edge to matching set M
                    applicants[j] = i;
                    return true;
                
                }
            }
        }
        return false;
    }

}

