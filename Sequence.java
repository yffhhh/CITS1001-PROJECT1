/**
 * Sequence models sequences.
 * 
 * @author Lyndon WhiledisplaySimple 
 * @version 1.0
 */
import java.util.ArrayList;

public class Sequence
{
    // the numbers in the sequence 
    private ArrayList<Double> sequence;

    // sets up sequence by parsing s 
    // e.g. Sequence("3, -4, 8.5") sets sequence to <3, -4, 8.5> 
    public Sequence(String s)
    {
        sequence = new ArrayList<>();
        for (String x : s.split(",")) 
            sequence.add(Double.parseDouble(x));
    }

    // returns sequence 
    public ArrayList<Double> getSequence()
    {
        return sequence;
    }

    // returns the product of 1..x 
    // e.g. factorial(4) = 1 * 2 * 3 * 4 returns 24 
    public int factorial(int x)
    {
        // TODO
        if (x==0)
            return 1;
        else
            return x*factorial(x-1);

    }

    // returns true iff all of the items on seq are equal 
    // e.g. allEqual(<4, 4, 4>) returns true, and allEqual(<3, 3, -2>) returns false 
    public boolean allEqual(ArrayList<Double> seq)
    {
        // TODO      
        Double first=seq.get(0);
        for(Double seqs:seq)
        {
            if(!seqs.equals(first))
            {
                return false;
            }
        }
        return true;
    }

    // returns a new ArrayList holding the differences between adjacent items on seq 
    // e.g. differences(<4, 6, 1, 1>) returns <2, -5, 0>  
    // e.g. differences(<4, 6>) returns <2> 
    // e.g. differences(<4>) returns <？？？> 
    public ArrayList<Double> differences(ArrayList<Double> seq)
    {
        // TODO
        int size=seq.size();
        ArrayList<Double> diff=new ArrayList<>();
        for(int i=0;i<size-1;i++) //diff always one value shorter than original seq
        {
            Double first=seq.get(i);
            Double second=seq.get(i+1); 
            Double differences=second-first;
            diff.add(differences);
        }
        return diff;
    }

    // returns the next term in the simplest polynomial that generates sequence 
    // implements Steps 1-3 of the algorithm description on the project web page 
    public Term nextTerm()
    {
        // TODO
        double coefficient=0;
        int exponent=0;
        if(allEqual(sequence))
        {
            coefficient=sequence.get(0);
        }
        else
        {
            ArrayList<Double> tempSeq= new ArrayList<>(sequence);
            while(!allEqual(tempSeq))
            {
                tempSeq=differences(tempSeq);
                exponent++;
            }
            coefficient=tempSeq.get(0)/factorial(exponent);
        }
        Term term=new Term(coefficient,exponent);
        return term;
    }

    // returns the value to subtract from the kth index of term t
    // e.g. termUpdate(Term<2, 3>, 4) returns 128 
    //The Term <2, 3> represents +2x^3. When x = 4, this term returns 128. 
    //Term <coefficient, exponent>,x
    public double termUpdate(Term t, int k)
    {
        // TODO
        return t.getCoefficient()*Math.pow(k, t.getExponent());
    }

    // subtracts from each item in sequence the effect of the term t 
    // implements Step 4 of the algorithm description on the project web page 
    //update elements at a certain postion in an ArrayList
    public void updateSequence(Term t)
    {
        // TODO
        int seqSize=sequence.size();
        for (int i=0;i<seqSize;i++) {
            //k start from 1
            double newValue=sequence.get(i)-termUpdate(t,i+1);
            //Updating ist element
            sequence.set(i,newValue);
        }

    }

    // returns the simplest polynomial that generates sequence 
    // implements the algorithm description on the project web page 
    // and also displays the polynomial as a String 
    public Polynomial solveSequence()
    {
        // TODO
        Polynomial poly1 = new Polynomial();
        while (!allEqual(sequence)) {
            Term term = nextTerm();
            poly1.addTerm(term);
            updateSequence(term);
        }
        poly1.addTerm(nextTerm());        
        System.out.print(poly1.displayPolynomial());
        return poly1;
    }

    // reads the file filename, solves the sequences therein, and displays the results
    public static void solveFileSequences(String filename) {
        for (String s : (new FileIO(filename)).lines)
            if (s.length() > 0 && "0123456789-".indexOf(s.charAt(0)) != -1) {
                System.out.print("Sequence: " + s + "\nPolynomial: ");
                (new Sequence(s)).solveSequence();
                System.out.println();
            }
    }
}
