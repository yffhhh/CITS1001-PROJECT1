/**
 * Term models terms in a polynomial. 
 * 
 * @author Lyndon While
 * @version 1.0
 */
import java.text.DecimalFormat;
public class Term
{
    // the term = coefficient * x ^ exponent
    private double coefficient;
    private int    exponent;

    public Term(double c, int e)
    {
        coefficient = c;
        exponent = e;
    }

    // returns the coefficient
    public double getCoefficient()
    { return coefficient; }

    // returns the exponent
    public int getExponent()
    {    return exponent;  }

    // returns the term as a simple String for display 
    // e.g. with coefficient = 2 and exponent = 1, return "+ 2.0 x^1" 
    public String displaySimple()
    {
        // TODO
        if(coefficient >= 0 )
        {   return (("+ " + coefficient) + " x^" + exponent);   }
        else
        {   return ("- " + (-1 * coefficient) + " x^" + exponent);  }

    }
    // returns the term as a String for display: 
    // see the sample file and the test program for the layout required 

    // returns the term as a String for display: 
    // see the sample file and the test program for the layout required 
    public String displayImproved()
    {//TODO
        //cx^e
        //e cannot be less than zero,so ignore negative e
        double c=coefficient;
        int e=exponent;
        String result;    
        if(c==0){result= "0";}
        else if(c%1==0)
        {
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
            if (c>0){   //positive coefficient
                if(c==1)
                {
                    if(e==0){result= "+1";}  //1x^0=1
                    else if(e==1){result= "+ x";} //1x^1=x
                    
                    else {result= "+"+"x^"+e;} //1x^e=x^e
                }
                else
                {
                    if(e==0){result= "+"+decimalFormat.format(c);}  //cx^0=c
                    else if(e==1){result= "+"+decimalFormat.format(c)+"x";} //cx^1=cx
                    else {result= "+"+decimalFormat.format(c)+"x^"+e;} //cx^e
                }
            }
            else{//negative coefficient
                if(c==-1)
                {
                    if(e==0){result= "-1";}  //-1x^0=-1
                    else if(e==1){result= "- x";} //-1x^1=-x
                    else {result= "- x^"+ e; } //-1x^e=-x^e
                }
                else
                {
                    if(e==0){result= ""+decimalFormat.format(c);}  //-cx^0=-c
                    else if(e==1){result= ""+decimalFormat.format(c)+"x";} //-cx^1=-cx
                    else {result= ""+decimalFormat.format(c)+"x^"+e;} //-cx^e
                }
            }
        }
        else
        {
            if (c>0){   //positive coefficient
                if(c==1)
                {
                    if(e==0){result= "+1";}  //1x^0=1
                    else if(e==1){result= "+ x";} //1x^1=x
                    else {result= "+"+"x^"+e;} //1x^e=x^e
                }
                else
                {
                    if(e==0){result= "+"+c;}  //cx^0=c
                    else if(e==1){result= "+"+c+"x";} //cx^1=cx
                    else {result= "+"+c+"x^"+e;} //cx^e
                }
            }
            else{//negative coefficient
                if(c==-1)
                {
                    if(e==0){result= "-1";}  //-1x^0=-1
                    else if(e==1){result= "- x";} //-1x^1=-x
                    else {result= "- x^"+ e; } //-1x^e=-x^e
                }
                else
                {
                    if(e==0){result= "-"+(-1*c);}  //-cx^0=-c
                    else if(e==1){result= "-"+(-1*c)+"x";} //-cx^1=-cx
                    else {result= "-"+(-1*c)+"x^"+e;} //-cx^e
                }
            }
        }
        return result;
    }

}
