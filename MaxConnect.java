
/*

Prithvi Macherla

<<<<<<< HEAD
=======

//kjadakhdaksljkl
>>>>>>> parent of 2bdd502... fasdfasd
*/
import java.util.Scanner;

 class HelloWorld{
static String a[][]=new String[7][8];
String player,comp;



void empty(){} 
void startGame() // This method is called from the main method. This triggers the game. 
{

	System.out.println("Do you want to play 1st or 2nd? [1/2]");
	Scanner sc=new Scanner(System.in);
	//take i/p from user and store it in reply.
	int reply=sc.nextInt();
	if(reply==1)
	{
		player="1";
		comp="2";
		player(); //player() and comp() keep calling each other until the board is full.
	}
		
	if(reply==2)
	{
		comp="1";
		player="2";
		comp();
	}

}

void player()
 {
	System.out.println("Enter your position"); // column number where the token should be dropped should be entered. 
	Scanner sc=new Scanner(System.in);
	int var=sc.nextInt();// player should give input that is stored in a variable. 
	if((var<8)&&(var!=0))
	System.out.println("You played at:"+var );
	position(var,player); // calls position with the column number entered above as parameter and also who is calling it as parameter. 
	showValues();
	System.out.print("Player: ");
	int a=score(player); // scores display at each step. score() function is invoked. 
	System.out.println(a);
		System.out.print("Computer: ");
	int b=score(comp);
	System.out.print(b);
	System.out.println();
	if(terminatingCondition()) // This condition is checked before calling the opponent's function to check whether the board is full or not. 
	{
		System.out.println("The game is complete and the final scores are:");
	//final scores have to be called here. score function is called. 
		System.out.print("Player: ");
		 a=score(player); // scores display at each step. score() function is invoked. 
		System.out.println(a);
			System.out.print("Computer: ");
		b=score(comp);
		System.out.print(b);
		System.out.println();
		if(a<b)
		{
			System.out.println("Computer won");
		}
		if(a>b)
		{
			System.out.println("You won!");
		}
		if(a==b)
			System.out.println("It is a TIE!");
	}
	else
	{
		
		comp();
 }}

void comp()
{
	int m;
	m=0;
	for(int i=1;i<7;i++)
	{
		for(int j=1;j<8;j++)
		{
			if(a[i][j]!="*")
				m++;
				}
	}
	if(m<2) // the first move of the computer is at 4.
	{
	position(4,comp);
	showValues(); // prints the whole board. 
	System.out.print("Player: ");
	int a=score(player); // scores display at each step. score() function is invoked. 
	System.out.println(a);
		System.out.print("Computer: ");
	int b=score(comp);
	System.out.print(b);
	System.out.println();
		player();
	}
	else
	{ 
	compMove(); // to compete with the player the comp() function calls this function. compMove() is the AI part of the program.  
	showValues();
	System.out.print("Player: ");
	int a=score(player); // scores display at each step. score() function is invoked. 
	System.out.println(a);
		System.out.print("Computer: ");
	int b=score(comp);
	System.out.print(b);
	System.out.println();
	if(terminatingCondition())
	{
	System.out.println("The game is over and the final scores are:");
	System.out.print("Player: ");
	 a=score(player); // scores display at each step. score() function is invoked. 
	System.out.println(a);
		System.out.print("Computer: ");
	 b=score(comp);
	System.out.print(b);
	System.out.println();
	if(a<b)
	{
		System.out.println("Computer won");
	}
	if(a>b)
	{
		System.out.println("You won!");
	}
	if(a==b)
		System.out.println("It is a TIE!");
	}
	else // If the terminating condition is not met, then again the opponent player() is called to keep the game going. 
		player();
}
}

boolean terminatingCondition()
{
	int count=0;
	int i=1; // 'i' is 1 because, if all the elements of the top row are full then it implies that the board is full. 
	for(int j=1;j<8;j++) 
	{
		if(a[i][j]!="*")
		{
			count++;
		}
	
	}
	if(count==7)
	return true;
	else
		return false;
	}






    void position(int j, String who ) // input parameters are the column number and who is making the move. 
{
  
   int count=0;
   if(j<8)
   {
for(int i=6;i>0;i--) // drops the token till the greatest depth available as per the rules of the program. 
{
if(a[i][j]=="*")
{
count++; // To know that it was a valid position and the move was made. 
a[i][j]=who;
break; // comes out of the loop as the move is made. 
}}
if(count==0)
{
	System.out.println("invalid move");
	System.out.println("Enter the position again");
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	position(x,who);
}
}
   if(j>8)
   {
	   System.out.println("invalid move");
		System.out.println("Enter the position again");
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		position(x,who);
   }
}
   
    
void compMove() // Called by comp() to place a token when it turn comes. 
{
int col[]=new int[200];
int prio[]=new int[200];
int p=0;

for(int j=1;j<8;j++) // analyzing starts from i=6,j=1
{
	int i=6;  
   if(a[i][j]=="*")
   continue;
   else
   {
       
       while(i>0)
{
   if(a[i][j]=="*")
   i=0; // helps to exit the while loop when there is nothing in that column to analyze. 
   if(a[i][j]!="*")
   {
   if(a[i][j]==player)
   {
       
    if(j<=4) // covers horizontal playing. 
{
int count=1;
for(int y=j+1; y<j+4; y++)
{
if(a[i][y]==player)
{
count++;
//System.out.println(count);
}
}
if(count==3) // If the player has three tokens consecutively, control goes in. 
{
	for(int b=0;b<4;b++)
	{
if(a[i][j+b]=="*")
{
   if(i!=6)
   {
   if(a[i+1][j+b]!="*")
   {
       col[p]=j+b; // appropriate column number goes into the col array while its priority goes to prio array. 
       prio[p]=100; // given maximum priority to stop him from getting a point when the player has 3 consecutive tokens.
       p++;
      // System.out.println("1 "+col[p]);
   }}
   if(i==6)
   {
       col[p]=j+b;
       prio[p]=100;
       p++;
       //System.out.println("2 "+col[p]);
   }
}
	   }
}
}

   
   if(j>=4)
   {
	
   int count=1;
   for(int y=j-1; y>j-4; y--)
   {
   if(a[i][y]==player)
   {
   count++;
      }
   }
   if(count==3)
   {
   	for(int b=1;b<4;b++)
   	{
   if(a[i][j-b]=="*")
   {
      if(i!=6)
      {
      if(a[i+1][j-b]!="*")
      {
          col[p]=j-b;
          prio[p]=100;
          p++;
      
      }}
      if(i==6)
      {
          col[p]=j-b;
          prio[p]=100;
          p++;
   
      }
   }
   	   }
   }
   }
   
 
   
    if(i>4) // stops from making points vertically. The code is quite similar to above(horizontal) with necessary changes. 
{
int count=1;
for(int x=i-1; x>i-4; x--)
{
if(a[x][j]==player)
{
count++;
}
}
if(count==3)
{
	
if(a[i-3][j]=="*")
{
	
   col[p]=j;
   prio[p]=100;
   p++;
 //  System.out.println("5 "+col[p]);
}
}
}

if(j<=4&&i>=4) // avoiding the player from making points at the diagonals.
{
	 
   int count=1;
   for(int x=i-1, y=j+1; x>i-4; x--,y++)
   {
	  
if(a[x][y]==player)
{
	
count++;
}

if(count==3)
{
	// System.out.println(i+" "+j);
for(int b=1;b<4;b++)
{
if(a[i-b][j+b]=="*")
{
   if(a[i-(b-1)][j+b]!="*")
   {
       col[p]=j+b;
       prio[p]=100;
       p++;
   //    System.out.println("6 "+col[p]);
   }

   }
}
}
}
}
  

if(i>3&&j>3)
{
   int count=1;
   for(int x=i-1, y=j-1; x>i-4; x--,y--)
   {
	 //  System.out.println("i="+x+" j="+y);
 if(a[x][y]!="*")
{
if(a[x][y]==player)
{
count++;
//System.out.println("count="+count);
}

if(count==3)
{
	for(int b=1;b<4;b++)
	{
if(a[i-b][j-b]=="*")
{
  if((i-b)<6)
  {
   if(a[i-(b-1)][j-b]!="*")
   {
       col[p]=j-b;
       prio[p]=100;
       p++;
   }
   }}
   if((i-b)==6)
   {
    col[p]=j-b;
       prio[p]=100;
       p++;
}
}
}
}
}}
} 

  if(a[i][j]==comp) // if the current token that is analyzed is a computer token, then the control comes into this loop. 
  {

 if(j<=4)
   {
   int count=1;
   for(int y=j+1; y<j+4; y++)
   {
   if(a[i][y]!="*")
   {
   if(a[i][y]==comp)
   {
   count++;
   }
   if(a[i][y]==player)
	   count=0;
   }
   }
  if(j+count<8)
   {
for(int b=1;b<=count;b++)
{
	  if(a[i][j+b]=="*")
	  {
      if(i!=6)
      {
      if(a[i+1][j+b]!="*")
      {
          col[p]=j+b; // appropriate column number goes into the array 'col'. 
          prio[p]=count; // count gives the number of tokens of computer are together and this is stored as the priority of this move/option. 
          p++;
      }}
      if(i==6)
      {
          col[p]=j+b;
          prio[p]=count;
          p++;
      }
    }  
   }
   }
   }
 if(j>=4)
 {

 int count=1;
 for(int y=j-1; y>j-4; y--)
 {
 if(a[i][y]!="*")
 {
 if(a[i][y]==comp)
 {
 count++;
 }
 if(a[i][y]==player)
	   count=0;
 }
 }
 if(j>count)
 {
for(int b=1;b<=count;b++)
{
 if(a[i][j-b]=="*")
 {
    if(i!=6)
    {
    if(a[i+1][j-b]!="*")
    {
        col[p]=j-b;
        prio[p]=count;
        p++;
    }}
    if(i==6)
    {
        col[p]=j-b;
        prio[p]=count;
        p++;
    }
}    
 }
 }
 }
      
  if(i>=4) // code for vertical building of the computer tokens. 
   {
   int count=1;
   for(int x=i-1; x>i-4; x--)
   {
   if(a[x][j]!="*")
   {
   if(a[x][j]==comp)
   {
   count++;
   }
   if(a[x][j]==player)
   count=0;
   }}
 
   if(i>count)
   {
   if(a[i-count][j]=="*")
   {
      col[p]=j;
      prio[p]=count;
      p++;
   }
   }
 }
 
   if(j<=4&&i>=4) // across the diagonals. 
   {
      int count=1;
      for(int x=i-1, y=j+1; x>i-4; x--,y++)
      {
      if(a[x][y]!="*")
   {
   if(a[x][y]==comp)
   {
   count++;
   }
   if(a[x][y]==player)
   count=0;
   }}
if(i>count&&(j+count)<8)
{
for(int b=1;b<=count;b++)
{
   if(a[i-b][j+b]=="*")
   {
      if(i!=6)
      {
      if(a[i-(b-1)][j+b]!="*")
      {
          col[p]=j+b;
          prio[p]=count;
          p++;
      }
      }
      if(i==6)
      {
          col[p]=j+b;
          prio[p]=count;
          p++;
      }
      }
   }
   }
 } 

if(i>3&&j>3)
{
   int count=1;
   for(int x=i-1, y=j-1; x>i-4; x--,y--)
   {
 if(a[x][y]!="*")
{
if(a[x][y]==comp)
{
count++;
}
if(a[x][y]==player)
count=0;
}}
if(i>count&&j>count)
{
for(int b=1;b<=count;b++)
{
if(a[i-b][j-b]=="*")
{
   if(i!=6)
   {
   if(a[i-(b-1)][j-b]!="*")
   {
       col[p]=j-b;
       prio[p]=count;
       p++;
   }}
   if(i==6)
   {
       col[p]=j-b;
       prio[p]=count;
       p++;
   }
   }
}
}
}
 } 
    

 
   if(i>=1) // condition to exit the while loop. 
   {
       i--;
   }
   
}

       
   }}}
  
for(int a=0;a<42;a++)
{
   for(int b=a+1;b<42;b++)
   {
       if(prio[a]<prio[b]) // Sorting of the priorities is done finally in descending order, hence the highest priority stats at prio[0] after sorting. 
       {
           int temp= prio[a];
           prio[a]=prio[b];
           prio[b]=temp;
           
           temp= col[a];
           col[a]=col[b];
           col[b]=temp;
       }
   }
}

int f=col[0]; // this is passed to position as the column number. Best of the alternatives present. 
if(f==0) // If there isn't any option provided by the compMove() then a random placing is done to complete the computer's turn of play.
{
    int light=0;
    for(int i=6;i>0;i--)
    {
        for(int j=1;j<8;j++)
        {
            if(a[i][j]=="*")
            {
            light=j;
            break;
            }
        }
        if(light!=0)
        break;
    }
    System.out.println("Computer played at:"+light);
position(light,comp);
}
else
{
     System.out.println("Computer played at:"+f);
position(f,comp);
}
    
}

 int score(String who) // to calculate scores of the player and computer this function is used.
  {
      int scores=0;
       for(int i=6;i>0;i--)
        {
            for(int j=1;j<8;j++)
            {
                 if(j<=4)
{
int count=0;
for(int y=j; y<j+4; y++)
{
if(a[i][y]!="*")
{
if(a[i][y]==who)
{
count++;
}
else 
break;
}
}
if(count>=4)
{
   
scores++;
}
}

if(i>=4)
{
int count=0;
for(int x=i; x>i-4; x--)
{
if(a[x][j]!="*")
{
if(a[x][j]==who)
{
count++;
}
else 
break;
}
if(count>=4)
{
scores++;
}
}
}


if(i>3&&j>3)
{
    int count=0;
    for(int x=i, y=j; x>i-4; x--,y--)
    {
  if(a[x][y]!="*")
{
if(a[x][y]==who)
{
count++;
}
else 
break;
}
if(count>=4)
{
scores++;
}   
}
} 

if(j<=4&&i>=4)
{
    int count=0;
    for(int x=i, y=j; x>i-4; x--,y++)
    {
    if(a[x][y]!="*")
{
if(a[x][y]==who)
{
count++;
}
else 
break;
}
if(count>=4)
{
scores++;
}
}
}
  }}
    return scores;
  }

void showValues() // prints the board when called. 
{
 for(int i=1;i<7;i++)
{
    for(int j=1;j<8;j++)
    {
      System.out.print("   "+a[i][j]);
    }
    System.out.println();
}
}
   public static void main(String args[])
   {
	   for(int i=1;i<7;i++)
	   {
		   for(int j=1;j<8;j++)
		   {
			   a[i][j]="*";
		   }
	   }
       HelloWorld o=new HelloWorld();
       o.startGame(); // Here the game is triggered. 
       //o.position(1);
       //o.position(1);
       //o.position(1);
       //o.position(2);
       // o.position(2);
       //int location=o.compMove();
       //System.out.println(location);
       //o.position(location);
       //o.showValues();
       
   }
}



