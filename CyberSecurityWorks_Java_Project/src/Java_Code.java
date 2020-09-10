import java.util.*;
public class Java_Code {
	static void display(char ch[][],int a,int b){
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++)
                System.out.print(ch[i][j]);
            System.out.println();
        }
    }
    static char[][] normal(char ch[][],int a,int b){
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++)
                ch[i][j] = ' ';
        }
        return ch;
    }
    static char[][] putMan(char ch[][],int top){
        ch[0][top] = 'o';
        ch[1][top-1] = '/';
        ch[1][top] = '|';
        ch[1][top+1] = '\\';
        ch[2][top-1] = '<';
        ch[2][top+1] = '>';
        return ch;
    }
    static char[][] putMts(char ch[][],int n,int prev,int max,int x,int in){
        int i,j;
        for(i=0;i<n;i++){
            if(in == 0)
            ch[max-prev-1-i][x+i] = '/';
            else
            ch[max-prev+i][x+i] = '\\';
        }
        return ch;
    }
    static int getFirstValue(int cf[]){
        int i,value=0;
        for(i=0;i<cf.length;i++){
            if(cf[i] < 0 && value > cf[i]){
                value = cf[i];
            }
        }
        value *= -1;
        return value;
    }
    static int[] updateCF(int cf[]){
        int i,value=getFirstValue(cf);
        for(i=0;i<cf.length;i++){
            cf[i] = cf[i] + value;
        }
        return cf;
    }
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        //int m[] = {3,1,2,3,6,2,3,6,2,3,6,3,2,3,6,2,3,4,3,2,5,4,2,1,2,1,2,3,1,2,6,2,3,6,2,3,6,3,2,3,1,5,3,2,1,2,4,2,1,8,1,2};
        //int m[] = {1,2,3,3,2,1,1};
	    System.out.println("Enter the series limit:");
        int inputNo = sc.nextInt();
        int m[]= new int [inputNo];
        for(int k = 0;k<inputNo;k++)
            m[k] = sc.nextInt();
        int cf[] = new int[m.length],total[] = new int[m.length];
        int i,j,max = m[0],y=0,totalY=m[0];
        cf[0]=m[0];total[0] = m[0];
        for(i=1;i<m.length;i++){
            if(i%2 == 0){
                cf[i] = cf[i-1] + m[i];
            }else{
                cf[i] = cf[i-1] - m[i];
            }
            totalY += m[i];
            total[i]= totalY;
            if(cf[i] > max){
                max = cf[i];
                y = totalY;
            }
            /*if(cf[i] < 0){
                System.out.println("Input Error");
                System.exit(0);
            }*/
        }
        int ivalue = getFirstValue(cf);//new update
        cf = updateCF(cf);//new update
        max = max+ivalue;//new update
        //System.out.println(max+ "-----"+ivalue);
        //System.out.println("i  m[i]  cf[i]  total[]");
        //for(i=0;i<m.length;i++)
        //System.out.println(i+"--  "+m[i]+"     "+cf[i]+"     "+total[i]);
        char ch[][] = new char[max+3][totalY+2];
        ch = normal(ch,max+3,totalY+2);
        ch = putMan(ch,y);
        putMts(ch,m[0],ivalue,max+3,0,0);//3rd para was 0
        for(i=1;i<m.length;i++){
            if(i%2 == 0){
                putMts(ch,m[i],cf[i-1],max+3,total[i-1],0);
            }else{
                putMts(ch,m[i],cf[i-1],max+3,total[i-1],1);
            }
            if(cf[i] == max)
            break;
        }
        for(j=i+1;j<m.length;j++){
            if(j%2 == 0){
                putMts(ch,m[j],cf[j-1],max+3,total[j-1]+1,0);
            }else{
                putMts(ch,m[j],cf[j-1],max+3,total[j-1]+1,1);
            }
        }
        display(ch,max+3,totalY+2);
        
	}

}
